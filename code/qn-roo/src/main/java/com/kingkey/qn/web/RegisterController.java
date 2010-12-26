package com.kingkey.qn.web;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kingkey.qn.domain.Company;
import com.kingkey.qn.domain.Department;
import com.kingkey.qn.domain.SystemUser;

@RequestMapping("/register")
@Controller
public class RegisterController {

    private static final Logger log = Logger.getLogger(RegisterController.class);

    @Autowired
    protected AuthenticationManager authenticationManager;

    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute("systemUser", new SystemUser());
        return "register/create";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid SystemUser systemUser, BindingResult result, Model model,
            HttpServletRequest request) {
        if (result.hasErrors()) {
            log.info("" + result.toString());
            model.addAttribute("systemUser", systemUser);
            return "register/create";
        }
        systemUser.persist();
        
        authenticateUserAndSetSession(systemUser, request);
        
        return "redirect:/answers";
    }

    private void authenticateUserAndSetSession(SystemUser user, HttpServletRequest request) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                user.getLogin(), user.getPassword());

        // generate session if one doesn't exist
        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }

    @ModelAttribute("companys")
    public Collection<Company> populateCompanys() {
        return Company.findAllCompanys();
    }

    @ModelAttribute("departments")
    public Collection<Department> populateDepartments() {
        return Department.findAllDepartments();
    }
}
