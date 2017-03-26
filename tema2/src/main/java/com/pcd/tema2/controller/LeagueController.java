package com.pcd.tema2.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.pcd.tema2.model.League;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.pcd.tema2.db.DB.leagues;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.SEE_OTHER;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/leagues")
public class LeagueController {

    @RequestMapping(method = GET)
    public ResponseEntity<List<League>> getAll(@RequestParam(value="name", defaultValue="") String name,
                                              @RequestParam(value = "country", defaultValue="")String country) {
        return ResponseEntity.ok()
                .body(leagues.stream()
                        .filter(it -> name.isEmpty() || name.equalsIgnoreCase(it.getName()))
                        .filter(it -> country.isEmpty() || country.equalsIgnoreCase(it.getCountry()))
                        .collect(toList()));
    }

    @RequestMapping(method = POST)
    public ResponseEntity addOne(@RequestBody League league) throws URISyntaxException {
        return leagues.stream()
                .filter(it -> league.getName().isEmpty() || league.getName().equalsIgnoreCase(it.getName()))
                .filter(it -> league.getCountry().isEmpty() || league.getCountry().equalsIgnoreCase(it.getCountry()))
                .findAny()
                .map(it -> ResponseEntity.status(SEE_OTHER).location(URI.create("/leagues/" + it.getId())).build())
                .orElse(ResponseEntity.created(URI.create("/leagues/" + league.getId())).build());
    }
}