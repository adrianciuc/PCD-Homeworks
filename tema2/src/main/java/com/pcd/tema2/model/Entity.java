package com.pcd.tema2.model;

import static com.pcd.tema2.model.Sequence.nextSequence;

public class Entity {

    private final Integer id;

    public Entity() {
        this.id = nextSequence();
    }

    public Integer getId() {
        return id;
    }
}
