// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kingkey.qn.domain;

import com.kingkey.qn.domain.Department;
import java.lang.String;
import java.util.Set;

privileged aspect Company_Roo_JavaBean {
    
    public String Company.getName() {
        return this.name;
    }
    
    public void Company.setName(String name) {
        this.name = name;
    }
    
    public Set<Department> Company.getDepartments() {
        return this.departments;
    }
    
    public void Company.setDepartments(Set<Department> departments) {
        this.departments = departments;
    }
    
}
