package com.pcd.tema2.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.function.Supplier;

import com.pcd.tema2.model.League;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.pcd.tema2.db.DB.leagues;
import static java.lang.String.valueOf;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.SEE_OTHER;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/leagues")
public class LeagueController {

    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<League>> getAll(@RequestParam(value="name", defaultValue="") String name,
                                              @RequestParam(value = "country", defaultValue="")String country) {
        return ResponseEntity.ok()
                .body(leagues.stream()
                        .filter(it -> name.isEmpty() || name.equalsIgnoreCase(it.getName()))
                        .filter(it -> country.isEmpty() || country.equalsIgnoreCase(it.getCountry()))
                        .collect(toList()));
    }

    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity addOne(@RequestBody League league) throws URISyntaxException {
        return leagues.stream()
                .filter(it -> isNullOrEmpty(league.getName()) || league.getName().equalsIgnoreCase(it.getName()))
                .filter(it -> isNullOrEmpty(league.getCountry()) || league.getCountry().equalsIgnoreCase(it.getCountry()))
                .findFirst()
                .map(it -> ResponseEntity.status(SEE_OTHER).location(URI.create("/leagues/" + it.getId())).build())
                .orElseGet(() -> getResponseEntitySupplier(league));
    }

    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<League> getOne(@PathVariable String id) {
        return leagues.stream()
                .filter(it -> valueOf(it.getId()).equals(id))
                .findFirst()
                .map(it -> ResponseEntity.ok().body(it))
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity deleteOne(@PathVariable String id) {
        return leagues.stream()
                .filter(it -> valueOf(it.getId()).equals(id))
                .findFirst()
                .map(this::deleteLeague)
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity updateOne(@PathVariable String id,
                                    @RequestBody League league) throws URISyntaxException {
        return leagues.stream()
                .filter(it -> isNullOrEmpty(league.getName()) || league.getName().equalsIgnoreCase(it.getName()))
                .filter(it -> isNullOrEmpty(league.getCountry()) || league.getCountry().equalsIgnoreCase(it.getCountry()))
                .findFirst()
                .map(it -> updateEntity(id, it, league))
                .orElse(ResponseEntity.notFound().build());
    }

    private ResponseEntity updateEntity(String id, League existentLeague, League newLeague) {
        newLeague.setId(Integer.valueOf(id));
        leagues.remove(existentLeague);
        leagues.add(newLeague);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity deleteLeague(League league) {
        leagues.remove(league);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<Object> getResponseEntitySupplier(@RequestBody League league) {
        leagues.add(league);
        return ResponseEntity.created(URI.create("/leagues/" + league.getId())).build();
    }
}