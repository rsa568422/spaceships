package com.rsa.spaceships.infrastructure.adapters;

import com.rsa.spaceships.Data;
import com.rsa.spaceships.infrastructure.mappers.RecordingMapper;
import com.rsa.spaceships.infrastructure.repositories.RecordingJpaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
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
class RecordingRepositoryAdapterTest {

    @InjectMocks
    private RecordingRepositoryAdapter adapter;

    @Mock
    private RecordingJpaRepository repository;

    @Mock
    private RecordingMapper mapper;

    @Test
    void findBySpaceshipNameLike() {
        var name = "X-Wing";
        var entities = Data.EXPECTED_RECORDINGS.keySet().stream().map(Data::getExpectedRecordingEntity).toList();
        var expected = Data.EXPECTED_RECORDINGS.keySet().stream().map(Data::getExpectedRecording).toList();

        when(repository.findBySpaceshipNameLike(name.toLowerCase())).thenReturn(entities);
        when(mapper.toRecordings(entities)).thenReturn(expected);

        var actual = adapter.findBySpaceshipNameLike(name);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertIterableEquals(expected, actual)
        );

        verify(repository, times(1)).findBySpaceshipNameLike(any());
        verify(repository, times(1)).findBySpaceshipNameLike(name.toLowerCase());
        verify(mapper, times(1)).toRecordings(any());
        verify(mapper, times(1)).toRecordings(entities);
        noMoreInteractions();
    }

    @Test
    void findById() {
        var entity = Data.getExpectedRecordingEntity(1);
        var expected = Data.getExpectedRecording(1);

        when(repository.findById(1)).thenReturn(Optional.of(entity));
        when(mapper.toRecording(entity)).thenReturn(expected);

        var actual = adapter.findById(1);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertTrue(actual.isPresent()),
                () -> assertEquals(expected, actual.orElse(null))
        );

        verify(repository, times(1)).findById(any());
        verify(repository, times(1)).findById(1);
        verify(mapper, times(1)).toRecording(any());
        verify(mapper, times(1)).toRecording(entity);
        noMoreInteractions();
    }

    @Test
    void save() {
        var recordingEntity = Data.getExpectedRecordingEntity(1);
        recordingEntity.setId(null);
        var recording = Data.getExpectedRecording(1);
        recording.setId(null);
        var expectedEntity = Data.getExpectedRecordingEntity(1);
        var expected = Data.getExpectedRecording(1);

        when(repository.save(recordingEntity)).thenReturn(expectedEntity);
        when(mapper.toRecordingEntity(recording)).thenReturn(recordingEntity);
        when(mapper.toRecording(expectedEntity)).thenReturn(expected);

        var actual = adapter.save(recording);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );

        verify(repository, times(1)).save(any());
        verify(repository, times(1)).save(recordingEntity);
        verify(mapper, times(1)).toRecordingEntity(any());
        verify(mapper, times(1)).toRecordingEntity(recording);
        verify(mapper, times(1)).toRecording(any());
        verify(mapper, times(1)).toRecording(expectedEntity);
    }

    private void noMoreInteractions() {
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }
}