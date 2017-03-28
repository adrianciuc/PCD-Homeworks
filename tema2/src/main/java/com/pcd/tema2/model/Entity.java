package com.pcd.tema2.model;

import static com.pcd.tema2.model.Sequence.nextSequence;

public class Entity {

    private Integer id;

    public Entity() {
        this.id = nextSequence();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
