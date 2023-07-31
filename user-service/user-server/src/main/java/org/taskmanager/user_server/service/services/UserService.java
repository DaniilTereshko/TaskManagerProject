package org.taskmanager.user_server.service.services;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.taskmanager.user_client.core.dto.base.UserDTO;
import org.taskmanager.user_client.core.dto.create.UserCreateDTO;
import org.taskmanager.user_client.core.dto.update.UserUpdateDTO;
import org.taskmanager.user_server.dao.entity.User;
import org.taskmanager.user_server.dao.repositories.IUserRepository;
import org.taskmanager.user_server.endpoints.web.exception_hendler.exception.EmailAlreadyTakenException;
import org.taskmanager.user_server.endpoints.web.exception_hendler.exception.NoSuchUserException;
import org.taskmanager.user_server.endpoints.web.exception_hendler.exception.VersionsMatchException;
import org.taskmanager.user_server.service.api.IAuditService;
import org.taskmanager.user_server.service.api.IUserService;

import java.util.UUID;

public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final IAuditService auditService;
    private final ConversionService conversionService;


    public UserService(IUserRepository userRepository, IAuditService auditService, ConversionService conversionService) {
        this.userRepository = userRepository;
        this.auditService = auditService;
        this.conversionService = conversionService;
    }

    @Override
    public User save(UserCreateDTO item) {
        this.userRepository.findByEmail(item.getMail())
                .ifPresent(u -> {throw new EmailAlreadyTakenException(item.getMail());});
        User user = this.conversionService.convert(item, User.class);
        user.setUuid(UUID.randomUUID());
        User savedUser = this.userRepository.save(user);
        this.auditService.save(conversionService.convert(savedUser, UserDTO.class), "Пользователь сохранен");
        return savedUser;
    }

    @Override
    public Page<User> get(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<User> userPage = this.userRepository.findAll(pageRequest);
        return userPage;
    }

    @Override
    public User get(UUID uuid) {
        return this.userRepository.findById(uuid)
                .orElseThrow(() -> new NoSuchUserException(uuid));
    }

    @Override
    public User update(UserUpdateDTO item) {
        User updatedUser;
        User user = this.conversionService.convert(item, User.class);
        User currentUser = this.get(user.getUuid());
        user.setCreateDate(currentUser.getCreateDate());
        if (user.getUpdateDate().isEqual(currentUser.getUpdateDate())) {
            updatedUser = this.userRepository.save(user);
        } else {
            throw new VersionsMatchException();
        }
        if (updatedUser != null){
            this.auditService.save(conversionService.convert(updatedUser, UserDTO.class), "Пользователь обновлен");
        }
        return updatedUser;
    }
}
