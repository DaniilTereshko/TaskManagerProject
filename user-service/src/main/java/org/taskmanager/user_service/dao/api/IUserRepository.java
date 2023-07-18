package org.taskmanager.user_service.dao.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;
import org.taskmanager.user_service.dao.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface IUserRepository extends JpaRepository<User, UUID> {
    @Override
    <S extends User> S save(S entity);

    @Override
    Page<User> findAll(Pageable pageable);

    @Override
    Optional<User> findById(UUID uuid);

    User findByEmailAndActivationCode(String email, UUID activationCode);
}
