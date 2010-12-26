package com.kingkey.qn.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity
public class Question {

    @NotNull
    private String question;

    @Enumerated
    private QuestionType questionType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Part part;

    private String firstLabel;

    private String lastLabel;

    private Integer orderNo;

    private Boolean required;

    private String comment;

    private String defaultCompletContent;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "question")
    @OrderBy("orderNo")
    private Set<QuestionOption> questionOptions = new HashSet<QuestionOption>();

    public static long countByPart(Long partId) {
        return entityManager()
                .createQuery("select count(o) from Question o where part = :part", Long.class)
                .setParameter("part", Part.findPart(partId)).getSingleResult();
    }
}
