package com.rsa.spaceships.infrastructure.mappers;

import com.rsa.spaceships.domain.models.Appearance;
import com.rsa.spaceships.infrastructure.entities.AppearanceEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RecordingMapper.class, SpaceshipMapper.class})
public interface AppearanceMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "recording", target = "recording")
    @Mapping(source = "spaceship", target = "spaceship")
    Appearance toAppearance(AppearanceEntity appearanceEntity);

    Iterable<Appearance> toAppearances(Iterable<AppearanceEntity> appearanceEntities);

    @InheritInverseConfiguration
    AppearanceEntity toAppearanceEntity(Appearance appearance);
}
