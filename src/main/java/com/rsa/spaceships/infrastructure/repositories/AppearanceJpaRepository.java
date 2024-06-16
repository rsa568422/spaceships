package com.rsa.spaceships.infrastructure.repositories;

import com.rsa.spaceships.infrastructure.entities.AppearanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppearanceJpaRepository extends JpaRepository<AppearanceEntity, Integer> {

    @Query("select a from AppearanceEntity a where a.spaceship.id = ?1")
    Iterable<AppearanceEntity> findBySpaceshipId(Integer id);
}
