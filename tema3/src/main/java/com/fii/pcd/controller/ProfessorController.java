package com.fii.pcd.controller;

import com.fii.pcd.bean.ClassWithStudentsBean;
import com.fii.pcd.bean.ProfessorAndSubjectBean;
import com.fii.pcd.security.CustomUserDetails;
import com.fii.pcd.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @RequestMapping(method = GET)
    public String getProfessorsPage(Model model) {

        Integer professorId = getAuthenticatedProfessor().getUserId();
        List<ClassWithStudentsBean> classesOfProffesor = professorService.getClassesForProffesor(professorId);
        model.addAttribute("profClasses", classesOfProffesor);

        ProfessorAndSubjectBean professorAndSubject = professorService.getProfessorNameAndSubjectForId(professorId);
        model.addAttribute("profNameAndSubject", professorAndSubject);
        return "professor";
    }

    private CustomUserDetails getAuthenticatedProfessor() {
        return (CustomUserDetails)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

}
