package com.kingkey.qn.vo;

import java.io.Serializable;

import org.springframework.roo.addon.javabean.RooJavaBean;

import com.kingkey.qn.domain.Answer;
import com.kingkey.qn.domain.AnswerSheet;
import com.kingkey.qn.domain.QuestionOption;

@RooJavaBean
public class QuestionOptionVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String content;

    private Integer orderNo;

    private Boolean needCompletion;

    private QuestionVo question;

    /**
     * for update answer
     */
    private Boolean checked;

    /**
     * for stat
     */
    private Integer checkedCount;
    
    private double checkedPercent;
    
    private String checkedPercentStr = "0%";

    public static QuestionOptionVo convert(QuestionVo questionVo, QuestionOption questionOption,
            AnswerSheet answerSheet) {
        QuestionOptionVo qovo = new QuestionOptionVo();
        qovo.setContent(questionOption.getContent());
        qovo.setId(questionOption.getId());
        qovo.setNeedCompletion(questionOption.getNeedCompletion());
        qovo.setOrderNo(questionOption.getOrderNo());
        qovo.setQuestion(questionVo);
        Answer answer = answerSheet.findAnswer(questionOption);
        qovo.setChecked(answer != null);
        return qovo;
    }
}
