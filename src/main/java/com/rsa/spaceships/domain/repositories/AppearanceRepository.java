package com.rsa.spaceships.domain.repositories;

import com.rsa.spaceships.domain.models.Appearance;

public interface AppearanceRepository {

    Iterable<Appearance> findBySpaceshipId(Integer id);

    Appearance save(Appearance appearance);

    void deleteById(Integer id);
}
