// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kingkey.qn.domain;

import com.kingkey.qn.domain.Answer;
import com.kingkey.qn.domain.Department;
import com.kingkey.qn.domain.Questionnaire;
import com.kingkey.qn.domain.SystemUser;
import java.util.Date;
import java.util.Set;

privileged aspect AnswerSheet_Roo_JavaBean {
    
    public Questionnaire AnswerSheet.getQuestionnaire() {
        return this.questionnaire;
    }
    
    public void AnswerSheet.setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }
    
    public Department AnswerSheet.getDepartment() {
        return this.department;
    }
    
    public void AnswerSheet.setDepartment(Department department) {
        this.department = department;
    }
    
    public SystemUser AnswerSheet.getAnsweredUser() {
        return this.answeredUser;
    }
    
    public void AnswerSheet.setAnsweredUser(SystemUser answeredUser) {
        this.answeredUser = answeredUser;
    }
    
    public Date AnswerSheet.getAnsweredDate() {
        return this.answeredDate;
    }
    
    public void AnswerSheet.setAnsweredDate(Date answeredDate) {
        this.answeredDate = answeredDate;
    }
    
    public Set<Answer> AnswerSheet.getAnswers() {
        return this.answers;
    }
    
    public void AnswerSheet.setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }
    
}