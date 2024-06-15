package com.rsa.spaceships.infrastructure.adapters;

import com.rsa.spaceships.domain.models.Appearance;
import com.rsa.spaceships.domain.repositories.AppearanceRepository;
import com.rsa.spaceships.infrastructure.mappers.AppearanceMapper;
import com.rsa.spaceships.infrastructure.repositories.AppearanceJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AppearanceRepositoryAdapter implements AppearanceRepository {

    private final AppearanceJpaRepository repository;

    private final AppearanceMapper mapper;

    public AppearanceRepositoryAdapter(AppearanceJpaRepository repository, AppearanceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Iterable<Appearance> findBySpaceshipId(Integer id) {
        return mapper.toAppearances(repository.findBySpaceshipId(id));
    }

    @Override
    public Appearance save(Appearance appearance) {
        return mapper.toAppearance(repository.save(mapper.toAppearanceEntity(appearance)));
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
