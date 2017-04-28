package com.fii.pcd.bean;

import com.fii.pcd.model.Grade;

import java.util.List;

public class StudentBean {

    private String name;

    private List<Grade> grades;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) { this.grades = grades; }
}
