package com.rsa.spaceships.infrastructure.mappers;

import com.rsa.spaceships.Data;
import com.rsa.spaceships.infrastructure.entities.SpaceshipEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpaceshipMapperTest {

    @Autowired
    private SpaceshipMapper mapper;

    @Test
    void toSpaceship() {
        var entity = Data.getExpectedSpaceshipEntity(1);
        var expected = Data.getExpectedSpaceship(1);

        var actual = mapper.toSpaceship(entity);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void nullToSpaceship() {
        assertNull(mapper.toSpaceship(null));
    }

    @Test
    void toSpaceshipsIterable() {
        var entities = Data.EXPECTED_SPACESHIPS.keySet().stream().map(Data::getExpectedSpaceshipEntity).toList();
        var expected = Data.EXPECTED_SPACESHIPS.keySet().stream().map(Data::getExpectedSpaceship).toList();

        var actual = mapper.toSpaceships(entities);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertIterableEquals(expected, actual)
        );
    }

    @Test
    void nullToSpaceships() {
        assertNull(mapper.toSpaceships((Iterable<SpaceshipEntity>) null));
    }

    @Test
    void toSpaceshipsPage() {
        var entities = Data.EXPECTED_SPACESHIPS.keySet().stream().map(Data::getExpectedSpaceshipEntity).toList();
        var expected = Data.EXPECTED_SPACESHIPS.keySet().stream().map(Data::getExpectedSpaceship).toList();

        var actual = mapper.toSpaceships(new PageImpl<>(entities, PageRequest.of(0, 10), 5));

        assertAll(
                () -> assertNotNull(actual),
                () -> assertIterableEquals(expected, actual)
        );
    }

    @Test
    void toSpaceshipEntity() {
        var spaceship = Data.getExpectedSpaceship(1);
        var expected = Data.getExpectedSpaceshipEntity(1);

        var actual = mapper.toSpaceshipEntity(spaceship);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void nullToSpaceshipEntity() {
        assertNull(mapper.toSpaceshipEntity(null));
    }
}