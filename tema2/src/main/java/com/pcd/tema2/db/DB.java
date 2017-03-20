package com.pcd.tema2.db;

import com.pcd.tema2.model.League;

import java.util.ArrayList;
import java.util.List;

public class DB {

    public static List<League> leagues;

    static {
        leagues = new ArrayList<>();
        leagues.add(new League());
        leagues.add(new League());
    }
}
