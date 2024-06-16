package com.rsa.spaceships.infrastructure.mappers;

import com.rsa.spaceships.Data;
import com.rsa.spaceships.domain.models.Appearance;
import com.rsa.spaceships.infrastructure.entities.AppearanceEntity;
import com.rsa.spaceships.infrastructure.entities.SpaceshipEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppearanceMapperTest {

    @Autowired
    private AppearanceMapper mapper;

    @Test
    void toAppearance() {
        var entity = toAppearanceEntity(1, Data.getExpectedSpaceshipEntity(1));
        var expected = new Appearance(1, Data.getExpectedSpaceship(1), Data.getExpectedRecording(1));

        var actual = mapper.toAppearance(entity);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void nullToAppearance() {
        assertNull(mapper.toAppearance(null));
    }

    @Test
    void toAppearances() {
        var entities = Data.EXPECTED_RECORDINGS.keySet()
                .stream()
                .map(id ->  toAppearanceEntity(id, Data.getExpectedSpaceshipEntity(1)))
                .toList();
        var expected = Data.EXPECTED_RECORDINGS.keySet()
                .stream()
                .map(id ->  new Appearance(id, Data.getExpectedSpaceship(1), Data.getExpectedRecording(id)))
                .toList();

        var actual = mapper.toAppearances(entities);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void nullToAppearances() {
        assertNull(mapper.toAppearances(null));
    }

    @Test
    void toAppearanceEntity() {
        var appearance = new Appearance(1, Data.getExpectedSpaceship(1), Data.getExpectedRecording(1));
        var expected = toAppearanceEntity(1, Data.getExpectedSpaceshipEntity(1));

        var actual = mapper.toAppearanceEntity(appearance);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void nullToAppearanceEntity() {
        assertNull(mapper.toAppearanceEntity(null));
    }

    private static AppearanceEntity toAppearanceEntity(Integer id, SpaceshipEntity spaceshipEntity) {
        var appearanceEntity = new AppearanceEntity();
        appearanceEntity.setId(id);
        appearanceEntity.setSpaceship(spaceshipEntity);
        appearanceEntity.setRecording(Data.getExpectedRecordingEntity(id));
        return appearanceEntity;
    }
}