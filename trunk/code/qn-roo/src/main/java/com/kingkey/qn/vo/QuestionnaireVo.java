package com.kingkey.qn.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.roo.addon.javabean.RooJavaBean;

import com.kingkey.qn.domain.AnswerSheet;
import com.kingkey.qn.domain.Department;
import com.kingkey.qn.domain.Part;
import com.kingkey.qn.domain.Questionnaire;
import com.kingkey.qn.domain.QuestionnaireType;

@RooJavaBean
public class QuestionnaireVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String subject;

    private String description;

    private Boolean displayPartLabel;

    private QuestionnaireType questionnaireType;

    private List<PartVo> parts = new ArrayList<PartVo>();

    private int answeredCount;

    public static QuestionnaireVo convert(Questionnaire questionnaire, AnswerSheet answerSheet) {
        QuestionnaireVo qvo = new QuestionnaireVo();
        qvo.setDescription(questionnaire.getDescription());
        qvo.setDisplayPartLabel(questionnaire.getDisplayPartLabel());
        qvo.setId(questionnaire.getId());
        qvo.setQuestionnaireType(questionnaire.getQuestionnaireType());
        qvo.setSubject(questionnaire.getSubject());

        Set<Part> parts = questionnaire.getParts();
        for (Part part : parts) {
            PartVo pvo = PartVo.convert(qvo, part, answerSheet);
            qvo.getParts().add(pvo);
        }

        return qvo;
    }

    public static QuestionnaireVo stat(Questionnaire questionnaire, Department department) {
        QuestionnaireVo qvo = new QuestionnaireVo();
        qvo.setDescription(questionnaire.getDescription());
        qvo.setDisplayPartLabel(questionnaire.getDisplayPartLabel());
        qvo.setId(questionnaire.getId());
        qvo.setQuestionnaireType(questionnaire.getQuestionnaireType());
        qvo.setSubject(questionnaire.getSubject());

        Long count = AnswerSheet.countByQuestionnaireAndDepartment(questionnaire, department);
        qvo.setAnsweredCount(count.intValue());

        Set<Part> parts = questionnaire.getParts();
        for (Part part : parts) {
            PartVo pvo = PartVo.stat(qvo, part, department);
            qvo.getParts().add(pvo);
        }

        return qvo;
    }
}
