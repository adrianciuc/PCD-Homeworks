package com.fii.pcd.service;

import com.fii.pcd.bean.ClassWithStudentsBean;
import com.fii.pcd.bean.ProfessorAndSubjectBean;
import com.fii.pcd.bean.StudentBean;
import com.fii.pcd.model.Classs;
import com.fii.pcd.model.Professor;
import com.fii.pcd.model.Student;
import com.fii.pcd.repository.ProfessorRepository;
import com.fii.pcd.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository, StudentRepository studentRepository) {
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
    }

    public List<ClassWithStudentsBean> getClassesForProffesor(int professorId) {
        Professor p = professorRepository.findOne(professorId);
        List<Classs> classses = p.getClassses();
        List<ClassWithStudentsBean> beans = new ArrayList<>();

        for (Classs classs : classses) {
            ClassWithStudentsBean bean = new ClassWithStudentsBean();
            List<Student> students = studentRepository.findByClasss(classs);
            List<StudentBean> studentBeans = new ArrayList<>();

            for (Student student : students) {

                StudentBean studentToAdd = new StudentBean();
                studentToAdd.setId(student.getId());
                studentToAdd.setName(student.getName());
                studentToAdd.setGrades(student.getGrades());
                studentBeans.add(studentToAdd);

            }
            bean.setName(classs.getName());
            bean.setStudents(studentBeans);

            beans.add(bean);
        }
        return beans;
    }

    public ProfessorAndSubjectBean getProfessorNameAndSubjectForId(int professorId) {
        ProfessorAndSubjectBean professorAndSubject = new ProfessorAndSubjectBean();
        Professor p = professorRepository.findOne(professorId);

        professorAndSubject.setProfessorName(p.getName());
        professorAndSubject.setSubjectName(p.getSubject().getName());
        professorAndSubject.setSubjectId(p.getSubject().getId());

        return professorAndSubject;
    }

}
