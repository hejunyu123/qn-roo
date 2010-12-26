package com.kingkey.qn.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString(toStringMethod = "toRooString")
@RooEntity
public class Questionnaire {

    @NotNull
    @Column(unique = true)
    private String subject;

    private String description;

    private Boolean displayPartLabel;

    @NotNull
    @Enumerated
    private QuestionnaireType questionnaireType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    private SystemUser createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "SS")
    private Date createdDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "questionnaire")
    @OrderBy("orderNo")
    private Set<Part> parts = new HashSet<Part>();

    @Override
    public String toString() {
        return subject;
    }

    public static List<Questionnaire> findQuestionnaireEntries(boolean onlyAnonymousAnswer,
            int firstResult, int maxResults) {
        String hql = "select o from Questionnaire o";

        if (onlyAnonymousAnswer)
            hql += " where o.questionnaireType = :questionnaireType";

        TypedQuery<Questionnaire> query = entityManager().createQuery(hql, Questionnaire.class);

        if (onlyAnonymousAnswer)
            query.setParameter("questionnaireType", QuestionnaireType.AnonymousAnswer);

        return query.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static long countQuestionnaires(boolean onlyAnonymousAnswer) {
        String hql = "select count(o) from Questionnaire o";

        if (onlyAnonymousAnswer)
            hql += " where o.questionnaireType = :questionnaireType";

        TypedQuery<Long> query = entityManager().createQuery(hql, Long.class);

        if (onlyAnonymousAnswer)
            query.setParameter("questionnaireType", QuestionnaireType.AnonymousAnswer);

        return query.getSingleResult();
    }

    public static List<Questionnaire> findAllQuestionnaires(boolean onlyAnonymousAnswer) {
        String hql = "select o from Questionnaire o";

        if (onlyAnonymousAnswer)
            hql += " where o.questionnaireType = :questionnaireType";

        TypedQuery<Questionnaire> query = entityManager().createQuery(hql, Questionnaire.class);

        if (onlyAnonymousAnswer)
            query.setParameter("questionnaireType", QuestionnaireType.AnonymousAnswer);

        return query.getResultList();
    }
}
