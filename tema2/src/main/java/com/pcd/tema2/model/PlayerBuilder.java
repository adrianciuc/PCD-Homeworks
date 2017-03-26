package com.pcd.tema2.model;

public class PlayerBuilder {

    private String name;
    private String age;
    private String rating;
    private String country;

    private PlayerBuilder() {}

    public static PlayerBuilder aBuilder() {
        return new PlayerBuilder();
    }

    public PlayerBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PlayerBuilder withAge(String age) {
        this.age = age;
        return this;
    }

    public PlayerBuilder withRating(String rating) {
        this.rating = rating;
        return this;
    }

    public PlayerBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public PlayerBuilder withDefaultValues() {
        this.name = "Messi";
        this.country = "Argentina";
        this.rating = "99.9";
        this.age = "29";
        return this;
    }

    public Player build() {
        Player player = new Player();
        player.setName(this.name);
        player.setAge(this.age);
        player.setCountry(this.country);
        player.setRating(this.rating);
        return player;
    }
}
