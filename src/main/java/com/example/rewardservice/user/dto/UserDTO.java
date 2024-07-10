package com.example.rewardservice.user.dto;

import com.example.rewardservice.user.domain.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@ToString
public class UserDTO implements UserDetails {
    private UUID id;
    private String password;
    private String name;
    private String email;
    private long totalPoint;
    private LocalDateTime lastUpdateDate;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDTO(UUID id,String userPassword, String userName,String userEmail, long totalPoint, LocalDateTime lastUpdateDate, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.password = userPassword;
        this.name = userName;
        this.email = userEmail;
        this.totalPoint = totalPoint;
        this.lastUpdateDate = lastUpdateDate;
        this.authorities = authorities;
    }

    public UserDTO() {
    }


    public UserDTO(User user, Collection<GrantedAuthority> authorities) {
        this.id = user.getId();
        //this.userId = user.getUserId();
        this.password = user.getPassword();
        this.name = user.getName();
        this.totalPoint = user.getTotalPoint();
        this.lastUpdateDate = user.getLastUpdateDate();
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
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}