package com.kingkey.qn.web;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kingkey.qn.domain.Answer;
import com.kingkey.qn.domain.AnswerSheet;
import com.kingkey.qn.domain.Department;
import com.kingkey.qn.domain.Part;
import com.kingkey.qn.domain.Permission;
import com.kingkey.qn.domain.Question;
import com.kingkey.qn.domain.QuestionOption;
import com.kingkey.qn.domain.Questionnaire;
import com.kingkey.qn.domain.QuestionnaireType;
import com.kingkey.qn.domain.SystemUser;
import com.kingkey.qn.security.AuthorityService;
import com.kingkey.qn.vo.QuestionnaireVo;

@RequestMapping("/answers")
@Controller
public class AnswerController {

    private static final Logger log = Logger.getLogger(AnswerController.class);

    @Autowired
    private transient AuthorityService authorityService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(HttpServletRequest request,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size, Model model) {

        boolean isAnonymous = authorityService.hasAnyRole("ROLE_ANONYMOUS");

        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            model.addAttribute(
                    "questionnaires",
                    Questionnaire.findQuestionnaireEntries(isAnonymous,
                            page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Questionnaire.countQuestionnaires(isAnonymous) / sizeNo;
            model.addAttribute("maxPages",
                    (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1
                            : nrOfPages));
        } else {
            model.addAttribute("questionnaires", Questionnaire.findAllQuestionnaires(isAnonymous));
        }

        return "answers/listQn";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String createForm(@PathVariable("id") Long id, Model model) {

        Questionnaire questionnaire = Questionnaire.findQuestionnaire(id);

        SystemUser user = authorityService.getLoginUser();

        if (user != null) {
            AnswerSheet answered = AnswerSheet.findByUserAndQuestionnaire(user, questionnaire);
            if (answered != null) {
                log.info(user.getName() + " had answered this questionnaire");
                return "redirect:/answers/" + answered.getId() + "?updateForm";
            }
            model.addAttribute("department", user.getDepartment().getId());
        } else {// 匿名登陆
            if (questionnaire.getQuestionnaireType() == QuestionnaireType.RealNameAnswer) {
                throw new AccessDeniedException(
                        "You are not login, you cannot answer this questionnaire");
            }

        }

        model.addAttribute("questionnaire", questionnaire);
        return "answers/create";
    }

    @RequestMapping(params = "updateForm", value = "/{answerSheetId}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("answerSheetId") Long answerSheetId, Model model,
            HttpSession session) {

        SystemUser loginUser = authorityService.getLoginUser();

        if (loginUser != null) {
            if (loginUser.getPermission() == Permission.RealNameAnswer) {
                AnswerSheet answerSheet = AnswerSheet.findAnswerSheet(answerSheetId);
                SystemUser answeredUser = answerSheet.getAnsweredUser();
                if (answeredUser == null || !answeredUser.getId().equals(loginUser.getId())) {
                    throw new AccessDeniedException("You cannot update other user's answer sheet");
                }
            }
        } else {// 匿名登陆
            Long lastAnsweredId = (Long) session.getAttribute("lastAnsweredId");
            if (!answerSheetId.equals(lastAnsweredId))
                throw new AccessDeniedException("You cannot update other user's answer sheet");
        }

        return doUpdateForm(answerSheetId, model, "answers");
    }

    public static String doUpdateForm(Long answerSheetId, Model model, String namespace) {

        AnswerSheet answerSheet = AnswerSheet.findAnswerSheet(answerSheetId);
        Questionnaire questionnaire = answerSheet.getQuestionnaire();

        QuestionnaireVo qvo = QuestionnaireVo.convert(questionnaire, answerSheet);

        model.addAttribute("questionnaire", qvo);
        model.addAttribute("answerSheet", answerSheet);

        model.addAttribute("namespace", namespace);

        return namespace + "/update";
    }

    /**
     * <pre>
     * 单选:        name="q${question.id}" value="${questionOption.id}"
     * 单选混合:    name="qc${questionOption.id}"
     * 多选:        name="q${question.id}" value="${questionOption.id}"
     * 多选混合:    name="qc${questionOption.id}"
     * 填空                       name="q${question.id}"
     * </pre>
     * 
     * @param result
     * @param model
     * @param redirect
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid AnswerSheet answerSheet, BindingResult result, Model model,
            HttpServletRequest request, HttpSession session,
            @RequestParam(value = "login", required = true) String login,
            @RequestParam(value = "department", required = true) Long departmentId) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError objectError : errors) {
                log.warn("error code: " + objectError.getCode() + "DefaultMessage: "
                        + objectError.getDefaultMessage());
            }
            model.addAttribute("answerSheet", answerSheet);
            return "redirect:/answers/";
        }

        answerSheet.setAnsweredDate(new Date());
        answerSheet.setDepartment(Department.findDepartment(departmentId));
        if (StringUtils.hasText(login))
            answerSheet.setAnsweredUser(SystemUser.findSystemUserByLogin(login));

        answerSheet.persist();

        saveAnswer(answerSheet, result, request);

        log.info("set lastAnsweredId: " + answerSheet.getId() + " to session");
        session.setAttribute("lastAnsweredId", answerSheet.getId());

        return "redirect:/answers/" + answerSheet.getId() + "?updateForm";
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid AnswerSheet answerSheet, BindingResult result, Model model,
            HttpServletRequest request) {

        return doSaveAnswerSheet(answerSheet, result, model, request, "answers", "?updateForm");
    }

    public static String doSaveAnswerSheet(AnswerSheet answerSheet, BindingResult result,
            Model model, HttpServletRequest request, String namespace, String params) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError objectError : errors) {
                log.warn("error code: " + objectError.getCode() + "DefaultMessage: "
                        + objectError.getDefaultMessage());
            }
            model.addAttribute("answerSheet", answerSheet);
            return "redirect:/" + namespace + "/";
        }

        answerSheet.setAnsweredDate(new Date());

        answerSheet.merge();

        // why CANNOT getAnswers from answerSheet
        // Set<Answer> answers = answerSheet.getAnswers();
        List<Answer> answers = Answer.findAllByAnswerSheet(answerSheet);

        log.info("deleting answers: " + answers.size());
        for (Answer answer : answers) {
            log.info("deleting answer: " + answer.getId());
            answer.remove();
        }

        saveAnswer(answerSheet, result, request);

        return "redirect:/" + namespace + "/" + answerSheet.getId() + params;
    }

    private static void saveAnswer(AnswerSheet answerSheet, BindingResult result,
            HttpServletRequest request) {
        Questionnaire questionnaire = answerSheet.getQuestionnaire();

        Set<Part> parts = questionnaire.getParts();
        for (Part part : parts) {
            log.info("answering part: " + part.getLabel());
            Set<Question> questions = part.getQuestions();
            for (Question question : questions) {
                log.info("answering question: " + question.getQuestion() + ", QuestionType: "
                        + question.getQuestionType());

                switch (question.getQuestionType()) {

                /**
                 * <pre>
                 * 单选: name="q${question.id}" value="${questionOption.id}" 
                 * 单选混合: name="qc${questionOption.id}"
                 * </pre>
                 */
                case SingleSelection:
                case SingleSelectionCompletion: {
                    String qoIdStr = request.getParameter("q" + question.getId());

                    log.info("q" + question.getId() + ":" + qoIdStr);

                    if (!StringUtils.hasText(qoIdStr) && question.getRequired()) {
                        log.info(question.getQuestion() + "is required!!");
                        result.addError(new ObjectError(question.getQuestion(), question
                                .getQuestion() + " is required!"));
                        break;
                    }

                    if (StringUtils.hasText(qoIdStr)) {
                        Answer answer = createAnswer(answerSheet, question);
                        QuestionOption questionOption = QuestionOption.findQuestionOption(Long
                                .parseLong(qoIdStr));
                        log.info("SingleSelection: " + questionOption.getContent());
                        answer.setQuestionOption(questionOption);

                        if (questionOption.getNeedCompletion()) {
                            String content = request.getParameter("qc" + questionOption.getId());
                            log.info("NeedCompletion: " + content);
                            answer.setCompletion(content);
                        }

                        answer.persist();
                    }
                    break;
                }

                    /**
                     * <pre>
                     * 多选                 name="q${question.id}" value="${questionOption.id}"
                     * 多选混合        name="qc${questionOption.id}"
                     * </pre>
                     */
                case MultipleChoice:
                case MultipleChoiceCompletion: {

                    String[] qoIdsStr = request.getParameterValues("q" + question.getId());

                    if ((qoIdsStr == null || qoIdsStr.length == 0) && question.getRequired()) {
                        log.info(question.getQuestion() + "is required!!");
                        result.addError(new ObjectError(question.getQuestion(), question
                                .getQuestion() + " is required!"));
                        break;
                    }

                    if (qoIdsStr != null && qoIdsStr.length > 0) {
                        for (String qoIdStr : qoIdsStr) {
                            log.info("q" + question.getId() + ":" + qoIdStr);

                            Answer answer = createAnswer(answerSheet, question);
                            QuestionOption questionOption = QuestionOption.findQuestionOption(Long
                                    .parseLong(qoIdStr));
                            answer.setQuestionOption(questionOption);

                            if (questionOption.getNeedCompletion()) {
                                String content = request
                                        .getParameter("qc" + questionOption.getId());
                                log.info("NeedCompletion: " + content);
                                answer.setCompletion(content);
                            }

                            answer.persist();
                        }
                    }

                    break;
                }

                    /**
                     * <pre>
                     * 填空                name="q${question.id}"
                     * </pre>
                     **/
                case Completion: {
                    String content = request.getParameter("q" + question.getId());

                    Answer answer = createAnswer(answerSheet, question);
                    answer.setCompletion(content);
                    answer.persist();
                    break;
                }

                default:
                    break;
                }
            }
        }
    }

    private static Answer createAnswer(AnswerSheet answerSheet, Question question) {
        Answer answer = new Answer();
        answer.setAnswerSheet(answerSheet);
        answer.setQuestion(question);
        return answer;
    }

    @ModelAttribute("departments")
    public Collection<Department> populateDepartments() {
        return Department.findAllDepartments();
    }

}
