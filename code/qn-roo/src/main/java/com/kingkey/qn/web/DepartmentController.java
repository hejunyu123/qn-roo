package com.kingkey.qn.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.kingkey.qn.domain.Department;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "departments", formBackingObject = Department.class)
@RequestMapping("/departments")
@Controller
public class DepartmentController {
}
