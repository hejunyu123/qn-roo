// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kingkey.qn.vo;

import com.kingkey.qn.domain.QuestionType;
import com.kingkey.qn.vo.PartVo;
import com.kingkey.qn.vo.QuestionOptionVo;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.List;

privileged aspect QuestionVo_Roo_JavaBean {
    
    public Long QuestionVo.getId() {
        return this.id;
    }
    
    public void QuestionVo.setId(Long id) {
        this.id = id;
    }
    
    public String QuestionVo.getQuestion() {
        return this.question;
    }
    
    public void QuestionVo.setQuestion(String question) {
        this.question = question;
    }
    
    public QuestionType QuestionVo.getQuestionType() {
        return this.questionType;
    }
    
    public void QuestionVo.setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }
    
    public PartVo QuestionVo.getPart() {
        return this.part;
    }
    
    public void QuestionVo.setPart(PartVo part) {
        this.part = part;
    }
    
    public String QuestionVo.getFirstLabel() {
        return this.firstLabel;
    }
    
    public void QuestionVo.setFirstLabel(String firstLabel) {
        this.firstLabel = firstLabel;
    }
    
    public String QuestionVo.getLastLabel() {
        return this.lastLabel;
    }
    
    public void QuestionVo.setLastLabel(String lastLabel) {
        this.lastLabel = lastLabel;
    }
    
    public Integer QuestionVo.getOrderNo() {
        return this.orderNo;
    }
    
    public void QuestionVo.setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
    
    public Boolean QuestionVo.getRequired() {
        return this.required;
    }
    
    public void QuestionVo.setRequired(Boolean required) {
        this.required = required;
    }
    
    public String QuestionVo.getComment() {
        return this.comment;
    }
    
    public void QuestionVo.setComment(String comment) {
        this.comment = comment;
    }
    
    public String QuestionVo.getDefaultCompletContent() {
        return this.defaultCompletContent;
    }
    
    public void QuestionVo.setDefaultCompletContent(String defaultCompletContent) {
        this.defaultCompletContent = defaultCompletContent;
    }
    
    public String QuestionVo.getCompletContent() {
        return this.completContent;
    }
    
    public void QuestionVo.setCompletContent(String completContent) {
        this.completContent = completContent;
    }
    
    public List<QuestionOptionVo> QuestionVo.getQuestionOptions() {
        return this.questionOptions;
    }
    
    public void QuestionVo.setQuestionOptions(List<QuestionOptionVo> questionOptions) {
        this.questionOptions = questionOptions;
    }
    
}
