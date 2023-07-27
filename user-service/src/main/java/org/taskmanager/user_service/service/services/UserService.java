package org.taskmanager.user_service.service.services;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.taskmanager.user_service.core.dto.create.UserCreateDTO;
import org.taskmanager.user_service.core.dto.update.UserUpdateDTO;
import org.taskmanager.user_service.dao.entity.User;
import org.taskmanager.user_service.dao.repositories.IUserRepository;
import org.taskmanager.user_service.endpoints.web.exception_hendler.exception.EmailAlreadyTakenException;
import org.taskmanager.user_service.endpoints.web.exception_hendler.exception.NoSuchUserException;
import org.taskmanager.user_service.endpoints.web.exception_hendler.exception.VersionsMatchException;
import org.taskmanager.user_service.service.api.IUserService;

import java.util.UUID;

public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final ConversionService conversionService;


    public UserService(IUserRepository userRepository, ConversionService conversionService) {
        this.userRepository = userRepository;
        this.conversionService = conversionService;
    }

    @Override
    public User save(UserCreateDTO item) {
        userRepository.findByEmail(item.getMail()).ifPresent(u -> {throw new EmailAlreadyTakenException(item.getMail());});
        User user = conversionService.convert(item, User.class);
        user.setUuid(UUID.randomUUID());
        return userRepository.save(user);
    }

    @Override
    public Page<User> get(PageRequest pageRequest) {
        Page<User> userPage = userRepository.findAll(pageRequest);
        return userPage;
    }

    @Override
    public User get(UUID uuid) {
        return userRepository.findById(uuid)
                .orElseThrow(() -> new NoSuchUserException(uuid));
    }

    @Override
    public User update(UserUpdateDTO item) {
        User user = conversionService.convert(item, User.class);
        User currentUser = this.get(user.getUuid());
        user.setCreateDate(currentUser.getCreateDate());
        if (user.getUpdateDate().isEqual(currentUser.getUpdateDate())) {
            return userRepository.save(user);
        } else {
            throw new VersionsMatchException();
        }
    }
}
