// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kingkey.qn.domain;

import com.kingkey.qn.domain.Company;
import com.kingkey.qn.domain.SystemUser;
import java.lang.String;
import java.util.Set;

privileged aspect Department_Roo_JavaBean {
    
    public String Department.getName() {
        return this.name;
    }
    
    public void Department.setName(String name) {
        this.name = name;
    }
    
    public Company Department.getCompany() {
        return this.company;
    }
    
    public void Department.setCompany(Company company) {
        this.company = company;
    }
    
    public Set<SystemUser> Department.getUsers() {
        return this.users;
    }
    
    public void Department.setUsers(Set<SystemUser> users) {
        this.users = users;
    }
    
}