package org.taskmanager.user_server.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taskmanager.user_server.dao.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface IUserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    List<User> findByUuidIn(List<UUID> users);
}
