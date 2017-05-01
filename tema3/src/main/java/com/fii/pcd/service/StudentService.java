package com.fii.pcd.service;

import com.fii.pcd.model.Grade;
import com.fii.pcd.model.Student;
import com.fii.pcd.repository.GradeRepository;
import com.fii.pcd.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    private GradeRepository gradeRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, GradeRepository gradeRepository) {
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
    }

    public Student getStudentForStudentId(int studentId) {
        return studentRepository.findOne(studentId);
    }

    public void saveGradeForStudentandSubject(Grade grade) {
        gradeRepository.save(grade);
    }
}
