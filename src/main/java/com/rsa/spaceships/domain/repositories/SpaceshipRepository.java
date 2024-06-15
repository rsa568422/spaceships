package com.rsa.spaceships.domain.repositories;

import com.rsa.spaceships.domain.models.Spaceship;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SpaceshipRepository {

    Iterable<Spaceship> findAll(Pageable pageable);

    Optional<Spaceship> findById(Integer id);

    Iterable<Spaceship> findByNameLike(String name);

    Iterable<Spaceship> findByRecordingNameLike(String name);

    Spaceship save(Spaceship spaceship);

    void deleteById(Integer id);
}
