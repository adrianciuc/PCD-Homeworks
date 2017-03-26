package com.pcd.tema2.model;

public class ManagerBuilder {

    private String age;
    private String name;

    private ManagerBuilder() {}

    public static ManagerBuilder aBuilder() {
        return new ManagerBuilder();
    }

    public ManagerBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ManagerBuilder withAge(String age) {
        this.age = age;
        return this;
    }

    public ManagerBuilder withDefaultValues() {
        name = "Luis Enrique";
        age = "40";
        return this;
    }

    public Manager build() {
        Manager manager = new Manager();
        manager.setName(this.name);
        manager.setAge(this.age);
        return manager;
    }
}
