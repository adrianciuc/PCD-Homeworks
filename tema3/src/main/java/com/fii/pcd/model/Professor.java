package com.fii.pcd.model;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "professors", fetch = EAGER)
    private List<Classs> classses;

    @OneToOne
    @JoinColumn(name = "id_subject")
    private Subject subject;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Classs> getClassses() {
        return classses;
    }

    public void setClassses(List<Classs> classses) {
        this.classses = classses;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
