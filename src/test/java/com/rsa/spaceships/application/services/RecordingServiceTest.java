package com.rsa.spaceships.application.services;

import com.rsa.spaceships.Data;
import com.rsa.spaceships.domain.models.Recording;
import com.rsa.spaceships.domain.repositories.RecordingRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class RecordingServiceTest {

    @InjectMocks
    private RecordingService service;

    @Mock
    private RecordingRepository repository;

    @Test
    void findBySpaceshipNameLike() {
        var name = "X-Wing";
        var expected = Data.EXPECTED_RECORDINGS.entrySet()
                .stream()
                .map(entry -> new Recording(entry.getKey(), entry.getValue()))
                .toList();

        when(repository.findBySpaceshipNameLike(name)).thenReturn(expected);

        var actual = service.findBySpaceshipNameLike(name);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertIterableEquals(expected, actual)
        );

        verify(repository, times(1)).findBySpaceshipNameLike(any());
        verify(repository, times(1)).findBySpaceshipNameLike(name);
    }
}