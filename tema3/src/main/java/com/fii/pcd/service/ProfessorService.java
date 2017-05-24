package com.fii.pcd.service;

import com.fii.pcd.bean.ClassWithStudentsBean;
import com.fii.pcd.bean.ProfessorAndSubjectBean;
import com.fii.pcd.bean.StudentBean;
import com.fii.pcd.model.Classs;
import com.fii.pcd.model.Professor;
import com.fii.pcd.model.Student;
import com.fii.pcd.model.Subject;
import com.fii.pcd.repository.ProfessorRepository;
import com.fii.pcd.repository.StudentRepository;
import com.fii.pcd.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    private final StudentRepository studentRepository;

    private StudentService studentService;

    @Autowired
<<<<<<< HEAD
    public ProfessorService(ProfessorRepository professorRepository, StudentRepository studentRepository, SubjectRepository subjectRepository) {
=======
    public ProfessorService(ProfessorRepository professorRepository, StudentRepository studentRepository, StudentService studentService) {
>>>>>>> 48617bc16cf52872b586e34e1e8f4eedf43d7e8f
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
        this.studentService = studentService;
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
                studentToAdd.setGrades(studentService.getGradesForStudentAtSubject(student, p.getSubject()));
                studentToAdd.setAverage(getAverageGradeForStudentAndSubject(student, p.getSubject()) + "");
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

    public double getAverageGradeForStudentAndSubject(Student student, Subject subject) {
        List<Grade> grades = studentService.getGradesForStudentAtSubject(student, subject);
        double total = 0.0;
        int count = 0;

        for (Grade grade : grades) {
            total += Double.parseDouble(grade.getGrade());
            count ++;
        }

        return total/ (double) count;
    }



}
