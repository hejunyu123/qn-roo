package com.kingkey.qn.security;

import org.springframework.security.core.GrantedAuthority;

import com.kingkey.qn.domain.SystemUser;

public class SystemUserGrantedAuthority implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    private SystemUser systemUser;

    public SystemUserGrantedAuthority(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    @Override
    public String getAuthority() {
        return systemUser.getPermission().name();
    }

}
