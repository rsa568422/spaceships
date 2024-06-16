package com.rsa.spaceships.infrastructure.repositories;

import com.rsa.spaceships.infrastructure.entities.SpaceshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SpaceshipJpaRepository extends JpaRepository<SpaceshipEntity, Integer> {

    @Query("select s from SpaceshipEntity s where lower(s.name) like lower(concat('%', ?1, '%'))")
    Iterable<SpaceshipEntity> findByNameLike(String name);

    @Query("select s from AppearanceEntity a join a.spaceship s join a.recording r where lower(r.name) like lower(concat('%', ?1, '%'))")
    Iterable<SpaceshipEntity> findByRecordingNameLike(String name);
}
