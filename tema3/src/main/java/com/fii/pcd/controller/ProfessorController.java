package com.fii.pcd.controller;

import com.fii.pcd.bean.ClassWithStudentsBean;
import com.fii.pcd.bean.ProfessorAndSubjectBean;
import com.fii.pcd.model.Grade;
import com.fii.pcd.model.Student;
import com.fii.pcd.model.Subject;
import com.fii.pcd.security.CustomUserDetails;
import com.fii.pcd.service.ProfessorService;
import com.fii.pcd.service.StudentService;
import com.fii.pcd.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static java.lang.Integer.parseInt;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorService professorService;

    private final StudentService studentService;

    private final SubjectService subjectService;

    @Autowired
    public ProfessorController(ProfessorService professorService, StudentService studentService, SubjectService subjectService) {
        this.professorService = professorService;
        this.studentService = studentService;
        this.subjectService = subjectService;
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

    @RequestMapping(method = POST)
    public String addGradeToStudent(Model model,
                                    @RequestParam("studentGrade") String studentGrade,
                                    @RequestParam("studentId") String studentId,
                                    @RequestParam("subjectId") String subjectId) {

        Student student = studentService.getStudentForStudentId(parseInt(studentId));
        Subject subject = subjectService.getSubjectForSubjectId(parseInt(subjectId));

        Grade grade = new Grade();
        grade.setGrade(studentGrade);
        grade.setSubject(subject);
        grade.setStudent(student);

        studentService.saveGradeForStudentAndSubject(grade);
        return "professor";
    }

}
