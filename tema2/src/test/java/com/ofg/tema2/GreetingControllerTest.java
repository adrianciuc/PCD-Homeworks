package com.ofg.tema2;

import com.pcd.tema2.Application;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = {Application.class})
public class GreetingControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void defaultParameterTest() {
        String body = this.restTemplate.getForObject("/greeting", String.class);
        assertThat(body).isEqualTo("Yellow!!!World");
    }

    @Test
    public void nameParameterTest() {
        String body = this.restTemplate.getForObject("/greeting?name=me", String.class);
        assertThat(body).isEqualTo("Yellow!!!me");
    }
}