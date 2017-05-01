package com.fii.pcd.controller;

import com.fii.pcd.bean.ClassWithStudentsBean;
import com.fii.pcd.bean.ProfessorAndSubjectBean;
import com.fii.pcd.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @RequestMapping(method = GET, params = "profId")
    public String getProfessorsPage(Model model, @RequestParam(value = "profId") int profId) {

        List<ClassWithStudentsBean> classesOfProffesor = professorService.getClassesForProffesor(profId);
        model.addAttribute("profClasses", classesOfProffesor);

        ProfessorAndSubjectBean professorAndSubject = professorService.getProfessorNameAndSubjectForId(profId);
        model.addAttribute("profNameAndSubject", professorAndSubject);
        return "professor";
    }

    @RequestMapping(method = POST)
    public String addGradeToStudent(Model model, @RequestParam("studentGrade") String studentGrade, @RequestParam("subjectId") String subjectId) {
        /*System.out.println(studentGrade + " " + subjectId);*/
        return "professor";
    }

}
