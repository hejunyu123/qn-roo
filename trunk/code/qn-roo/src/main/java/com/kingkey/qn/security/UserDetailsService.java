package com.kingkey.qn.security;

import javax.persistence.NoResultException;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.kingkey.qn.domain.SystemUser;

@Component
public class UserDetailsService implements
        org.springframework.security.core.userdetails.UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException,
            DataAccessException {

        SystemUser systemUser = null;
        try {
            systemUser = SystemUser.findSystemUserByLogin(username);
        } catch (NoResultException ex) {
            throw new UsernameNotFoundException("not found user: " + username);
        }

        return new UserInfo(systemUser);
    }
}
