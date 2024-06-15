package com.rsa.spaceships.infrastructure.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rsa.spaceships.Data;
import com.rsa.spaceships.domain.models.Appearance;
import com.rsa.spaceships.domain.models.Recording;
import com.rsa.spaceships.domain.models.Spaceship;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ControllersIntegrationTest {

    @Autowired
    private TestRestTemplate client;

    @LocalServerPort
    private int port;

    @Test
    @Order(1)
    void recordingControllerFindBySpaceshipNameLike() {
        var expected = Data.EXPECTED_RECORDINGS.keySet().stream().map(Data::getExpectedRecording).toArray();

        var pathVariables = Map.of("name", "X-W");
        var response = client.exchange(
                getTestUri("/recording/{name}", port),
                HttpMethod.GET,
                null,
                Recording[].class,
                pathVariables
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(APPLICATION_JSON, response.getHeaders().getContentType());

        var actual = response.getBody();

        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(2)
    void spaceshipControllerFindAll() {
        var expected = Data.EXPECTED_SPACESHIPS.keySet().stream().map(Data::getExpectedSpaceship).toArray();

        var response = client.getForEntity(
                getTestUri("/spaceship", port),
                Spaceship[].class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(APPLICATION_JSON, response.getHeaders().getContentType());

        var actual = response.getBody();

        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(3)
    void spaceshipControllerFindById() {
        var expected = Data.getExpectedSpaceship(1);

        var pathVariables = Map.of("id", 1);
        var response = client.exchange(
                getTestUri("/spaceship/{id}", port),
                HttpMethod.GET,
                null,
                Spaceship.class,
                pathVariables
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(APPLICATION_JSON, response.getHeaders().getContentType());

        var actual = response.getBody();

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    @Order(4)
    void spaceshipControllerFindByIdKoNotFound() {
        var pathVariables = Map.of("id", -1);
        var response = client.exchange(
                getTestUri("/spaceship/{id}", port),
                HttpMethod.GET,
                null,
                Spaceship.class,
                pathVariables
        );

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @Order(5)
    void spaceshipControllerFindByNameLike() {
        var pathVariables = Map.of("name", "X-Wing");
        var response = client.exchange(
                getTestUri("/spaceship/name/{name}", port),
                HttpMethod.GET,
                null,
                Spaceship[].class,
                pathVariables
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(APPLICATION_JSON, response.getHeaders().getContentType());

        var actual = response.getBody();

        assertNotNull(actual);
        assertEquals(1, actual.length);
        assertEquals(Data.getExpectedSpaceship(1), actual[0]);
    }

    @Test
    @Order(6)
    void spaceshipControllerFindByRecordingNameLike() {
        var expected = Data.EXPECTED_SPACESHIPS.keySet().stream().map(Data::getExpectedSpaceship).toArray();

        var pathVariables = Map.of("name", "Star Wars: Episodio IV - Una nueva esperanza");
        var response = client.exchange(
                getTestUri("/spaceship/recording/{name}", port),
                HttpMethod.GET,
                null,
                Spaceship[].class,
                pathVariables
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(APPLICATION_JSON, response.getHeaders().getContentType());

        var actual = response.getBody();

        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(7)
    void spaceshipControllerCreate() {
        var spaceship = new Spaceship(null, "another test");

        var response = client.postForEntity(getTestUri("/spaceship", port), spaceship, Spaceship.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(APPLICATION_JSON, response.getHeaders().getContentType());

        var actual = response.getBody();

        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertNotNull(actual.getName());
        assertEquals("another test", actual.getName());
    }

    @Test
    @Order(8)
    void spaceshipControllerUpdate() {
        var spaceship = new Spaceship(6, "update test");
        var request = RequestEntity
                .put(getTestUri("/spaceship", port))
                .accept(MediaType.APPLICATION_JSON)
                .body(spaceship);

        var response = this.client.exchange(
                getTestUri("/spaceship", port),
                HttpMethod.PUT,
                request,
                Spaceship.class,
                new HashMap<>()
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(APPLICATION_JSON, response.getHeaders().getContentType());

        var actual = response.getBody();

        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(6, actual.getId());
        assertNotNull(actual.getName());
        assertEquals("update test", actual.getName());
    }

    @Test
    @Order(9)
    void appearanceControllerCreate() throws JsonProcessingException {
        var appearance = new Appearance(
                null,
                new Spaceship(null, "test spaceship"),
                new Recording(null, "test recording")
        );

        var response = client.postForEntity(getTestUri("/appearance", port), appearance, Appearance.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(APPLICATION_JSON, response.getHeaders().getContentType());

        var actual = response.getBody();

        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertNotNull(actual.getRecording());
        assertNotNull(actual.getRecording().getId());
        assertEquals("test recording", actual.getRecording().getName());
        assertNotNull(actual.getSpaceship());
        assertNotNull(actual.getSpaceship().getId());
        assertEquals("test spaceship", actual.getSpaceship().getName());
    }

    @Test
    @Order(10)
    void spaceshipControllerDelete() {
        var pathVariables = Map.of("id", 7);
        var response = this.client.exchange(
                getTestUri("/spaceship/{id}", port),
                HttpMethod.DELETE,
                null,
                Void.class,
                pathVariables
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(11)
    void spaceshipControllerDeleteKo() {
        var pathVariables = Map.of("id", 999);
        var response = this.client.exchange(
                getTestUri("/spaceship/{id}", port),
                HttpMethod.DELETE,
                null,
                Void.class,
                pathVariables
        );

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    private static String getTestUri(String uri, int port) {
        return String.format("http://localhost:%d%s", port, uri);
    }
}