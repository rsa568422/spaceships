package com.rsa.spaceships.infrastructure.adapters;

import com.rsa.spaceships.domain.models.User;
import com.rsa.spaceships.domain.repositories.UserRepository;
import com.rsa.spaceships.infrastructure.mappers.UserMapper;
import com.rsa.spaceships.infrastructure.repositories.UserJpaRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository repository;

    private final UserMapper mapper;

    public UserRepositoryAdapter(UserJpaRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        var users =  Streamable.of(repository.findByUsername(username)).toList();
        return users.isEmpty() ? Optional.empty() : Optional.of(mapper.toUser(users.getFirst()));
    }
}
