// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kingkey.qn.domain;

import com.kingkey.qn.domain.QuestionOption;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;

privileged aspect QuestionOptionDataOnDemand_Roo_DataOnDemand {
    
    declare @type: QuestionOptionDataOnDemand: @Component;
    
    private Random QuestionOptionDataOnDemand.rnd = new java.security.SecureRandom();
    
    private List<QuestionOption> QuestionOptionDataOnDemand.data;
    
    public QuestionOption QuestionOptionDataOnDemand.getNewTransientQuestionOption(int index) {
        com.kingkey.qn.domain.QuestionOption obj = new com.kingkey.qn.domain.QuestionOption();
        obj.setContent("content_" + index);
        obj.setNeedCompletion(Boolean.TRUE);
        obj.setOrderNo(new Integer(index));
        obj.setQuestion(null);
        return obj;
    }
    
    public QuestionOption QuestionOptionDataOnDemand.getSpecificQuestionOption(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        QuestionOption obj = data.get(index);
        return QuestionOption.findQuestionOption(obj.getId());
    }
    
    public QuestionOption QuestionOptionDataOnDemand.getRandomQuestionOption() {
        init();
        QuestionOption obj = data.get(rnd.nextInt(data.size()));
        return QuestionOption.findQuestionOption(obj.getId());
    }
    
    public boolean QuestionOptionDataOnDemand.modifyQuestionOption(QuestionOption obj) {
        return false;
    }
    
    public void QuestionOptionDataOnDemand.init() {
        data = com.kingkey.qn.domain.QuestionOption.findQuestionOptionEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'QuestionOption' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new java.util.ArrayList<com.kingkey.qn.domain.QuestionOption>();
        for (int i = 0; i < 10; i++) {
            com.kingkey.qn.domain.QuestionOption obj = getNewTransientQuestionOption(i);
            obj.persist();
            obj.flush();
            data.add(obj);
        }
    }
    
}
