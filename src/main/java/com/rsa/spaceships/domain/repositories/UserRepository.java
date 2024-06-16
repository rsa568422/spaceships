package com.rsa.spaceships.domain.repositories;

import com.rsa.spaceships.domain.models.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsername(String name);
}
