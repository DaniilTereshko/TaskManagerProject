package org.taskmanager.user_server.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taskmanager.user_server.dao.entity.VerificationToken;
import org.taskmanager.user_server.dao.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface IVerificationTokenRepository extends JpaRepository<VerificationToken, UUID> {
    Optional<VerificationToken> findByUser(User user);
    Optional<VerificationToken> findByEmail(String email);

}
