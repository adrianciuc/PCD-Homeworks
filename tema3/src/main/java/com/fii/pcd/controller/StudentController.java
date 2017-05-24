package com.fii.pcd.controller;

import com.fii.pcd.bean.StudentAndClassBean;
import com.fii.pcd.bean.StudentClassSubjectGradeBean;
import com.fii.pcd.security.CustomUserDetails;
import com.fii.pcd.service.StudentService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(method = GET)
    public String getStudentPage(Model model) {
        Integer studentId = getAuthenticatedStudent().getUserId();
        List<StudentClassSubjectGradeBean> studentsClassSubjGrade = studentService.getClassDisciplineGradeForStudent(studentId);

        StudentAndClassBean studentAndClass = studentService.getStudentNameAndClassForId(studentId);
        model.addAttribute("studentAndClass", studentAndClass);
        model.addAttribute("students", studentsClassSubjGrade);
        return "student";
    }

    private CustomUserDetails getAuthenticatedStudent() {
        return (CustomUserDetails)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
