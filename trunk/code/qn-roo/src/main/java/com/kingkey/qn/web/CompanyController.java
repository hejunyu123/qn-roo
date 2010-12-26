package com.kingkey.qn.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kingkey.qn.domain.Company;

@RooWebScaffold(path = "companys", formBackingObject = Company.class)
@RequestMapping("/companys")
@Controller
public class CompanyController {
}
