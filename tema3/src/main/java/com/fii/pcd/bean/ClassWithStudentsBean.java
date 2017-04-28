package com.fii.pcd.bean;

import java.util.List;

public class ClassWithStudentsBean {

    private String name;

    private List<StudentBean> students;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StudentBean> getStudents() {
        return students;
    }

    public void setStudents(List<StudentBean> students) {
        this.students = students;
    }
}
