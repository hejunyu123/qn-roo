package com.kingkey.qn.domain;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import javax.validation.constraints.NotNull;
import com.kingkey.qn.domain.Questionnaire;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.OrderBy;

import java.util.Set;
import com.kingkey.qn.domain.Question;
import java.util.HashSet;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

@RooJavaBean
@RooToString
@RooEntity
public class Part {

    @NotNull
    private String label;

    @NotNull
    private Integer orderNo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Questionnaire questionnaire;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "part")
    @OrderBy("orderNo")
    private Set<Question> questions = new HashSet<Question>();

    public static long countByQuestionnaire(Long questionnaireId) {
        return entityManager()
                .createQuery("select count(o) from Part o where o.questionnaire = :questionnaire",
                        Long.class)
                .setParameter("questionnaire", Questionnaire.findQuestionnaire(questionnaireId))
                .getSingleResult();
    }
}
