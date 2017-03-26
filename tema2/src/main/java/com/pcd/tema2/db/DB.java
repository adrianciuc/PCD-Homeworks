package com.pcd.tema2.db;

import com.pcd.tema2.model.*;

import java.util.ArrayList;
import java.util.List;

public class DB {

    public static List<League> leagues;

    static {
        leagues = new ArrayList<>();
        leagues.add(LeagueBuilder.aBuilder()
                .withDefaultValues()
                .withTeam(TeamBuilder.abuilder()
                        .withName("Atletico de Madrid")
                        .withManager(ManagerBuilder.aBuilder()
                                .withName("Diego Simeone")
                                .withAge("35")
                                .build())
                        .withPlayer(PlayerBuilder.aBuilder()
                                .withName("Antoine Griezman")
                                .withAge("25")
                                .withCountry("France")
                                .withRating("88.0")
                                .build())
                        .build())
                .build());
        leagues.add(LeagueBuilder.aBuilder()
                .withName("Ligue 1")
                .withCountry("France")
                .withTeam(TeamBuilder.abuilder()
                        .withName("Paris Saint-Germain")
                        .withManager(ManagerBuilder.aBuilder()
                                .withName("Arsene Wanger")
                                .withAge("60")
                                .build())
                        .withPlayer(PlayerBuilder.aBuilder()
                                .withName("Julian Draxler")
                                .withAge("23")
                                .withRating("84.0")
                                .withCountry("Germany")
                                .build())
                        .build())
                .withTeam(TeamBuilder.abuilder()
                        .withName("Olympique Lyonnais")
                        .withManager(ManagerBuilder.aBuilder()
                                .withName("Bruno Génésio")
                                .withAge("26")
                                .build())
                        .withPlayer(PlayerBuilder.aBuilder()
                                .withName("Memphis Depay")
                                .withAge("21")
                                .withRating("81.0")
                                .withCountry("Netherlands")
                                .build())
                        .build())
                .build());
    }
}
