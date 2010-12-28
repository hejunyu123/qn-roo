package com.kingkey.qn.domain;

import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity
public class Answer {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private AnswerSheet answerSheet;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    private QuestionOption questionOption;

    private String completion;

    public static List<Answer> findAllByAnswerSheet(AnswerSheet answerSheet) {

        List<Answer> list = entityManager()
                .createQuery("select o from Answer o where o.answerSheet = :answerSheet",
                        Answer.class).setParameter("answerSheet", answerSheet).getResultList();
        return list;
    }

    public static Long countByDepartmentAndQuestionOption(Department department,
            QuestionOption questionOption) {

        String hql = "select count(o) from Answer o where o.questionOption = :questionOption";
        if (department != null)
            hql += " and o.answerSheet.department = :department";

        TypedQuery<Long> query = entityManager().createQuery(hql, Long.class);
        query.setParameter("questionOption", questionOption);

        if (department != null)
            query.setParameter("department", department);

        Long count = query.getSingleResult();

        return count;
    }
}
