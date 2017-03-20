package com.pcd.tema2.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.pcd.tema2.model.League;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.pcd.tema2.db.DB.leagues;
import static java.util.stream.Collectors.toList;

@RestController
public class LeagueController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/leagues")
    public List<League> greeting(@RequestParam(value="name", defaultValue="") String name,
                                 @RequestParam(value = "country", defaultValue="")String country) {
        return leagues.stream()
                .filter(it -> name.isEmpty() || name.equalsIgnoreCase(it.getName()))
                .filter(it -> country.isEmpty() || country.equalsIgnoreCase(it.getName()))
                .collect(toList());
    }
}