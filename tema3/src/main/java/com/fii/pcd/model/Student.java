package com.fii.pcd.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_classs")
    private Classs classs;

    @OneToMany(mappedBy = "student")
    private List<Grade> grade;

    @OneToMany
    private List<Subject> subjects;
}
