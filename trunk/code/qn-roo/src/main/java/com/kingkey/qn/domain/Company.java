package com.kingkey.qn.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
// @RooToString
@RooEntity
public class Company {

    @NotNull
    @Column(unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
    private Set<Department> departments = new HashSet<Department>();

    @Override
    public String toString() {
        return getName();
    }
}
