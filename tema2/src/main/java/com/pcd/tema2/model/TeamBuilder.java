package com.pcd.tema2.model;

import java.util.ArrayList;
import java.util.List;

public class TeamBuilder {

    private String name;
    private Manager manager;
    private List<Player> players;

    private TeamBuilder() {
        players = new ArrayList<>();
    }

    public static TeamBuilder abuilder() {
        return new TeamBuilder();
    }

    public TeamBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TeamBuilder withManager(Manager manager) {
        this.manager = manager;
        return this;
    }

    public TeamBuilder withPlayers(List<Player> players) {
        this.players.addAll(players);
        return this;
    }

    public TeamBuilder withPlayer(Player player) {
        this.players.add(player);
        return this;
    }

    public TeamBuilder withDefaultValues() {
        name = "Barcelona";
        manager = ManagerBuilder.aBuilder()
                .withDefaultValues()
                .build();
        players = new ArrayList<>();
        players.add(PlayerBuilder.aBuilder()
                .withDefaultValues()
                .build());
        return this;
    }

    public Team build() {
        Team team = new Team();
        team.setName(this.name);
        team.setManager(this.manager);
        team.setPlayers(this.players);
        return team;
    }
}
