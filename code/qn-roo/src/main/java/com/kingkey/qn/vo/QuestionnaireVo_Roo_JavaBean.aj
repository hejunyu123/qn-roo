// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kingkey.qn.vo;

import com.kingkey.qn.domain.QuestionnaireType;
import com.kingkey.qn.vo.PartVo;
import java.lang.Boolean;
import java.lang.Long;
import java.lang.String;
import java.util.List;

privileged aspect QuestionnaireVo_Roo_JavaBean {
    
    public Long QuestionnaireVo.getId() {
        return this.id;
    }
    
    public void QuestionnaireVo.setId(Long id) {
        this.id = id;
    }
    
    public String QuestionnaireVo.getSubject() {
        return this.subject;
    }
    
    public void QuestionnaireVo.setSubject(String subject) {
        this.subject = subject;
    }
    
    public String QuestionnaireVo.getDescription() {
        return this.description;
    }
    
    public void QuestionnaireVo.setDescription(String description) {
        this.description = description;
    }
    
    public Boolean QuestionnaireVo.getDisplayPartLabel() {
        return this.displayPartLabel;
    }
    
    public void QuestionnaireVo.setDisplayPartLabel(Boolean displayPartLabel) {
        this.displayPartLabel = displayPartLabel;
    }
    
    public QuestionnaireType QuestionnaireVo.getQuestionnaireType() {
        return this.questionnaireType;
    }
    
    public void QuestionnaireVo.setQuestionnaireType(QuestionnaireType questionnaireType) {
        this.questionnaireType = questionnaireType;
    }
    
    public List<PartVo> QuestionnaireVo.getParts() {
        return this.parts;
    }
    
    public void QuestionnaireVo.setParts(List<PartVo> parts) {
        this.parts = parts;
    }
    
    public int QuestionnaireVo.getAnsweredCount() {
        return this.answeredCount;
    }
    
    public void QuestionnaireVo.setAnsweredCount(int answeredCount) {
        this.answeredCount = answeredCount;
    }
    
}
