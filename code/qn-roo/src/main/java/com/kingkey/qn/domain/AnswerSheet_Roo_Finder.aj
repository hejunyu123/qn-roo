// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kingkey.qn.domain;

import com.kingkey.qn.domain.AnswerSheet;
import com.kingkey.qn.domain.Questionnaire;
import com.kingkey.qn.domain.SystemUser;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect AnswerSheet_Roo_Finder {
    
    public static TypedQuery<AnswerSheet> AnswerSheet.findAnswerSheetsByQuestionnaireAndAnsweredUser(Questionnaire questionnaire, SystemUser answeredUser) {
        if (questionnaire == null) throw new IllegalArgumentException("The questionnaire argument is required");
        if (answeredUser == null) throw new IllegalArgumentException("The answeredUser argument is required");
        EntityManager em = AnswerSheet.entityManager();
        TypedQuery<AnswerSheet> q = em.createQuery("SELECT AnswerSheet FROM AnswerSheet AS answersheet WHERE answersheet.questionnaire = :questionnaire AND answersheet.answeredUser = :answeredUser", AnswerSheet.class);
        q.setParameter("questionnaire", questionnaire);
        q.setParameter("answeredUser", answeredUser);
        return q;
    }
    
}
