package com.ofg.tema2;

import com.pcd.tema2.Application;
import com.pcd.tema2.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.pcd.tema2.db.DB.leagues;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = {Application.class})
public class LeagueControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void prepareInMemoryDB() {
        leagues.clear();
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

    @Test
    public void getAllLeagues() {
        ResponseEntity<League[]> response =
                this.restTemplate.getForEntity("/leagues", League[].class);
        League[] body = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(body).hasSize(2);
        assertThat(body[0].getName()).isEqualToIgnoringCase("BBVA");
        assertThat(body[0].getCountry()).isEqualToIgnoringCase("Spain");
        assertThat(body[0].getTeams()).hasSize(2);
        assertThat(body[0].getTeams().get(0).getName()).isEqualToIgnoringCase("Barcelona");
        assertThat(body[0].getTeams().get(0).getManager().getName()).isEqualToIgnoringCase("Luis Enrique");
        assertThat(body[0].getTeams().get(0).getManager().getAge()).isEqualToIgnoringCase("40");
        assertThat(body[0].getTeams().get(0).getPlayers()).hasSize(1);
        assertThat(body[0].getTeams().get(0).getPlayers().get(0).getName()).isEqualToIgnoringCase("Messi");
        assertThat(body[0].getTeams().get(0).getPlayers().get(0).getAge()).isEqualToIgnoringCase("29");
        assertThat(body[0].getTeams().get(0).getPlayers().get(0).getCountry()).isEqualToIgnoringCase("Argentina");
        assertThat(body[0].getTeams().get(0).getPlayers().get(0).getRating()).isEqualToIgnoringCase("99.9");
        assertThat(body[0].getTeams().get(1).getName()).isEqualToIgnoringCase("Atletico de Madrid");
        assertThat(body[0].getTeams().get(1).getManager().getName()).isEqualToIgnoringCase("Diego Simeone");
        assertThat(body[0].getTeams().get(1).getManager().getAge()).isEqualToIgnoringCase("35");
        assertThat(body[0].getTeams().get(1).getPlayers()).hasSize(1);
        assertThat(body[0].getTeams().get(1).getPlayers().get(0).getName()).isEqualToIgnoringCase("Antoine Griezman");
        assertThat(body[0].getTeams().get(1).getPlayers().get(0).getAge()).isEqualToIgnoringCase("25");
        assertThat(body[0].getTeams().get(1).getPlayers().get(0).getCountry()).isEqualToIgnoringCase("France");
        assertThat(body[0].getTeams().get(1).getPlayers().get(0).getRating()).isEqualToIgnoringCase("88.0");
        assertThat(body[1].getName()).isEqualToIgnoringCase("Ligue 1");
        assertThat(body[1].getCountry()).isEqualToIgnoringCase("France");
        assertThat(body[1].getTeams()).hasSize(2);
        assertThat(body[1].getTeams().get(0).getName()).isEqualToIgnoringCase("Paris Saint-Germain");
        assertThat(body[1].getTeams().get(0).getManager().getName()).isEqualToIgnoringCase("Arsene Wanger");
        assertThat(body[1].getTeams().get(0).getManager().getAge()).isEqualToIgnoringCase("60");
        assertThat(body[1].getTeams().get(0).getPlayers()).hasSize(1);
        assertThat(body[1].getTeams().get(0).getPlayers().get(0).getName()).isEqualToIgnoringCase("Julian Draxler");
        assertThat(body[1].getTeams().get(0).getPlayers().get(0).getAge()).isEqualToIgnoringCase("23");
        assertThat(body[1].getTeams().get(0).getPlayers().get(0).getCountry()).isEqualToIgnoringCase("Germany");
        assertThat(body[1].getTeams().get(0).getPlayers().get(0).getRating()).isEqualToIgnoringCase("84.0");
        assertThat(body[1].getTeams().get(1).getName()).isEqualToIgnoringCase("Olympique Lyonnais");
        assertThat(body[1].getTeams().get(1).getManager().getName()).isEqualToIgnoringCase("Bruno Génésio");
        assertThat(body[1].getTeams().get(1).getManager().getAge()).isEqualToIgnoringCase("26");
        assertThat(body[1].getTeams().get(1).getPlayers()).hasSize(1);
        assertThat(body[1].getTeams().get(1).getPlayers().get(0).getName()).isEqualToIgnoringCase("Memphis Depay");
        assertThat(body[1].getTeams().get(1).getPlayers().get(0).getAge()).isEqualToIgnoringCase("21");
        assertThat(body[1].getTeams().get(1).getPlayers().get(0).getCountry()).isEqualToIgnoringCase("Netherlands");
        assertThat(body[1].getTeams().get(1).getPlayers().get(0).getRating()).isEqualToIgnoringCase("81.0");
    }

    @Test
    public void getAllLeaguesWithNameFilter() {
        ResponseEntity<League[]> response =
                this.restTemplate.getForEntity("/leagues?name=BBVA", League[].class);
        League[] body = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(body).hasSize(1);
        assertThat(body[0].getName()).isEqualToIgnoringCase("BBVA");
        assertThat(body[0].getCountry()).isEqualToIgnoringCase("Spain");
        assertThat(body[0].getTeams()).hasSize(2);
        assertThat(body[0].getTeams().get(0).getName()).isEqualToIgnoringCase("Barcelona");
        assertThat(body[0].getTeams().get(0).getManager().getName()).isEqualToIgnoringCase("Luis Enrique");
        assertThat(body[0].getTeams().get(0).getManager().getAge()).isEqualToIgnoringCase("40");
        assertThat(body[0].getTeams().get(0).getPlayers()).hasSize(1);
        assertThat(body[0].getTeams().get(0).getPlayers().get(0).getName()).isEqualToIgnoringCase("Messi");
        assertThat(body[0].getTeams().get(0).getPlayers().get(0).getAge()).isEqualToIgnoringCase("29");
        assertThat(body[0].getTeams().get(0).getPlayers().get(0).getCountry()).isEqualToIgnoringCase("Argentina");
        assertThat(body[0].getTeams().get(0).getPlayers().get(0).getRating()).isEqualToIgnoringCase("99.9");
        assertThat(body[0].getTeams().get(1).getName()).isEqualToIgnoringCase("Atletico de Madrid");
        assertThat(body[0].getTeams().get(1).getManager().getName()).isEqualToIgnoringCase("Diego Simeone");
        assertThat(body[0].getTeams().get(1).getManager().getAge()).isEqualToIgnoringCase("35");
        assertThat(body[0].getTeams().get(1).getPlayers()).hasSize(1);
        assertThat(body[0].getTeams().get(1).getPlayers().get(0).getName()).isEqualToIgnoringCase("Antoine Griezman");
        assertThat(body[0].getTeams().get(1).getPlayers().get(0).getAge()).isEqualToIgnoringCase("25");
        assertThat(body[0].getTeams().get(1).getPlayers().get(0).getCountry()).isEqualToIgnoringCase("France");
        assertThat(body[0].getTeams().get(1).getPlayers().get(0).getRating()).isEqualToIgnoringCase("88.0");
    }

    @Test
    public void getAllLeaguesWithNameFilterHavingWrongName() {
        ResponseEntity<League[]> response =
                this.restTemplate.getForEntity("/leagues?name=INEXISTENT", League[].class);
        League[] body = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(body).hasSize(0);
    }

    @Test
    public void getAllLeaguesWithCountryFilter() {
        ResponseEntity<League[]> response =
                this.restTemplate.getForEntity("/leagues?country=Spain", League[].class);
        League[] body = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(body).hasSize(1);
        assertThat(body[0].getName()).isEqualToIgnoringCase("BBVA");
        assertThat(body[0].getCountry()).isEqualToIgnoringCase("Spain");
        assertThat(body[0].getTeams()).hasSize(2);
        assertThat(body[0].getTeams().get(0).getName()).isEqualToIgnoringCase("Barcelona");
        assertThat(body[0].getTeams().get(0).getManager().getName()).isEqualToIgnoringCase("Luis Enrique");
        assertThat(body[0].getTeams().get(0).getManager().getAge()).isEqualToIgnoringCase("40");
        assertThat(body[0].getTeams().get(0).getPlayers()).hasSize(1);
        assertThat(body[0].getTeams().get(0).getPlayers().get(0).getName()).isEqualToIgnoringCase("Messi");
        assertThat(body[0].getTeams().get(0).getPlayers().get(0).getAge()).isEqualToIgnoringCase("29");
        assertThat(body[0].getTeams().get(0).getPlayers().get(0).getCountry()).isEqualToIgnoringCase("Argentina");
        assertThat(body[0].getTeams().get(0).getPlayers().get(0).getRating()).isEqualToIgnoringCase("99.9");
        assertThat(body[0].getTeams().get(1).getName()).isEqualToIgnoringCase("Atletico de Madrid");
        assertThat(body[0].getTeams().get(1).getManager().getName()).isEqualToIgnoringCase("Diego Simeone");
        assertThat(body[0].getTeams().get(1).getManager().getAge()).isEqualToIgnoringCase("35");
        assertThat(body[0].getTeams().get(1).getPlayers()).hasSize(1);
        assertThat(body[0].getTeams().get(1).getPlayers().get(0).getName()).isEqualToIgnoringCase("Antoine Griezman");
        assertThat(body[0].getTeams().get(1).getPlayers().get(0).getAge()).isEqualToIgnoringCase("25");
        assertThat(body[0].getTeams().get(1).getPlayers().get(0).getCountry()).isEqualToIgnoringCase("France");
        assertThat(body[0].getTeams().get(1).getPlayers().get(0).getRating()).isEqualToIgnoringCase("88.0");
    }

    @Test
    public void getAllLeaguesWithCountryFilterHavingWrongValue() {
        ResponseEntity<League[]> response =
                this.restTemplate.getForEntity("/leagues?country=Wrong", League[].class);
        League[] body = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(body).hasSize(0);
    }

    @Test
    public void getAllLeaguesWithNameFilterAndCountryFilter() {
        ResponseEntity<League[]> response =
                this.restTemplate.getForEntity("/leagues?name=BBVA&country=Spain", League[].class);
        League[] body = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(body).hasSize(1);
        assertThat(body[0].getName()).isEqualToIgnoringCase("BBVA");
        assertThat(body[0].getCountry()).isEqualToIgnoringCase("Spain");
        assertThat(body[0].getTeams()).hasSize(2);
        assertThat(body[0].getTeams().get(0).getName()).isEqualToIgnoringCase("Barcelona");
        assertThat(body[0].getTeams().get(0).getManager().getName()).isEqualToIgnoringCase("Luis Enrique");
        assertThat(body[0].getTeams().get(0).getManager().getAge()).isEqualToIgnoringCase("40");
        assertThat(body[0].getTeams().get(0).getPlayers()).hasSize(1);
        assertThat(body[0].getTeams().get(0).getPlayers().get(0).getName()).isEqualToIgnoringCase("Messi");
        assertThat(body[0].getTeams().get(0).getPlayers().get(0).getAge()).isEqualToIgnoringCase("29");
        assertThat(body[0].getTeams().get(0).getPlayers().get(0).getCountry()).isEqualToIgnoringCase("Argentina");
        assertThat(body[0].getTeams().get(0).getPlayers().get(0).getRating()).isEqualToIgnoringCase("99.9");
        assertThat(body[0].getTeams().get(1).getName()).isEqualToIgnoringCase("Atletico de Madrid");
        assertThat(body[0].getTeams().get(1).getManager().getName()).isEqualToIgnoringCase("Diego Simeone");
        assertThat(body[0].getTeams().get(1).getManager().getAge()).isEqualToIgnoringCase("35");
        assertThat(body[0].getTeams().get(1).getPlayers()).hasSize(1);
        assertThat(body[0].getTeams().get(1).getPlayers().get(0).getName()).isEqualToIgnoringCase("Antoine Griezman");
        assertThat(body[0].getTeams().get(1).getPlayers().get(0).getAge()).isEqualToIgnoringCase("25");
        assertThat(body[0].getTeams().get(1).getPlayers().get(0).getCountry()).isEqualToIgnoringCase("France");
        assertThat(body[0].getTeams().get(1).getPlayers().get(0).getRating()).isEqualToIgnoringCase("88.0");
    }

    @Test
    public void getAllLeaguesWithNameFilterHavingWrongValueAndCountryFilter() {
        ResponseEntity<League[]> response =
                this.restTemplate.getForEntity("/leagues?name=justwrong&country=Spain", League[].class);
        League[] body = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(body).hasSize(0);
    }

    @Test
    public void getAllLeaguesWithNameFilterAndCountryFilterHavingWrongValue() {
        ResponseEntity<League[]> response =
                this.restTemplate.getForEntity("/leagues?name=BBVA&country=yetwrong", League[].class);
        League[] body = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(body).hasSize(0);
    }
}