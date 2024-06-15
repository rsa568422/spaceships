package com.rsa.spaceships.application.services;

import com.rsa.spaceships.domain.models.Spaceship;
import com.rsa.spaceships.domain.repositories.AppearanceRepository;
import com.rsa.spaceships.domain.repositories.SpaceshipRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class SpaceshipService {

    private final AppearanceRepository appearanceRepository;

    private final SpaceshipRepository spaceshipRepository;

    public SpaceshipService(AppearanceRepository appearanceRepository, SpaceshipRepository spaceshipRepository) {
        this.appearanceRepository = appearanceRepository;
        this.spaceshipRepository = spaceshipRepository;
    }

    public Iterable<Spaceship> findAll(Pageable pageable) {
        return spaceshipRepository.findAll(pageable);
    }

    public Optional<Spaceship> findById(Integer id) {
        return spaceshipRepository.findById(id);
    }

    public Iterable<Spaceship> findByNameLike(String name) {
        return spaceshipRepository.findByNameLike(name);
    }

    public Iterable<Spaceship> findByRecordingNameLike(String name){
        return spaceshipRepository.findByRecordingNameLike(name);
    }

    public Spaceship save(Spaceship spaceship) {
        return spaceshipRepository.save(spaceship);
    }

    @Transactional
    public void deleteById(Integer id) {
        appearanceRepository.findBySpaceshipId(id)
                .forEach(appearance -> appearanceRepository.deleteById(appearance.getId()));
        spaceshipRepository.deleteById(id);
    }
}
