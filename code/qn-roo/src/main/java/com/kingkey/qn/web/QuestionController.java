package com.kingkey.qn.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kingkey.qn.domain.Question;
import com.kingkey.qn.domain.QuestionType;

@RooWebScaffold(path = "questions", formBackingObject = Question.class)
@RequestMapping("/questions")
@Controller
public class QuestionController {

    @Autowired
    private transient MessageSource messageSource;

    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(Model model,
            @RequestParam(value = "redirect", required = false) String redirect,
            @RequestParam(value = "part", required = false) Long part) {
        Question question = new Question();
        model.addAttribute("question", question);

        if (StringUtils.hasText(redirect))
            model.addAttribute("redirect", redirect);

        if (part != null) {
            model.addAttribute("part", part);
            question.setOrderNo((int) (Question.countByPart(part) + 1));
        }

        return "questions/create";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid Question question, BindingResult result, Model model,
            @RequestParam(value = "redirect", required = false) String redirect) {

        if (result.hasErrors()) {
            model.addAttribute("question", question);
            model.addAttribute("part", question.getPart().getId());
            return "questions/create";
        }
        question.persist();

        if (StringUtils.hasText(redirect))
            return "redirect:" + redirect;
        else
            return "redirect:/questions/" + question.getId().toString();
    }

    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model,
            @RequestParam(value = "redirect", required = false) String redirect,
            @RequestParam(value = "part", required = false) Long part) {

        model.addAttribute("question", Question.findQuestion(id));

        if (StringUtils.hasText(redirect))
            model.addAttribute("redirect", redirect);

        if (part != null)
            model.addAttribute("part", part);

        return "questions/update";
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid Question question, BindingResult result, Model model,
            @RequestParam(value = "redirect", required = false) String redirect) {

        if (result.hasErrors()) {
            model.addAttribute("question", question);
            return "questions/update";
        }
        question.merge();

        if (StringUtils.hasText(redirect))
            return "redirect:" + redirect;
        else
            return "redirect:/questions/" + question.getId().toString();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size, Model model,
            @RequestParam(value = "redirect", required = false) String redirect) {

        Question.findQuestion(id).remove();

        if (StringUtils.hasText(redirect))
            return "redirect:" + redirect;
        else {
            model.addAttribute("page", (page == null) ? "1" : page.toString());
            model.addAttribute("size", (size == null) ? "10" : size.toString());
            return "redirect:/questions?page=" + ((page == null) ? "1" : page.toString())
                    + "&size=" + ((size == null) ? "10" : size.toString());
        }
    }

    @ModelAttribute("questiontypes")
    public Collection<QuestionTypeVo> populateQuestionTypes(HttpServletRequest request) {

        Collection<QuestionTypeVo> list = new ArrayList<QuestionTypeVo>();
        QuestionType[] questionTypes = QuestionType.class.getEnumConstants();
        for (QuestionType questionType : questionTypes) {
            String message = messageSource.getMessage("com_kingkey_qn_domain_question_questiontype_" + questionType.name(), null,
                    RequestContextUtils.getLocale(request));
            list.add(new QuestionTypeVo(questionType.name(), message));
        }

        return list;
    }

    public static class QuestionTypeVo implements Serializable {

        private static final long serialVersionUID = 1L;

        private String value;
        private String message;

        public QuestionTypeVo(String value, String message) {
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
