package com.rsa.spaceships.infrastructure.adapters;

import com.rsa.spaceships.Data;
import com.rsa.spaceships.domain.models.Appearance;
import com.rsa.spaceships.infrastructure.entities.AppearanceEntity;
import com.rsa.spaceships.infrastructure.entities.RecordingEntity;
import com.rsa.spaceships.infrastructure.entities.SpaceshipEntity;
import com.rsa.spaceships.infrastructure.mappers.AppearanceMapper;
import com.rsa.spaceships.infrastructure.repositories.AppearanceJpaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest
class AppearanceRepositoryAdapterTest {

    @InjectMocks
    private AppearanceRepositoryAdapter adapter;

    @Mock
    private AppearanceJpaRepository repository;

    @Mock
    private AppearanceMapper mapper;

    @Test
    void findBySpaceshipId() {
        var appearanceEntities = Data.EXPECTED_RECORDINGS.keySet()
                .stream()
                .map(Data::getExpectedRecordingEntity)
                .map(recordingEntity -> toAppearanceEntity(Data.getExpectedSpaceshipEntity(1), recordingEntity))
                .toList();
        var expected = Data.EXPECTED_RECORDINGS.keySet()
                .stream()
                .map(Data::getExpectedRecording)
                .map(recording -> new Appearance(recording.getId(), Data.getExpectedSpaceship(1), recording))
                .toList();

        when(repository.findBySpaceshipId(1)).thenReturn(appearanceEntities);
        when(mapper.toAppearances(appearanceEntities)).thenReturn(expected);

        var actual = adapter.findBySpaceshipId(1);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertIterableEquals(expected, actual)
        );

        verify(repository, times(1)).findBySpaceshipId(any());
        verify(repository, times(1)).findBySpaceshipId(1);
        verify(mapper, times(1)).toAppearances(any());
        verify(mapper, times(1)).toAppearances(appearanceEntities);
        noMoreInteractions();
    }

    @Test
    void save() {
        var spaceship = Data.getExpectedSpaceship(1);
        spaceship.setId(null);
        var spaceshipEntity = Data.getExpectedSpaceshipEntity(1);
        spaceship.setId(null);
        var recording = Data.getExpectedRecording(1);
        recording.setId(null);
        var recordingEntity = Data.getExpectedRecordingEntity(1);
        recording.setId(null);
        var appearance = new Appearance(null, spaceship, recording);
        var appearanceEntity = toAppearanceEntity(spaceshipEntity, recordingEntity);
        var expectedAppearanceEntity = toAppearanceEntity(
                Data.getExpectedSpaceshipEntity(1),
                Data.getExpectedRecordingEntity(1)
        );
        var expected = new Appearance(1, Data.getExpectedSpaceship(1), Data.getExpectedRecording(1));

        when(repository.save(appearanceEntity)).thenReturn(expectedAppearanceEntity);
        when(mapper.toAppearanceEntity(appearance)).thenReturn(appearanceEntity);
        when(mapper.toAppearance(expectedAppearanceEntity)).thenReturn(expected);

        var actual = adapter.save(appearance);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );

        verify(repository, times(1)).save(any());
        verify(repository, times(1)).save(appearanceEntity);
        verify(mapper, times(1)).toAppearanceEntity(any());
        verify(mapper, times(1)).toAppearanceEntity(appearance);
        verify(mapper, times(1)).toAppearance(any());
        verify(mapper, times(1)).toAppearance(expectedAppearanceEntity);
        noMoreInteractions();
    }

    @Test
    void deleteById() {
        assertDoesNotThrow(() -> adapter.deleteById(1));

        verify(repository, times(1)).deleteById(any());
        verify(repository, times(1)).deleteById(1);
        noMoreInteractions();
    }

    private void noMoreInteractions() {
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }

    private static AppearanceEntity toAppearanceEntity(SpaceshipEntity spaceship, RecordingEntity recording) {
        var appearanceEntity = new AppearanceEntity();
        appearanceEntity.setId(recording.getId());
        appearanceEntity.setRecording(recording);
        appearanceEntity.setSpaceship(spaceship);
        return appearanceEntity;
    }
}