package com.rsa.spaceships.application.services;

import com.rsa.spaceships.Data;
import com.rsa.spaceships.domain.models.Appearance;
import com.rsa.spaceships.domain.models.Recording;
import com.rsa.spaceships.domain.models.Spaceship;
import com.rsa.spaceships.domain.repositories.AppearanceRepository;
import com.rsa.spaceships.domain.repositories.RecordingRepository;
import com.rsa.spaceships.domain.repositories.SpaceshipRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest
class AppearanceServiceTest {

    @InjectMocks
    private AppearanceService service;

    @Mock
    private AppearanceRepository appearanceRepository;

    @Mock
    private RecordingRepository recordingRepository;

    @Mock
    private SpaceshipRepository spaceshipRepository;

    @Test
    void saveWhenAllExist() {
        var spaceship = Data.getExpectedSpaceship(1);
        var recording = Data.getExpectedRecording(1);
        var parameter = new Appearance(null, spaceship, recording);
        var expected = new Appearance(1, spaceship, recording);

        when(recordingRepository.findById(1)).thenReturn(Optional.of(recording));
        when(spaceshipRepository.findById(1)).thenReturn(Optional.of(spaceship));
        when(appearanceRepository.save(parameter)).thenReturn(expected);

        var actual = service.save(parameter);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );

        verify(recordingRepository, times(1)).findById(any());
        verify(recordingRepository, times(1)).findById(1);
        verify(spaceshipRepository, times(1)).findById(any());
        verify(spaceshipRepository, times(1)).findById(1);
        verify(appearanceRepository, times(1)).save(any());
        verify(appearanceRepository, times(1)).save(parameter);
        noMoreInteractions();
    }

    @Test
    void saveWhenRecordingNotExist() {
        var spaceship = Data.getExpectedSpaceship(1);
        var recording = Data.getExpectedRecording(1);
        var recordingToSave = new Recording(null, recording.getName());
        var parameter = new Appearance(null, spaceship, recordingToSave);
        var expected = new Appearance(1, spaceship, recording);

        when(recordingRepository.save(recordingToSave)).thenReturn(recording);
        when(spaceshipRepository.findById(1)).thenReturn(Optional.of(spaceship));
        when(appearanceRepository.save(parameter)).thenReturn(expected);

        var actual = service.save(parameter);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );

        verify(recordingRepository, times(1)).save(any());
        verify(recordingRepository, times(1)).save(recordingToSave);
        verify(spaceshipRepository, times(1)).findById(any());
        verify(spaceshipRepository, times(1)).findById(1);
        verify(appearanceRepository, times(1)).save(any());
        verify(appearanceRepository, times(1)).save(parameter);
        noMoreInteractions();
    }

    @Test
    void saveWhenSpaceshipNotExist() {
        var spaceship = Data.getExpectedSpaceship(1);
        var recording = Data.getExpectedRecording(1);
        var spaceshipToSave = new Spaceship(null, spaceship.getName());
        var parameter = new Appearance(null, spaceshipToSave, recording);
        var expected = new Appearance(1, spaceship, recording);

        when(recordingRepository.findById(1)).thenReturn(Optional.of(recording));
        when(spaceshipRepository.save(spaceshipToSave)).thenReturn(spaceship);
        when(appearanceRepository.save(parameter)).thenReturn(expected);

        var actual = service.save(parameter);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );

        verify(recordingRepository, times(1)).findById(any());
        verify(recordingRepository, times(1)).findById(1);
        verify(spaceshipRepository, times(1)).save(any());
        verify(spaceshipRepository, times(1)).save(spaceshipToSave);
        verify(appearanceRepository, times(1)).save(any());
        verify(appearanceRepository, times(1)).save(parameter);
        noMoreInteractions();
    }

    @Test
    void saveNothingExist() {
        var spaceship = Data.getExpectedSpaceship(1);
        var recording = Data.getExpectedRecording(1);
        var spaceshipToSave = new Spaceship(null, spaceship.getName());
        var recordingToSave = new Recording(null, recording.getName());
        var parameter = new Appearance(null, spaceshipToSave, recordingToSave);
        var expected = new Appearance(1, spaceship, recording);

        when(recordingRepository.save(recordingToSave)).thenReturn(recording);
        when(spaceshipRepository.save(spaceshipToSave)).thenReturn(spaceship);
        when(appearanceRepository.save(parameter)).thenReturn(expected);

        var actual = service.save(parameter);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );

        verify(recordingRepository, times(1)).save(any());
        verify(recordingRepository, times(1)).save(recordingToSave);
        verify(spaceshipRepository, times(1)).save(any());
        verify(spaceshipRepository, times(1)).save(spaceshipToSave);
        verify(appearanceRepository, times(1)).save(any());
        verify(appearanceRepository, times(1)).save(parameter);
        noMoreInteractions();
    }

    @Test
    void saveKoWhenRecordingNotExist() {
        var spaceship = Data.getExpectedSpaceship(1);
        var recording = Data.getExpectedRecording(1);
        recording.setId(9);
        var parameter = new Appearance(null, spaceship, recording);

        when(recordingRepository.findById(9)).thenReturn(Optional.empty());

        var exception = assertThrows(NoSuchElementException.class, () -> service.save(parameter));

        assertAll(
                () -> assertNotNull(exception),
                () -> assertEquals(NoSuchElementException.class, exception.getClass()),
                () -> assertEquals("AppearanceService::save -> recording not found", exception.getMessage())
        );

        verify(recordingRepository, times(1)).findById(any());
        verify(recordingRepository, times(1)).findById(9);
        noMoreInteractions();
    }

    @Test
    void saveKoWhenSpaceshipNotExist() {
        var spaceship = Data.getExpectedSpaceship(1);
        spaceship.setId(9);
        var recording = Data.getExpectedRecording(1);
        var parameter = new Appearance(null, spaceship, recording);

        when(recordingRepository.findById(1)).thenReturn(Optional.of(recording));
        when(spaceshipRepository.findById(9)).thenReturn(Optional.empty());

        var exception = assertThrows(NoSuchElementException.class, () -> service.save(parameter));

        assertAll(
                () -> assertNotNull(exception),
                () -> assertEquals(NoSuchElementException.class, exception.getClass()),
                () -> assertEquals("AppearanceService::save -> spaceship not found", exception.getMessage())
        );

        verify(recordingRepository, times(1)).findById(any());
        verify(recordingRepository, times(1)).findById(1);
        verify(spaceshipRepository, times(1)).findById(any());
        verify(spaceshipRepository, times(1)).findById(9);
        noMoreInteractions();
    }

    private void noMoreInteractions() {
        verifyNoMoreInteractions(recordingRepository);
        verifyNoMoreInteractions(spaceshipRepository);
        verifyNoMoreInteractions(appearanceRepository);
    }
}