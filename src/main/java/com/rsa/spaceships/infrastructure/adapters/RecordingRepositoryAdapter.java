package com.rsa.spaceships.infrastructure.adapters;

import com.rsa.spaceships.domain.models.Recording;
import com.rsa.spaceships.domain.repositories.RecordingRepository;
import com.rsa.spaceships.infrastructure.mappers.RecordingMapper;
import com.rsa.spaceships.infrastructure.repositories.RecordingJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RecordingRepositoryAdapter implements RecordingRepository {

    private final RecordingJpaRepository repository;

    private final RecordingMapper mapper;

    public RecordingRepositoryAdapter(RecordingJpaRepository repository, RecordingMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Iterable<Recording> findBySpaceshipNameLike(String name) {
        return mapper.toRecordings(repository.findBySpaceshipNameLike(name.toLowerCase()));
    }

    @Override
    public Optional<Recording> findById(Integer id) {
        return repository.findById(id).map(mapper::toRecording);
    }

    @Override
    public Recording save(Recording recording) {
        return mapper.toRecording(repository.save(mapper.toRecordingEntity(recording)));
    }
}
