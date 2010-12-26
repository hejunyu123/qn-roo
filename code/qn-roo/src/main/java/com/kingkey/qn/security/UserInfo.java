package com.kingkey.qn.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.kingkey.qn.domain.SystemUser;

public class UserInfo implements org.springframework.security.core.userdetails.UserDetails {

    private static final long serialVersionUID = 1L;

    private SystemUser systemUser;

    public UserInfo(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return systemUser.getAuthorities();
    }

    @Override
    public String getPassword() {
        return systemUser.getPassword();
    }

    @Override
    public String getUsername() {
        // return systemUser.getName();
        return systemUser.getLogin();
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
