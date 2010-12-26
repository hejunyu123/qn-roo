package com.kingkey.qn.security;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.kingkey.qn.domain.SystemUser;

@Component
public class AuthorityService {

    private static final Logger log = Logger.getLogger(AuthorityService.class);

    /**
     * 
     * @param roles
     * @return true if the user has one of the specified roles.
     */
    public boolean hasAnyRole(String... roles) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            Collection<GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                String auth = authority.getAuthority();
                for (String role : roles) {
                    if (role.equals(auth))
                        return true;
                }
            }
        }

        return false;
    }

    public SystemUser getLoginUser() {

        boolean isAnonymous = hasAnyRole("ROLE_ANONYMOUS");
        if (isAnonymous)
            return null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        log.info("login user: " + login);
        SystemUser user = SystemUser.findSystemUserByLogin(login);

        return user;
    }
}
