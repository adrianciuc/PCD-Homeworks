package com.pcd.tema2.model;

public class Sequence {

    private static int sequence = 0;

    public static Integer nextSequence() {
        return sequence++;
    }
}
