package com.fii.pcd.service;


import com.fii.pcd.bean.StudentClassSubjectGradeBean;
import com.fii.pcd.model.Grade;
import com.fii.pcd.model.Student;
import com.fii.pcd.model.Subject;
import com.fii.pcd.repository.GradeRepository;
import com.fii.pcd.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    private GradeRepository gradeRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, GradeRepository gradeRepository) {
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
    }

    public List<StudentClassSubjectGradeBean> getClassDisciplineGradeForStudent(int studentId) {
        Student student = studentRepository.findOne(studentId);
        List<Subject> subjects = getSubjectsForStudent(student);
        List<StudentClassSubjectGradeBean> beans = new ArrayList<>();

        for (Subject subject : subjects) {
            List<Grade> gradesForThatSubject = getGradesForStudentAtSubject(student, subject);

            for(Grade grade: gradesForThatSubject) {
                StudentClassSubjectGradeBean bean = new StudentClassSubjectGradeBean();
                bean.setName(student.getName());
                bean.setClassName(student.getClasss().getName());
                bean.setSubjectName(subject.getName());
                bean.setGrade(grade.getGrade());
                beans.add(bean);
            }
        }
        return beans;

    }

    public Student getStudentForStudentId(int studentId) {
        return studentRepository.findOne(studentId);
    }

    public void saveGradeForStudentAndSubject(Grade grade) {
        gradeRepository.save(grade);
    }

    public List<Subject> getSubjectsForStudent(Student student) {
        return student.getSubjects();
    }

    public List<Grade> getGradesForStudentAtSubject(Student student, Subject subject) {
        List<Grade> selectedGrades = new ArrayList<>();
        List<Grade> grades = gradeRepository.findAll();

        for (Grade g : grades) {
            if (g.getStudent().getId() == student.getId() && g.getSubject().getId() == subject.getId()) {
                selectedGrades.add(g);
            }
        }
        return selectedGrades;
    }
}
