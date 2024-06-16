package com.rsa.spaceships.infrastructure.adapters;

import com.rsa.spaceships.domain.models.Spaceship;
import com.rsa.spaceships.domain.repositories.SpaceshipRepository;
import com.rsa.spaceships.infrastructure.mappers.SpaceshipMapper;
import com.rsa.spaceships.infrastructure.repositories.SpaceshipJpaRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SpaceshipRepositoryAdapter implements SpaceshipRepository {

    private final SpaceshipJpaRepository repository;

    private final SpaceshipMapper mapper;

    public SpaceshipRepositoryAdapter(SpaceshipJpaRepository repository, SpaceshipMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Cacheable("spaceships")
    public Iterable<Spaceship> findAll(Pageable pageable) {
        return mapper.toSpaceships(repository.findAll(pageable));
    }

    @Override
    public Optional<Spaceship> findById(Integer id) {
        return repository.findById(id).map(mapper::toSpaceship);
    }

    @Override
    public Iterable<Spaceship> findByNameLike(String name) {
        return mapper.toSpaceships(repository.findByNameLike(name));
    }

    @Override
    public Iterable<Spaceship> findByRecordingNameLike(String name) {
        return mapper.toSpaceships(repository.findByRecordingNameLike(name));
    }

    @Override
    public Spaceship save(Spaceship spaceship) {
        return mapper.toSpaceship(repository.save(mapper.toSpaceshipEntity(spaceship)));
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
