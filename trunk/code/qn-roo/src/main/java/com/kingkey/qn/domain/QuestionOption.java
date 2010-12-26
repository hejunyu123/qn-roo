package com.kingkey.qn.domain;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity
public class QuestionOption {

    @NotNull
    private String content;

    private Integer orderNo;

    private Boolean needCompletion;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    public static long countByQuestion(Long questionId) {
        return entityManager()
                .createQuery("select count(o) from QuestionOption o where question = :question",
                        Long.class).setParameter("question", Question.findQuestion(questionId))
                .getSingleResult();
    }
}
