// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kingkey.qn.domain;

import com.kingkey.qn.domain.Question;
import com.kingkey.qn.domain.Questionnaire;
import java.lang.Integer;
import java.lang.String;
import java.util.Set;

privileged aspect Part_Roo_JavaBean {
    
    public String Part.getLabel() {
        return this.label;
    }
    
    public void Part.setLabel(String label) {
        this.label = label;
    }
    
    public Integer Part.getOrderNo() {
        return this.orderNo;
    }
    
    public void Part.setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
    
    public Questionnaire Part.getQuestionnaire() {
        return this.questionnaire;
    }
    
    public void Part.setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }
    
    public Set<Question> Part.getQuestions() {
        return this.questions;
    }
    
    public void Part.setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
    
}
