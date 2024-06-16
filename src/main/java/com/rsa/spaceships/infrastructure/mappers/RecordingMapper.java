package com.rsa.spaceships.infrastructure.mappers;

import com.rsa.spaceships.domain.models.Recording;
import com.rsa.spaceships.infrastructure.entities.RecordingEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecordingMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    Recording toRecording(RecordingEntity recordingEntity);

    Iterable<Recording> toRecordings(Iterable<RecordingEntity> recordingEntities);

    @InheritInverseConfiguration
    RecordingEntity toRecordingEntity(Recording recording);
}
