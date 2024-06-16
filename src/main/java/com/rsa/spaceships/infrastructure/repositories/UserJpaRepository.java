package com.rsa.spaceships.infrastructure.repositories;

import com.rsa.spaceships.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserJpaRepository extends JpaRepository<UserEntity, Integer> {

    @Query("select u from UserEntity u where u.username = ?1")
    Iterable<UserEntity> findByUsername(String username);
}
