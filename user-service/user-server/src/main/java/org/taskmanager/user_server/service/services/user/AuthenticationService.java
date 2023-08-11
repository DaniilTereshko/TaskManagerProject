package org.taskmanager.user_server.service.services.user;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.taskmanager.user_client.core.enums.UserStatus;
import org.taskmanager.user_server.core.dto.base.TokenDTO;
import org.taskmanager.user_server.core.dto.base.UserLoginDTO;
import org.taskmanager.user_server.core.dto.base.UserRegistrationDTO;
import org.taskmanager.user_server.core.exception.*;
import org.taskmanager.user_server.dao.entity.VerificationToken;
import org.taskmanager.user_server.dao.entity.User;
import org.taskmanager.user_server.endpoints.web.util.JwtHandler;
import org.taskmanager.user_server.service.api.user.IAuthenticationService;
import org.taskmanager.user_server.service.api.notification.IVerificationTokenService;
import org.taskmanager.user_server.service.api.notification.INotificationManagerService;
import org.taskmanager.user_server.service.api.user.IUserService;

import java.util.Set;
import java.util.UUID;

public class AuthenticationService implements IAuthenticationService {
    private static final String USER_ALREADY_EXISTS = "Пользователь уже зарегистрирован";
    private static final String TOKEN_NOT_FOUND = "Токен активации для данного пользователя не найден";
    private static final String VERIFICATION_ERROR = "Ошибка верификации пользователя";
    private static final String USER_NOT_FOUND= "Данный пользователь не найден";
    private static final String LOGIN_ERROR = "Неверные данные для входа в систему";
    private static final String NOT_ACTIVATED = "Для вохода в систему пользователь должен быть активирован";
    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtHandler jwtService;
    private final UserHolder userHolder;
    private final IVerificationTokenService verificationTokenService;
    private final INotificationManagerService notificationService;
    private final Validator validator;

    public AuthenticationService(IUserService userService, PasswordEncoder passwordEncoder, JwtHandler jwtService, UserHolder userHolder, IVerificationTokenService verificationTokenService, INotificationManagerService notificationService, Validator validator) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userHolder = userHolder;
        this.verificationTokenService = verificationTokenService;
        this.notificationService = notificationService;
        this.validator = validator;
    }
    @Transactional
    @Override
    public void registration(UserRegistrationDTO item) {
        this.validate(item);
        this.userService.findByEmail(item.getMail()).ifPresent(u -> {throw new EmailAlreadyTakenException(USER_ALREADY_EXISTS, "mail");});
        User user = this.userService.save(item);
        this.generateVerificationToken(user);
        this.notificationService.sendVerificationMail(user);
    }
    @Transactional
    @Override
    public void verification(String email, UUID token) {
        VerificationToken verificationToken = this.verificationTokenService.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(TOKEN_NOT_FOUND));
        User user = verificationToken.getUser();
        if (!(token.equals(verificationToken.getToken()) && user.getStatus().equals(UserStatus.WAITING_ACTIVATION))){
            throw new VerificationException(VERIFICATION_ERROR);
        }
        this.userService.activate(email);
        this.verificationTokenService.delete(verificationToken);
    }
    @Transactional(readOnly = true)
    @Override
    public TokenDTO login(UserLoginDTO item) {
        User user = this.userService.findByEmail(item.getMail())
                .orElseThrow(() -> new LoginException(LOGIN_ERROR));
        if(!this.passwordEncoder.matches(item.getPassword(), user.getPassword())){
            throw new LoginException(LOGIN_ERROR);
        }
        if(!user.getStatus().equals(UserStatus.ACTIVATED)){
            throw new UserNotActivatedException(NOT_ACTIVATED);
        }
        String jwt = this.jwtService.generateAccessToken(user.getEmail());
        return new TokenDTO(jwt);
    }
    @Transactional(readOnly = true)
    @Override
    public User getMe() {
        UserDetails userDetails = this.userHolder.getUser();
        return this.userService.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    }
    @Transactional
    private void generateVerificationToken(User user){
        VerificationToken token = new VerificationToken(UUID.randomUUID(), UUID.randomUUID(), user.getEmail(), user);
        this.verificationTokenService.save(token);
    }
    private <T> void validate(T item){
        Set<ConstraintViolation<T>> constraintViolations = this.validator.validate(item);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
