package org.taskmanager.user_client.core.dto.base;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.taskmanager.user_client.core.enums.UserRole;
import org.taskmanager.user_client.core.enums.UserStatus;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class UserDetailsDTO implements UserDetails {
    private final UUID uuid;
    private final String fio;
    private final String username;
    private final String password;
    private final UserStatus status;
    private final List<UserRole> authorities;

    public UserDetailsDTO(UUID uuid, String fio, String username, String password, UserStatus status, List<UserRole> authorities) {
        this.uuid = uuid;
        this.fio = fio;
        this.username = username;
        this.password = password;
        this.status = status;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !status.equals(UserStatus.DEACTIVATED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return status.equals(UserStatus.WAITING_ACTIVATION);
    }

    public String getFio() {
        return fio;
    }

    public UUID getUuid() {
        return uuid;
    }


}
