package com.rsa.spaceships.application.services;

import com.rsa.spaceships.domain.models.Appearance;
import com.rsa.spaceships.domain.repositories.AppearanceRepository;
import com.rsa.spaceships.domain.repositories.RecordingRepository;
import com.rsa.spaceships.domain.repositories.SpaceshipRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Objects;

public class AppearanceService {

    private final AppearanceRepository appearanceRepository;

    private final RecordingRepository recordingRepository;

    private final SpaceshipRepository spaceshipRepository;

    public AppearanceService(AppearanceRepository appearanceRepository, RecordingRepository recordingRepository,
                             SpaceshipRepository spaceshipRepository) {
        this.appearanceRepository = appearanceRepository;
        this.recordingRepository = recordingRepository;
        this.spaceshipRepository = spaceshipRepository;
    }

    @Transactional
    public Appearance save(Appearance appearance) {
        var recording = appearance.getRecording();
        var spaceship = appearance.getSpaceship();
        if (Objects.isNull(recording.getId())) {
            appearance.setRecording(recordingRepository.save(recording));
        } else if (recordingRepository.findById(recording.getId()).isEmpty()) {
            throw new NoSuchElementException("AppearanceService::save -> recording not found");
        }
        if (Objects.isNull(spaceship.getId())) {
            appearance.setSpaceship(spaceshipRepository.save(spaceship));
        } else if (spaceshipRepository.findById(spaceship.getId()).isEmpty()) {
            throw new NoSuchElementException("AppearanceService::save -> spaceship not found");
        }
        return appearanceRepository.save(appearance);
    }
}
