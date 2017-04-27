package com.fii.pcd.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "classs")
public class Classs {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name="classs_professor",
            joinColumns=@JoinColumn(name="id_classs", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="id_professor", referencedColumnName="id"))
    private List<Professor> professors;
}

