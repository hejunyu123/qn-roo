// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kingkey.qn.domain;

import com.kingkey.qn.domain.Company;
import com.kingkey.qn.domain.Department;
import com.kingkey.qn.domain.Permission;
import java.lang.String;

privileged aspect SystemUser_Roo_JavaBean {
    
    public String SystemUser.getName() {
        return this.name;
    }
    
    public void SystemUser.setName(String name) {
        this.name = name;
    }
    
    public String SystemUser.getLogin() {
        return this.login;
    }
    
    public void SystemUser.setLogin(String login) {
        this.login = login;
    }
    
    public String SystemUser.getPassword() {
        return this.password;
    }
    
    public void SystemUser.setPassword(String password) {
        this.password = password;
    }
    
    public String SystemUser.getTelNo() {
        return this.telNo;
    }
    
    public void SystemUser.setTelNo(String telNo) {
        this.telNo = telNo;
    }
    
    public String SystemUser.getEmail() {
        return this.email;
    }
    
    public void SystemUser.setEmail(String email) {
        this.email = email;
    }
    
    public Permission SystemUser.getPermission() {
        return this.permission;
    }
    
    public void SystemUser.setPermission(Permission permission) {
        this.permission = permission;
    }
    
    public Company SystemUser.getCompany() {
        return this.company;
    }
    
    public void SystemUser.setCompany(Company company) {
        this.company = company;
    }
    
    public Department SystemUser.getDepartment() {
        return this.department;
    }
    
    public void SystemUser.setDepartment(Department department) {
        this.department = department;
    }
    
}
