// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kingkey.qn.domain;

import java.lang.String;

privileged aspect Part_Roo_ToString {
    
    public String Part.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("Label: ").append(getLabel()).append(", ");
        sb.append("OrderNo: ").append(getOrderNo()).append(", ");
        sb.append("Questionnaire: ").append(getQuestionnaire()).append(", ");
        sb.append("Questions: ").append(getQuestions() == null ? "null" : getQuestions().size());
        return sb.toString();
    }
    
}