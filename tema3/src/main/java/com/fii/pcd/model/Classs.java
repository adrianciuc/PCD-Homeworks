package com.fii.pcd.model;

import javax.persistence.*;

@Entity
@Table(name = "classs")
public class Classs {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_professor")
    private Professor professor;
}

