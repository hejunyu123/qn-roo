package com.kingkey.qn.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.roo.addon.beaninfo.RooBeanInfo;
import org.springframework.roo.addon.javabean.RooJavaBean;

import com.kingkey.qn.domain.AnswerSheet;
import com.kingkey.qn.domain.Department;
import com.kingkey.qn.domain.Part;
import com.kingkey.qn.domain.Question;
import com.kingkey.qn.domain.QuestionType;

@RooJavaBean
@RooBeanInfo
public class PartVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String label;

    private Integer orderNo;

    private QuestionnaireVo questionnaire;

    private List<QuestionVo> questions = new ArrayList<QuestionVo>();

    public static PartVo convert(QuestionnaireVo questionnaire, Part part, AnswerSheet answerSheet) {
        PartVo pvo = new PartVo();
        pvo.setId(part.getId());
        pvo.setLabel(part.getLabel());
        pvo.setOrderNo(part.getOrderNo());
        pvo.setQuestionnaire(questionnaire);
        Set<Question> questions = part.getQuestions();
        for (Question question : questions) {
            QuestionVo qvo = QuestionVo.convert(pvo, question, answerSheet);
            pvo.getQuestions().add(qvo);
        }
        return pvo;
    }

    public static PartVo stat(QuestionnaireVo questionnaire, Part part, Department department) {
        PartVo pvo = new PartVo();
        pvo.setId(part.getId());
        pvo.setLabel(part.getLabel());
        pvo.setOrderNo(part.getOrderNo());
        pvo.setQuestionnaire(questionnaire);
        Set<Question> questions = part.getQuestions();
        for (Question question : questions) {
            if (question.getQuestionType() != QuestionType.Completion) {
                QuestionVo qvo = QuestionVo.stat(pvo, question, department);
                pvo.getQuestions().add(qvo);
            }
        }
        return pvo;
    }
}
