package com.kingkey.qn.web;

import javax.validation.Valid;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kingkey.qn.domain.QuestionOption;

@RooWebScaffold(path = "questionoptions", formBackingObject = QuestionOption.class)
@RequestMapping("/questionoptions")
@Controller
public class QuestionOptionController {

    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(Model model,
            @RequestParam(value = "redirect", required = false) String redirect,
            @RequestParam(value = "question", required = false) Long question) {

        QuestionOption questionOption = new QuestionOption();
        model.addAttribute("questionOption", questionOption);

        if (StringUtils.hasText(redirect))
            model.addAttribute("redirect", redirect);

        if (question != null) {
            model.addAttribute("question", question);
            questionOption.setOrderNo((int) (QuestionOption.countByQuestion(question) + 1));
        }

        return "questionoptions/create";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid QuestionOption questionOption, BindingResult result, Model model,
            @RequestParam(value = "redirect", required = false) String redirect) {

        if (result.hasErrors()) {
            model.addAttribute("questionOption", questionOption);
            model.addAttribute("question", questionOption.getQuestion().getId());
            return "questionoptions/create";
        }
        questionOption.persist();

        if (StringUtils.hasText(redirect))
            return "redirect:" + redirect;
        else
            return "redirect:/questionoptions/" + questionOption.getId().toString();
    }

    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model,
            @RequestParam(value = "redirect", required = false) String redirect,
            @RequestParam(value = "question", required = false) Long question) {

        model.addAttribute("questionOption", QuestionOption.findQuestionOption(id));

        if (StringUtils.hasText(redirect))
            model.addAttribute("redirect", redirect);

        if (question != null)
            model.addAttribute("question", question);

        return "questionoptions/update";
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid QuestionOption questionOption, BindingResult result, Model model,
            @RequestParam(value = "redirect", required = false) String redirect) {

        if (result.hasErrors()) {
            model.addAttribute("questionOption", questionOption);
            return "questionoptions/update";
        }
        questionOption.merge();

        if (StringUtils.hasText(redirect))
            return "redirect:" + redirect;
        else
            return "redirect:/questionoptions/" + questionOption.getId().toString();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size, Model model,
            @RequestParam(value = "redirect", required = false) String redirect) {

        QuestionOption.findQuestionOption(id).remove();

        if (StringUtils.hasText(redirect))
            return "redirect:" + redirect;
        else {
            model.addAttribute("page", (page == null) ? "1" : page.toString());
            model.addAttribute("size", (size == null) ? "10" : size.toString());
            return "redirect:/questionoptions?page=" + ((page == null) ? "1" : page.toString())
                    + "&size=" + ((size == null) ? "10" : size.toString());
        }
    }
}
