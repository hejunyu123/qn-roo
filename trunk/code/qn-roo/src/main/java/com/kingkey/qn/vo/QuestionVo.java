package com.kingkey.qn.vo;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.roo.addon.javabean.RooJavaBean;

import com.kingkey.qn.domain.Answer;
import com.kingkey.qn.domain.AnswerSheet;
import com.kingkey.qn.domain.Department;
import com.kingkey.qn.domain.Question;
import com.kingkey.qn.domain.QuestionOption;
import com.kingkey.qn.domain.QuestionType;

@RooJavaBean
public class QuestionVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final DecimalFormat decimalFormat = new DecimalFormat("###.##%");

    private Long id;

    private String question;

    private QuestionType questionType;

    private PartVo part;

    private String firstLabel;

    private String lastLabel;

    private Integer orderNo;

    private Boolean required;

    private String comment;

    private String defaultCompletContent;

    /**
     * 填空, 单选填空 或 多选填空 的内容
     */
    private String completContent;

    private List<QuestionOptionVo> questionOptions = new ArrayList<QuestionOptionVo>();

    public static QuestionVo convert(PartVo partVo, Question question, AnswerSheet answerSheet) {
        QuestionVo qvo = new QuestionVo();
        qvo.setComment(question.getComment());
        qvo.setDefaultCompletContent(question.getDefaultCompletContent());
        qvo.setFirstLabel(question.getFirstLabel());
        qvo.setId(question.getId());
        qvo.setLastLabel(question.getLastLabel());
        qvo.setOrderNo(question.getOrderNo());
        qvo.setPart(partVo);
        qvo.setQuestion(question.getQuestion());
        qvo.setQuestionType(question.getQuestionType());
        qvo.setRequired(question.getRequired());

        if (question.getQuestionType() == QuestionType.Completion) {// 填空
            Answer answer = answerSheet.findAnswer(question);
            if (answer != null)
                qvo.setCompletContent(answer.getCompletion());
        } else {
            Set<QuestionOption> questionOptions = question.getQuestionOptions();
            for (QuestionOption questionOption : questionOptions) {

                QuestionOptionVo qovo = QuestionOptionVo.convert(qvo, questionOption, answerSheet);
                qvo.getQuestionOptions().add(qovo);

                if (questionOption.getNeedCompletion()) {// 单选填空 或 多选填空
                    Answer answer = answerSheet.findAnswer(questionOption);
                    if (answer != null)
                        qvo.setCompletContent(answer.getCompletion());
                }
            }
        }

        return qvo;
    }

    public static QuestionVo stat(PartVo partVo, Question question, Department department) {
        QuestionVo qvo = new QuestionVo();
        qvo.setComment(question.getComment());
        qvo.setDefaultCompletContent(question.getDefaultCompletContent());
        qvo.setFirstLabel(question.getFirstLabel());
        qvo.setId(question.getId());
        qvo.setLastLabel(question.getLastLabel());
        qvo.setOrderNo(question.getOrderNo());
        qvo.setPart(partVo);
        qvo.setQuestion(question.getQuestion());
        qvo.setQuestionType(question.getQuestionType());
        qvo.setRequired(question.getRequired());

        if (question.getQuestionType() != QuestionType.Completion) {
            Set<QuestionOption> questionOptions = question.getQuestionOptions();
            for (QuestionOption questionOption : questionOptions) {

                Long checkedCount = Answer.countByDepartmentAndQuestionOption(department,
                        questionOption);

                QuestionOptionVo qovo = new QuestionOptionVo();
                qovo.setContent(questionOption.getContent());
                qovo.setId(questionOption.getId());
                qovo.setNeedCompletion(questionOption.getNeedCompletion());
                qovo.setOrderNo(questionOption.getOrderNo());
                qovo.setQuestion(qvo);
                qovo.setCheckedCount(checkedCount.intValue());
                double d = checkedCount.doubleValue()
                        / partVo.getQuestionnaire().getAnsweredCount();
                qovo.setCheckedPercent(d * 100);
                qovo.setCheckedPercentStr(decimalFormat.format(d));

                qvo.getQuestionOptions().add(qovo);
            }
        }

        return qvo;
    }

    public static void main(String[] args) {
        System.out.println(decimalFormat.format(61.904761904761905));
    }
}
