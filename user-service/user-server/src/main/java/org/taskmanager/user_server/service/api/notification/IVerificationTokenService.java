package org.taskmanager.user_server.service.api.notification;

import org.taskmanager.user_server.dao.entity.VerificationToken;
import org.taskmanager.user_server.dao.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface IVerificationTokenService {
    VerificationToken save(VerificationToken item);
    Optional<VerificationToken> findByUser(User user);
    Optional<VerificationToken> findByEmail(String email);
    void delete(VerificationToken item);
}
