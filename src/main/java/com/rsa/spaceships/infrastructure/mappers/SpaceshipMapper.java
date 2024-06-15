package com.rsa.spaceships.infrastructure.mappers;

import com.rsa.spaceships.domain.models.Spaceship;
import com.rsa.spaceships.infrastructure.entities.SpaceshipEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface SpaceshipMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    Spaceship toSpaceship(SpaceshipEntity spaceshipEntity);

    Iterable<Spaceship> toSpaceships(Iterable<SpaceshipEntity> spaceshipEntities);

    default Iterable<Spaceship> toSpaceships(Page<SpaceshipEntity> spaceshipEntities) {
        return spaceshipEntities.map(this::toSpaceship).stream().toList();
    }

    @InheritInverseConfiguration
    SpaceshipEntity toSpaceshipEntity(Spaceship spaceship);
}
