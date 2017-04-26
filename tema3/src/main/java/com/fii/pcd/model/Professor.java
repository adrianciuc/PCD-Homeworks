package com.fii.pcd.model;

import javax.persistence.*;

@Entity
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;
}
