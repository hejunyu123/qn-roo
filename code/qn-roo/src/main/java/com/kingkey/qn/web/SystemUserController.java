package com.kingkey.qn.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kingkey.qn.domain.Permission;
import com.kingkey.qn.domain.SystemUser;

@RooWebScaffold(path = "systemusers", formBackingObject = SystemUser.class)
@RequestMapping("/systemusers")
@Controller
public class SystemUserController {

    @Autowired
    private transient MessageSource messageSource;

    @ModelAttribute("permissions")
    public Collection<PermissionVo> populatePermissions(HttpServletRequest request) {

        Collection<PermissionVo> list = new ArrayList<PermissionVo>();
        Permission[] permissions = Permission.class.getEnumConstants();
        for (Permission permission : permissions) {
            String message = messageSource.getMessage(
                    "com_kingkey_qn_domain_systemuser_permission_" + permission.name(), null,
                    RequestContextUtils.getLocale(request));
            list.add(new PermissionVo(permission.name(), message));
        }

        return list;
    }

    public static class PermissionVo implements Serializable {

        private static final long serialVersionUID = 1L;

        private String value;
        private String message;

        public PermissionVo(String value, String message) {
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
