package com.fii.pcd.model;

import javax.persistence.*;

@Entity
@Table(name = "grade")
public class Grade {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "grade")
    private String grade;

    @ManyToOne
    @JoinColumn(name="id_student")
    private Student student;

    @ManyToOne
    @JoinColumn(name="id_subject")
    private Subject subject;

}

