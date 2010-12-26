// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kingkey.qn.web;

import com.kingkey.qn.domain.Part;
import com.kingkey.qn.domain.Question;
import com.kingkey.qn.domain.QuestionOption;
import com.kingkey.qn.domain.QuestionType;
import java.io.UnsupportedEncodingException;
import java.lang.Long;
import java.lang.String;
import java.util.Arrays;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect QuestionController_Roo_Controller {
    
    @Autowired
    private GenericConversionService QuestionController.conversionService;
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String QuestionController.show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("question", Question.findQuestion(id));
        model.addAttribute("itemId", id);
        return "questions/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String QuestionController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            model.addAttribute("questions", Question.findQuestionEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Question.countQuestions() / sizeNo;
            model.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            model.addAttribute("questions", Question.findAllQuestions());
        }
        return "questions/list";
    }
    
    @ModelAttribute("parts")
    public Collection<Part> QuestionController.populateParts() {
        return Part.findAllParts();
    }
    
    @ModelAttribute("questionoptions")
    public Collection<QuestionOption> QuestionController.populateQuestionOptions() {
        return QuestionOption.findAllQuestionOptions();
    }
    
    Converter<Part, String> QuestionController.getPartConverter() {
        return new Converter<Part, String>() {
            public String convert(Part part) {
                return new StringBuilder().append(part.getLabel()).append(" ").append(part.getOrderNo()).toString();
            }
        };
    }
    
    Converter<Question, String> QuestionController.getQuestionConverter() {
        return new Converter<Question, String>() {
            public String convert(Question question) {
                return new StringBuilder().append(question.getQuestion()).append(" ").append(question.getFirstLabel()).append(" ").append(question.getLastLabel()).toString();
            }
        };
    }
    
    Converter<QuestionOption, String> QuestionController.getQuestionOptionConverter() {
        return new Converter<QuestionOption, String>() {
            public String convert(QuestionOption questionOption) {
                return new StringBuilder().append(questionOption.getContent()).append(" ").append(questionOption.getOrderNo()).toString();
            }
        };
    }
    
    @PostConstruct
    void QuestionController.registerConverters() {
        conversionService.addConverter(getPartConverter());
        conversionService.addConverter(getQuestionConverter());
        conversionService.addConverter(getQuestionOptionConverter());
    }
    
    private String QuestionController.encodeUrlPathSegment(String pathSegment, HttpServletRequest request) {
        String enc = request.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        }
        catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}