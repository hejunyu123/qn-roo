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

import com.kingkey.qn.domain.Part;

@RooWebScaffold(path = "parts", formBackingObject = Part.class)
@RequestMapping("/parts")
@Controller
public class PartController {

    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(Model model,
            @RequestParam(value = "redirect", required = false) String redirect,
            @RequestParam(value = "questionnaire", required = false) Long questionnaire) {

        Part part = new Part();
        model.addAttribute("part", part);

        if (StringUtils.hasText(redirect))
            model.addAttribute("redirect", redirect);

        if (questionnaire != null) {
            model.addAttribute("questionnaire", questionnaire);
            Long c = Part.countByQuestionnaire(questionnaire);
            part.setOrderNo((int) (c + 1));
        }

        return "parts/create";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid Part part, BindingResult result, Model model,
            @RequestParam(value = "redirect", required = false) String redirect) {

        if (result.hasErrors()) {
            model.addAttribute("part", part);
            model.addAttribute("questionnaire", part.getQuestionnaire().getId());
            return "parts/create";
        }
        part.persist();

        if (StringUtils.hasText(redirect))
            return "redirect:" + redirect;
        else
            return "redirect:/parts/" + part.getId().toString();
    }

    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model,
            @RequestParam(value = "redirect", required = false) String redirect,
            @RequestParam(value = "questionnaire", required = false) Long questionnaire) {

        model.addAttribute("part", Part.findPart(id));

        if (StringUtils.hasText(redirect))
            model.addAttribute("redirect", redirect);

        if (questionnaire != null)
            model.addAttribute("questionnaire", questionnaire);

        return "parts/update";
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid Part part, BindingResult result, Model model,
            @RequestParam(value = "redirect", required = false) String redirect) {

        if (result.hasErrors()) {
            model.addAttribute("part", part);
            return "parts/update";
        }
        part.merge();

        if (StringUtils.hasText(redirect))
            return "redirect:" + redirect;
        else
            return "redirect:/parts/" + part.getId().toString();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size, Model model,
            @RequestParam(value = "redirect", required = false) String redirect) {

        Part.findPart(id).remove();

        if (StringUtils.hasText(redirect))
            return "redirect:" + redirect;
        else {
            model.addAttribute("page", (page == null) ? "1" : page.toString());
            model.addAttribute("size", (size == null) ? "10" : size.toString());
            return "redirect:/parts?page=" + ((page == null) ? "1" : page.toString()) + "&size="
                    + ((size == null) ? "10" : size.toString());
        }
    }
}
