package com.rsa.spaceships.domain.repositories;

import com.rsa.spaceships.domain.models.Recording;

import java.util.Optional;

public interface RecordingRepository {

    Iterable<Recording> findBySpaceshipNameLike(String name);

    Optional<Recording> findById(Integer id);

    Recording save(Recording recording);
}
