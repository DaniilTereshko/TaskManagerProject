package org.taskmanager.authentication_service.dao.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "verification_token")
public class VerificationToken {
    private static final int EXPIRATION = 60*24;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "token", unique = true)
    private UUID token;
    @Column(name = "user_id", nullable = false)
    private UUID user;
    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

    public VerificationToken() {
    }
}
