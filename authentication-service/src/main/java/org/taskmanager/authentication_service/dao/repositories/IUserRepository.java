package org.taskmanager.authentication_service.dao.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.taskmanager.user_service.dao.entity.User;

import java.util.Optional;
import java.util.UUID;
public interface IUserRepository extends JpaRepository<User, UUID> {
    @Override
    <S extends User> S save(S entity);

    @Override
    Page<User> findAll(Pageable pageable);

    @Override
    Optional<User> findById(UUID uuid);
}