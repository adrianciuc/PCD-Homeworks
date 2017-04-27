package com.fii.pcd.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "professors")
    private List<Classs> classses;

    @OneToOne
    @JoinColumn(name = "id_subject")
    private Subject subject;
}
