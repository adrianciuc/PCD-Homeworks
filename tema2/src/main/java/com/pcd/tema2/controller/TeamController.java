package com.pcd.tema2.controller;

import com.pcd.tema2.model.Team;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.pcd.tema2.db.DB.leagues;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.SEE_OTHER;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("leagues/{leagueId}/teams")
public class TeamController {

    @RequestMapping(method = GET,produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Team>> getAll(@PathVariable Integer leagueId,
                                             @RequestParam(value = "name", defaultValue = "") String name) {
        return leagues.stream()
                .filter(it -> it.getId().equals(leagueId))
                .findFirst()
                .map(it -> ResponseEntity.ok().body(it.getTeams()
                        .stream()
                        .filter(team -> name.isEmpty() || name.equals(team.getName()))
                        .collect(toList())))
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Team> getOne(@PathVariable Integer leagueId,
                                       @PathVariable Integer id) {
        return leagues.stream()
                .filter(it -> it.getId().equals(leagueId))
                .findFirst()
                .map(it -> it.getTeams().stream()
                        .filter(team -> id.equals(team.getId()))
                        .findFirst()
                        .map(foundTeam -> ResponseEntity.ok()
                                .body(foundTeam))
                        .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity createOne(@PathVariable Integer leagueId,
                                    @RequestBody Team team) {
        return leagues.stream()
                .filter(it -> it.getId().equals(leagueId))
                .findFirst()
                .map(league -> league.getTeams().stream()
                        .filter(existing -> isNullOrEmpty(team.getName())
                                || team.getName().equalsIgnoreCase(existing.getName()))
                        .findFirst()
                        .map(it -> ResponseEntity.status(SEE_OTHER)
                                .location(URI.create("/leagues/" + leagueId + "/teams/" + it.getId()))
                                .build())
                        .orElseGet(() -> getResponseEntitySupplier(leagueId, team)))
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/{id}", method = DELETE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity deleteOne(@PathVariable Integer leagueId,
                                    @PathVariable Integer id) {
        return leagues.stream()
                .filter(it -> it.getId().equals(leagueId))
                .findFirst()
                .map(it -> it.getTeams().stream()
                        .filter(team -> id.equals(team.getId()))
                        .findFirst()
                        .map(teamToDelete -> deleteTeam(leagueId, teamToDelete))
                        .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/{id}", method = PUT, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity updateOne(@PathVariable Integer leagueId,
                                    @PathVariable Integer id,
                                    @RequestBody Team newTeam) {
        return leagues.stream()
                .filter(it -> it.getId().equals(leagueId))
                .findFirst()
                .map(it -> it.getTeams().stream()
                        .filter(team -> id.equals(team.getId()))
                        .findFirst()
                        .map(teamToUpdate -> updateTeam(leagueId, teamToUpdate, newTeam))
                        .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }

    private ResponseEntity<Object> getResponseEntitySupplier(Integer leagueId, Team team) {
        leagues.stream()
                .filter(it -> it.getId().equals(leagueId))
                .findFirst()
                .map(it -> it.getTeams().add(team));
        return ResponseEntity.created(URI.create("/leagues/" + leagueId + "/teams/" + team.getId())).build();
    }

    private ResponseEntity deleteTeam(Integer leagueId, Team team) {
        leagues.stream()
                .filter(it -> it.getId().equals(leagueId))
                .findFirst()
                .map(it -> it.getTeams().remove(team));
        return ResponseEntity.ok().build();
    }

    private ResponseEntity updateTeam(Integer leagueId,
                                      Team existingTeam,
                                      Team newTeam) {
        newTeam.setId(existingTeam.getId());
        leagues.stream()
                .filter(it -> it.getId().equals(leagueId))
                .findFirst()
                .map(it -> it.getTeams().remove(existingTeam)
                        && it.getTeams().add(newTeam));
        return ResponseEntity.ok().build();
    }
}
