package com.kingkey.qn.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kingkey.qn.domain.AnswerSheet;
import com.kingkey.qn.domain.Department;
import com.kingkey.qn.domain.Questionnaire;
import com.kingkey.qn.vo.QuestionnaireVo;

@RooWebScaffold(path = "answersheets", formBackingObject = AnswerSheet.class)
@RequestMapping("/answersheets/**")
@Controller
public class AnswerSheetController {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id, Model model) {

        return AnswerController.doUpdateForm(id, model, "answersheets");
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid AnswerSheet answerSheet, BindingResult result, Model model,
            HttpServletRequest request) {

        return AnswerController.doSaveAnswerSheet(answerSheet, result, model, request,
                "answersheets");
    }

    @RequestMapping(params = "statForm", method = RequestMethod.GET)
    public String statForm(Model model) {
        return "answersheets/stat";
    }

    @RequestMapping(params = "stat", method = RequestMethod.GET)
    public String stat(Model model,
            @RequestParam(value = "questionnaire", required = true) Long questionnaire,
            @RequestParam(value = "department", required = true) Long departmentId) {

        Department department = null;
        if (departmentId != null && departmentId > 0)
            department = Department.findDepartment(departmentId);

        QuestionnaireVo questionnaireVo = QuestionnaireVo.stat(
                Questionnaire.findQuestionnaire(questionnaire), department);

        model.addAttribute("questionnaire", questionnaireVo);

        model.addAttribute("selectedQuestionnaire", questionnaire);
        model.addAttribute("selectedDepartment", departmentId);

        return "answersheets/stat";
    }
}
