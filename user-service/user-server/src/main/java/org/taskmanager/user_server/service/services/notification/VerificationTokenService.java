package org.taskmanager.user_server.service.services.notification;

import org.springframework.transaction.annotation.Transactional;
import org.taskmanager.user_server.dao.entity.VerificationToken;
import org.taskmanager.user_server.dao.entity.User;
import org.taskmanager.user_server.dao.repositories.IVerificationTokenRepository;
import org.taskmanager.user_server.service.api.notification.IVerificationTokenService;

import java.util.Optional;

public class VerificationTokenService implements IVerificationTokenService {
    private final IVerificationTokenRepository emailTokenRepository;

    public VerificationTokenService(IVerificationTokenRepository emailTokenRepository) {
        this.emailTokenRepository = emailTokenRepository;
    }
    @Transactional
    @Override
    public VerificationToken save(VerificationToken item) {
        return this.emailTokenRepository.save(item);
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<VerificationToken> findByUser(User user) {
        return this.emailTokenRepository.findByUser(user);
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<VerificationToken> findByEmail(String email) {
        return this.emailTokenRepository.findByEmail(email);
    }
    @Transactional
    @Override
    public void delete(VerificationToken item) {
        this.emailTokenRepository.delete(item);
    }
}
