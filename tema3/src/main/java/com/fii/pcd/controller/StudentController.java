package com.fii.pcd.controller;

import com.fii.pcd.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/student")
public class StudentController {

    @RequestMapping(method = GET)
    public String getStudentPage() {
        Integer studentId = getAuthenticatedStudent().getUserId();
        return "student";
    }

    private CustomUserDetails getAuthenticatedStudent() {
        return (CustomUserDetails)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
