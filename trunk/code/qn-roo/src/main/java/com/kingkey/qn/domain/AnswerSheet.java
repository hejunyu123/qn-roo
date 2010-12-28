package com.kingkey.qn.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findAnswerSheetsByQuestionnaireAndAnsweredUser" })
public class AnswerSheet {

    @ManyToOne(fetch = FetchType.LAZY)
    private Questionnaire questionnaire;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    private SystemUser answeredUser;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "SS")
    private Date answeredDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "answerSheet")
    private Set<Answer> answers = new HashSet<Answer>();

    public Answer findAnswer(QuestionOption questionOption) {
        List<Answer> list = entityManager()
                .createQuery(
                        "select o from Answer o where o.answerSheet = :answerSheet "
                                + "and o.questionOption = :questionOption", Answer.class)
                .setParameter("answerSheet", this).setParameter("questionOption", questionOption)
                .getResultList();
        if (list.isEmpty())
            return null;
        return list.get(0);
    }

    public Answer findAnswer(Question question) {
        List<Answer> list = entityManager()
                .createQuery(
                        "select o from Answer o where o.answerSheet = :answerSheet "
                                + "and o.question = :question", Answer.class)
                .setParameter("answerSheet", this).setParameter("question", question)
                .getResultList();
        if (list.isEmpty())
            return null;
        return list.get(0);
    }

    public static AnswerSheet findByUserAndQuestionnaire(SystemUser user,
            Questionnaire questionnaire) {
        List<AnswerSheet> list = entityManager()
                .createQuery(
                        "select o from AnswerSheet o where o.answeredUser = :answeredUser "
                                + "and o.questionnaire = :questionnaire", AnswerSheet.class)
                .setParameter("answeredUser", user).setParameter("questionnaire", questionnaire)
                .getResultList();
        if (list.isEmpty())
            return null;
        return list.get(0);
    }

    public static Long countByQuestionnaireAndDepartment(Questionnaire questionnaire,
            Department department) {

        String hql = "select count(o) from AnswerSheet o where o.questionnaire = :questionnaire";
        if (department != null)
            hql += " and o.department = :department";

        TypedQuery<Long> query = entityManager().createQuery(hql, Long.class);

        query.setParameter("questionnaire", questionnaire);

        if (department != null)
            query.setParameter("department", department);

        Long count = query.getSingleResult();

        return count;
    }
}
