// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kingkey.qn.domain;

import com.kingkey.qn.domain.Question;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;

privileged aspect QuestionOption_Roo_JavaBean {
    
    public String QuestionOption.getContent() {
        return this.content;
    }
    
    public void QuestionOption.setContent(String content) {
        this.content = content;
    }
    
    public Integer QuestionOption.getOrderNo() {
        return this.orderNo;
    }
    
    public void QuestionOption.setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
    
    public Boolean QuestionOption.getNeedCompletion() {
        return this.needCompletion;
    }
    
    public void QuestionOption.setNeedCompletion(Boolean needCompletion) {
        this.needCompletion = needCompletion;
    }
    
    public Question QuestionOption.getQuestion() {
        return this.question;
    }
    
    public void QuestionOption.setQuestion(Question question) {
        this.question = question;
    }
    
}