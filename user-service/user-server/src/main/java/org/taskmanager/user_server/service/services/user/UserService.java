package org.taskmanager.user_server.service.services.user;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.taskmanager.user_client.core.dto.create.UserCreateDTO;
import org.taskmanager.user_client.core.dto.update.UserUpdateDTO;
import org.taskmanager.user_client.core.enums.UserRole;
import org.taskmanager.user_client.core.enums.UserStatus;
import org.taskmanager.user_server.core.dto.base.UserRegistrationDTO;
import org.taskmanager.user_server.core.exception.*;
import org.taskmanager.user_server.dao.entity.User;
import org.taskmanager.user_server.dao.repositories.IUserRepository;
import org.taskmanager.user_server.service.api.audit.IAuditService;
import org.taskmanager.user_server.service.api.user.IUserService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class UserService implements IUserService {
    private static final String USER_ALREADY_EXISTS = "Пользователь уже зарегистрирован";
    private static final String VERSIONS_MATCH_ERROR = "Версии не совпадают";
    private static final String USER_NOT_FOUND= "Данный пользователь не найден";
    private static final String USER_IS_SAVED = "Пользователь сохранен";
    private static final String USER_IS_UPDATED = "Пользователь обновлен";
    private static final String INCORRECT_DATA = "Неверные данные. Попробуйте еще раз";
    private final IUserRepository userRepository;
    private final IAuditService auditService;
    private final Validator validator;
    private final PasswordEncoder passwordEncoder;
    private final ConversionService conversionService;


    public UserService(IUserRepository userRepository, IAuditService auditService, Validator validator, PasswordEncoder passwordEncoder, ConversionService conversionService) {
        this.userRepository = userRepository;
        this.auditService = auditService;
        this.validator = validator;
        this.passwordEncoder = passwordEncoder;
        this.conversionService = conversionService;
    }
    @Transactional
    @Override
    public User save(UserCreateDTO item) {
        this.validate(item);
        this.userRepository.findByEmail(item.getMail())
                .ifPresent(u -> {throw new EmailAlreadyTakenException(USER_ALREADY_EXISTS, "mail");});
        User user = this.conversionService.convert(item, User.class);//TODO !!!
        user.setUuid(UUID.randomUUID());
        user.setPassword(this.passwordEncoder.encode(item.getPassword()));
        User savedUser = this.userRepository.saveAndFlush(user);
        this.auditService.save(USER_IS_SAVED, savedUser.getUuid().toString());
        return savedUser;
    }
    @Transactional
    @Override
    public User save(UserRegistrationDTO item) {
        this.userRepository.findByEmail(item.getMail())
                .ifPresent(u -> {throw new EmailAlreadyTakenException(USER_ALREADY_EXISTS, "mail");});
        User user = new User(item.getFio(), UserRole.USER,
                UserStatus.WAITING_ACTIVATION,  item.getMail(),
                this.passwordEncoder.encode(item.getPassword()));
        user.setUuid(UUID.randomUUID());
        User savedUser = this.userRepository.saveAndFlush(user);
        return savedUser;
    }
    @Transactional(readOnly = true)
    @Override
    public Page<User> get(int page, int size) {
        if(page < 0 || size < 0){
            throw new IncorrectDataException(INCORRECT_DATA);
        }
        PageRequest pageRequest = PageRequest.of(page, size);
        return this.userRepository.findAll(pageRequest);
    }
    @Transactional(readOnly = true)
    @Override
    public User get(UUID uuid) {
        return this.userRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    }
    @Transactional
    @Override
    public User update(UserUpdateDTO item) {
        this.validate(item);
        User currentUser = this.get(item.getUuid());
        if (!item.getUpdateDate().isEqual(currentUser.getUpdateDate())) {
            throw new VersionsMatchException(VERSIONS_MATCH_ERROR);
        }
        currentUser.setFio(item.getFio());
        currentUser.setPassword(this.passwordEncoder.encode(item.getPassword()));
        currentUser.setEmail(item.getMail());
        currentUser.setStatus(item.getStatus());
        currentUser.setRole(item.getRole());
        User updatedUser = this.userRepository.saveAndFlush(currentUser);
        this.auditService.save(USER_IS_UPDATED, updatedUser.getUuid().toString());
        return updatedUser;
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
    @Transactional(readOnly = true)
    @Override
    public List<User> get(List<UUID> users) {
        return this.userRepository.findByUuidIn(users);
    }
    @Transactional
    @Override
    public void activate(String email) {
        User user = this.findByEmail(email).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        user.setStatus(UserStatus.ACTIVATED);
        this.userRepository.save(user);
    }

    private <T> void validate(T item){
        Set<ConstraintViolation<T>> constraintViolations = this.validator.validate(item);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
