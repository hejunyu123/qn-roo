package com.kingkey.qn.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kingkey.qn.domain.Questionnaire;
import com.kingkey.qn.domain.QuestionnaireType;
import com.kingkey.qn.domain.SystemUser;

@RooWebScaffold(path = "questionnaires", formBackingObject = Questionnaire.class)
@RequestMapping("/questionnaires")
@Controller
public class QuestionnaireController {

    @Autowired
    private transient MessageSource messageSource;

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid Questionnaire questionnaire, BindingResult result, Model model,
            HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("questionnaire", questionnaire);
            addDateTimeFormatPatterns(model);
            return "questionnaires/create";
        }
        questionnaire.setCreatedDate(new Date());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        questionnaire.setCreatedBy(SystemUser.findSystemUserByLogin(login));

        questionnaire.persist();
        return "redirect:/questionnaires/" + questionnaire.getId().toString();
    }

    @ModelAttribute("questionnairetypes")
    public Collection<QuestionnaireTypeVo> populateQuestionnaireTypes(HttpServletRequest request) {

        Collection<QuestionnaireTypeVo> list = new ArrayList<QuestionnaireTypeVo>();
        QuestionnaireType[] questionnaireTypes = QuestionnaireType.class.getEnumConstants();
        for (QuestionnaireType questionnaireType : questionnaireTypes) {
            String message = messageSource.getMessage(
                    "com_kingkey_qn_domain_questionnaire_questionnairetype_"
                            + questionnaireType.name(), null,
                    RequestContextUtils.getLocale(request));
            list.add(new QuestionnaireTypeVo(questionnaireType.name(), message));
        }

        return list;
    }

    public static class QuestionnaireTypeVo implements Serializable {

        private static final long serialVersionUID = 1L;

        private String value;
        private String message;

        public QuestionnaireTypeVo(String value, String message) {
            this.value = value;
            this.message = message;
        }

        @Override
        public String toString() {
            return message;
        }

        public String getValue() {
            return value;
        }
    }
}
