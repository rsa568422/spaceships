package com.rsa.spaceships.application.services;

import com.rsa.spaceships.domain.models.User;
import com.rsa.spaceships.domain.repositories.UserRepository;

import java.util.Optional;

public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
