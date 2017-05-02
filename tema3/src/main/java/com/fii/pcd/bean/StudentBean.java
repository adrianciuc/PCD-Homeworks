package com.fii.pcd.bean;

import com.fii.pcd.model.Grade;

import java.util.List;

public class StudentBean {

    private int id;

    private String name;

    private List<Grade> grades;

    private String average;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }
}
