package com.rsa.spaceships.application.services;

import com.rsa.spaceships.Data;
import com.rsa.spaceships.domain.models.Appearance;
import com.rsa.spaceships.domain.models.Spaceship;
import com.rsa.spaceships.domain.repositories.AppearanceRepository;
import com.rsa.spaceships.domain.repositories.SpaceshipRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest
class SpaceshipServiceTest {

    @InjectMocks
    private SpaceshipService service;

    @Mock
    private AppearanceRepository appearanceRepository;

    @Mock
    private SpaceshipRepository spaceshipRepository;

    @Test
    void findAll() {
        var page = PageRequest.of(0, 10);
        var expected = Data.EXPECTED_SPACESHIPS.entrySet()
                .stream()
                .map(entry -> new Spaceship(entry.getKey(), entry.getValue()))
                .toList();

        when(spaceshipRepository.findAll(page)).thenReturn(expected);

        var actual = service.findAll(page);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertIterableEquals(expected, actual)
        );

        verify(spaceshipRepository, times(1)).findAll(any());
        verify(spaceshipRepository, times(1)).findAll(page);
        noMoreInteractions();
    }

    @Test
    void findById() {
        var expected = Optional.of(Data.getExpectedSpaceship(1));

        when(spaceshipRepository.findById(1)).thenReturn(Optional.of(Data.getExpectedSpaceship(1)));

        var actual = service.findById(1);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertTrue(actual.isPresent()),
                () -> assertEquals(expected, actual)
        );

        verify(spaceshipRepository, times(1)).findById(any());
        verify(spaceshipRepository, times(1)).findById(1);
        noMoreInteractions();
    }

    @Test
    void findByNameLike() {
        var name = "X-Wing";
        var expected = Data.EXPECTED_SPACESHIPS.entrySet()
                .stream()
                .map(entry -> new Spaceship(entry.getKey(), entry.getValue()))
                .toList();

        when(spaceshipRepository.findByNameLike(name)).thenReturn(expected);

        var actual = service.findByNameLike(name);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertIterableEquals(expected, actual)
        );

        verify(spaceshipRepository, times(1)).findByNameLike(any());
        verify(spaceshipRepository, times(1)).findByNameLike(name);
        noMoreInteractions();
    }

    @Test
    void findByRecordingNameLike() {
        var name = "Star Wars: Episodio IV - Una nueva esperanza";
        var expected = Data.EXPECTED_SPACESHIPS.entrySet()
                .stream()
                .map(entry -> new Spaceship(entry.getKey(), entry.getValue()))
                .toList();

        when(spaceshipRepository.findByRecordingNameLike(name)).thenReturn(expected);

        var actual = service.findByRecordingNameLike(name);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertIterableEquals(expected, actual)
        );

        verify(spaceshipRepository, times(1)).findByRecordingNameLike(any());
        verify(spaceshipRepository, times(1)).findByRecordingNameLike(name);
        noMoreInteractions();
    }

    @Test
    void save() {
        var spaceship = new Spaceship(null, "X-Wing");
        var expected = Data.getExpectedSpaceship(1);

        when(spaceshipRepository.save(spaceship)).thenReturn(expected);

        var actual = service.save(spaceship);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );

        verify(spaceshipRepository, times(1)).save(any());
        verify(spaceshipRepository, times(1)).save(spaceship);
        noMoreInteractions();
    }

    @Test
    void deleteById() {
        var spaceship = Data.getExpectedSpaceship(1);
        var appearances = Data.EXPECTED_RECORDINGS.keySet()
                .stream()
                .map(Data::getExpectedRecording)
                .map(recording -> new Appearance(recording.getId(), spaceship, recording))
                .toList();

        when(appearanceRepository.findBySpaceshipId(1)).thenReturn(appearances);

        assertDoesNotThrow(() -> service.deleteById(1));

        verify(appearanceRepository, times(1)).findBySpaceshipId(any());
        verify(appearanceRepository, times(1)).findBySpaceshipId(1);
        verify(appearanceRepository, times(9)).deleteById(any());
        verify(appearanceRepository, times(1)).deleteById(1);
        verify(appearanceRepository, times(1)).deleteById(2);
        verify(appearanceRepository, times(1)).deleteById(3);
        verify(appearanceRepository, times(1)).deleteById(4);
        verify(appearanceRepository, times(1)).deleteById(5);
        verify(appearanceRepository, times(1)).deleteById(6);
        verify(appearanceRepository, times(1)).deleteById(7);
        verify(appearanceRepository, times(1)).deleteById(8);
        verify(appearanceRepository, times(1)).deleteById(9);
        verify(spaceshipRepository, times(1)).deleteById(any());
        verify(spaceshipRepository, times(1)).deleteById(1);
        noMoreInteractions();
    }

    private void noMoreInteractions() {
        verifyNoMoreInteractions(appearanceRepository);
        verifyNoMoreInteractions(spaceshipRepository);
    }
}