package com.rsa.spaceships.infrastructure.repositories;

import com.rsa.spaceships.infrastructure.entities.RecordingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecordingJpaRepository extends JpaRepository<RecordingEntity, Integer> {

    @Query("select r from AppearanceEntity a join a.recording r join a.spaceship s where lower(s.name) like lower(concat('%', ?1, '%'))")
    Iterable<RecordingEntity> findBySpaceshipNameLike(String name);
}
