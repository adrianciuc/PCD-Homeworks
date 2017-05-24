package com.fii.pcd.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/enter")
public class LoginSuccessController {

    static Map<String, String> userPages;
    static {
        userPages = new HashMap<>();
        userPages.put("ROLE_PROF", "redirect:/professor");
        userPages.put("ROLE_STUD", "redirect:/student");
        userPages.put("ROLE_ADM", "redirect:/admin");
    }

    @RequestMapping(method = GET)
    public String getUserPage() {
        for( GrantedAuthority authority : getAuthenticatedUserRoles()) {
            return userPages.get(authority.getAuthority());
        }
        return "unauthorized";
    }

    private Collection<? extends GrantedAuthority> getAuthenticatedUserRoles() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }
}
