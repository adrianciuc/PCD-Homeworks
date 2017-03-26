package com.pcd.tema2.model;

import java.util.ArrayList;
import java.util.List;

public class LeagueBuilder {

    private String name;
    private String country;
    private List<Team> teams;

    private LeagueBuilder() {
        teams = new ArrayList<>();
    }

    public static LeagueBuilder aBuilder() {
        return new LeagueBuilder();
    }

    public LeagueBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public LeagueBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public LeagueBuilder withTeams(List<Team> teams) {
        this.teams.addAll(teams);
        return this;
    }

    public LeagueBuilder withTeam(Team team) {
        this.teams.add(team);
        return this;
    }

    public LeagueBuilder withDefaultValues() {
        name = "BBVA";
        country = "Spain";
        teams = new ArrayList<>();
        teams.add(TeamBuilder.abuilder()
                .withDefaultValues()
                .build());
        return this;
    }

    public League build() {
        League league = new League();
        league.setName(this.name);
        league.setCountry(this.country);
        league.setTeams(this.teams);
        return league;
    }
}
