package com.fii.pcd.model;

import javax.persistence.*;

@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

}
