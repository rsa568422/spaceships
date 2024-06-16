package com.rsa.spaceships.infrastructure.mappers;

import com.rsa.spaceships.domain.models.User;
import com.rsa.spaceships.infrastructure.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "role", target = "role")
    User toUser(UserEntity userEntity);
}
