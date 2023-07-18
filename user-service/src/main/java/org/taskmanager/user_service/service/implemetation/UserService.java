package org.taskmanager.user_service.service.implemetation;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.taskmanager.user_service.core.dto.base.UserDTO;
import org.taskmanager.user_service.core.dto.base.UserPageDTO;
import org.taskmanager.user_service.core.dto.base.UserRegistrationDTO;
import org.taskmanager.user_service.core.dto.create.UserCreateDTO;
import org.taskmanager.user_service.core.dto.update.UserUpdateDTO;
import org.taskmanager.user_service.core.enums.UserRole;
import org.taskmanager.user_service.core.enums.UserStatus;
import org.taskmanager.user_service.dao.api.IUserRepository;
import org.taskmanager.user_service.dao.entity.User;
import org.taskmanager.user_service.service.api.ISendMailService;
import org.taskmanager.user_service.service.api.IUserService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final ISendMailService sendMailService;
    private final ConversionService conversionService;


    public UserService(IUserRepository userRepository, ISendMailService mailService, ConversionService conversionService) {
        this.userRepository = userRepository;
        this.sendMailService = mailService;
        this.conversionService = conversionService;
    }

    @Override
    public User save(UserCreateDTO item) {
        //TODO validation
        UserDTO userDTO = new UserDTO(item.getFio(), item.getMail(), item.getRole(), item.getStatus());
        UUID activationCode = sendMailService.sendActivateMail(userDTO);
        User user = conversionService.convert(userDTO, User.class);
        user.setPassword(item.getPassword());
        user.setActivationCode(activationCode);
        user.setUuid(UUID.randomUUID());

        return userRepository.save(user);
    }

    @Override
    public void registration(UserRegistrationDTO item) {
        UserDTO userDTO = new UserDTO(item.getFio(), item.getMail(), UserRole.USER.name() ,UserStatus.WAITING_ACTIVATION.name());
        UUID activationCode = sendMailService.sendActivateMail(userDTO);
        User user = conversionService.convert(userDTO, User.class);
        userDTO.setUuid(UUID.randomUUID());
        user.setPassword(item.getPassword());
        user.setActivationCode(activationCode);

        userRepository.save(user);
    }

    @Override
    public UserPageDTO get(PageRequest pageRequest) {
        Page<User> userPage = userRepository.findAll(pageRequest);
        return new UserPageDTO(userPage.getNumber(), userPage.getSize(), userPage.getTotalPages(),
                userPage.getTotalElements(), userPage.isFirst(), userPage.getNumberOfElements(),
                userPage.isLast(), userPage.get().map(u -> this.conversionService.convert(u, UserDTO.class)).toList());
    }

    @Override
    public User get(UUID uuid) {
        return userRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("Не могу найти тебя"));
    }

    @Override
    public User update(UserUpdateDTO item) {
        UserDTO userDTO = new UserDTO(item.getUuid(), item.getFio(), item.getMail(),
                item.getRole(), item.getStatus(), item.getUpdateDate());
        User user = conversionService.convert(userDTO, User.class);
        user.setPassword(item.getPassword());
        User currentUser = get(user.getUuid());//TODO ???
        if (user.getUpdateDate().isEqual(currentUser.getUpdateDate())) {
            return userRepository.save(user);
        } else {
            throw new RuntimeException("Версии не совпадают");
        }
    }

    @Override
    public boolean activate(String email, String code) {
        User user = userRepository.findByEmailAndActivationCode(email, UUID.fromString(code));
        if(user == null){
            return false;
        }
        user.setActivationCode(null);
        user.setUserStatus(UserStatus.ACTIVATED);
        userRepository.save(user);
        return true;
    }
}
