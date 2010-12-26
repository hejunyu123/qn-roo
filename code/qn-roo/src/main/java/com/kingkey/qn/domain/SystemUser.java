package com.kingkey.qn.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NoResultException;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.security.core.GrantedAuthority;

import com.kingkey.qn.security.SystemUserGrantedAuthority;

@RooJavaBean
@RooToString(toStringMethod = "toRooString")
@RooEntity
public class SystemUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String name;

    @NotNull
    @Column(unique = true)
    private String login;

    @NotNull
    private String password;

    private String telNo;

    private String email;

    @NotNull
    @Enumerated
    private Permission permission;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    public Collection<GrantedAuthority> getAuthorities() {
        GrantedAuthority[] auths = { new SystemUserGrantedAuthority(this) };
        return Arrays.asList(auths);
    }

    public static SystemUser findSystemUserByLogin(String login) throws NoResultException {
        return entityManager()
                .createQuery("select o from SystemUser o where o.login = :login", SystemUser.class)
                .setParameter("login", login).getSingleResult();
    }

    @Override
    public String toString() {
        return name;
    }

}
