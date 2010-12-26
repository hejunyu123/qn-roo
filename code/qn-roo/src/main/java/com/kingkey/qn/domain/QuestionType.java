package com.kingkey.qn.domain;

public enum QuestionType {

    /**
     * 单选
     */
    SingleSelection,

    /**
     * 多选
     */
    MultipleChoice,

    /**
     * 填空
     */
    Completion,

    /**
     * 单选混合,某个答案可以附带填空说明
     */
    SingleSelectionCompletion,

    /**
     * 多选混合,某个答案可以附带填空说明
     */
    MultipleChoiceCompletion;
}
