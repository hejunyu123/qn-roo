// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kingkey.qn.domain;

import java.lang.String;

privileged aspect Department_Roo_ToString {
    
    public String Department.toRooString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("Name: ").append(getName()).append(", ");
        sb.append("Company: ").append(getCompany()).append(", ");
        sb.append("Users: ").append(getUsers() == null ? "null" : getUsers().size());
        return sb.toString();
    }
    
}
