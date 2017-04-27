package com.fii.pcd.controller;

import com.fii.pcd.bean.ClassWithStudentsBean;
import com.fii.pcd.model.Professor;
import com.fii.pcd.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
        Professor professor = new Professor();
        professor.setId(1);
        List<ClassWithStudentsBean> classesOfProffesor = professorService.getClassesForProffesor(professor);
        model.addAttribute("profClasses", classesOfProffesor);
        return "professor";
    }

}
