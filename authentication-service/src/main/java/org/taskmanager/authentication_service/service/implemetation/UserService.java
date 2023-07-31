package org.taskmanager.authentication_service.service.implemetation;

import org.taskmanager.authentication_service.core.dto.base.UserRegistrationDTO;
import org.taskmanager.authentication_service.core.dto.create.UserCreateDTO;
import org.taskmanager.authentication_service.core.enums.UserRole;
import org.taskmanager.authentication_service.core.enums.UserStatus;
import org.taskmanager.authentication_service.service.api.INotificationService;
import org.taskmanager.authentication_service.service.api.IUserService;
import org.taskmanager.user_server.dao.entity.User;

import java.util.UUID;

public class UserService implements IUserService {

    private final INotificationService notificationService;


    public UserService(INotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void registration(UserRegistrationDTO item) {
        UserCreateDTO userCreateDTO = new UserCreateDTO(item.getFio(), item.getMail(), UserRole.USER.name(), UserStatus.WAITING_ACTIVATION.name(), item.getPassword());

        UUID activationCode = notificationService.sendActivateMail(userDTO);//TODO показать

        user.setActivationCode(activationCode);//???
        //Отправляем post запрос на UserService
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
