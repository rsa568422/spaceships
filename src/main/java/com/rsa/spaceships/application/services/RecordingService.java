package com.rsa.spaceships.application.services;

import com.rsa.spaceships.domain.models.Recording;
import com.rsa.spaceships.domain.repositories.RecordingRepository;

public class RecordingService {

    private final RecordingRepository recordingRepository;

    public RecordingService(RecordingRepository recordingRepository) {
        this.recordingRepository = recordingRepository;
    }

    public Iterable<Recording> findBySpaceshipNameLike(String name) {
        return recordingRepository.findBySpaceshipNameLike(name);
    }
}
