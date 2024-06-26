package com.rsa.spaceships.infrastructure.mappers;

import com.rsa.spaceships.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecordingMapperTest {

    @Autowired
    private RecordingMapper mapper;

    @Test
    void toRecording() {
        var entity = Data.getExpectedRecordingEntity(1);
        var expected = Data.getExpectedRecording(1);

        var actual = mapper.toRecording(entity);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void nullToRecording() {
        assertNull(mapper.toRecording(null));
    }

    @Test
    void toRecordings() {
        var entities = Data.EXPECTED_RECORDINGS.keySet().stream().map(Data::getExpectedRecordingEntity).toList();
        var expected = Data.EXPECTED_RECORDINGS.keySet().stream().map(Data::getExpectedRecording).toList();

        var actual = mapper.toRecordings(entities);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertIterableEquals(expected, actual)
        );
    }

    @Test
    void nullToRecordings() {
        assertNull(mapper.toRecordings(null));
    }

    @Test
    void toRecordingEntity() {
        var recording = Data.getExpectedRecording(1);
        var expected = Data.getExpectedRecordingEntity(1);

        var actual = mapper.toRecordingEntity(recording);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void nullToRecordingEntity() {
        assertNull(mapper.toRecordingEntity(null));
    }
}