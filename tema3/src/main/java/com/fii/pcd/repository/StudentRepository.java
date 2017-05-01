package com.fii.pcd.repository;

import com.fii.pcd.model.Classs;
import com.fii.pcd.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByClasss(Classs classs);

    Student findByName(String name);
}
