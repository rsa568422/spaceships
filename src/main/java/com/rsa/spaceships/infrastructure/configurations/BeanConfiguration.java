package com.rsa.spaceships.infrastructure.configurations;

import com.rsa.spaceships.application.services.AppearanceService;
import com.rsa.spaceships.application.services.RecordingService;
import com.rsa.spaceships.application.services.SpaceshipService;
import com.rsa.spaceships.domain.repositories.AppearanceRepository;
import com.rsa.spaceships.domain.repositories.RecordingRepository;
import com.rsa.spaceships.domain.repositories.SpaceshipRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public AppearanceService appearanceService(AppearanceRepository appearanceRepository,
                                               RecordingRepository recordingRepository,
                                               SpaceshipRepository spaceshipRepository) {
        return new AppearanceService(appearanceRepository, recordingRepository, spaceshipRepository);
    }

    @Bean
    public RecordingService recordingService(RecordingRepository recordingRepository) {
        return new RecordingService(recordingRepository);
    }

    @Bean
    public SpaceshipService spaceshipService(AppearanceRepository appearanceRepository,
                                             SpaceshipRepository spaceshipRepository) {
        return new SpaceshipService(appearanceRepository, spaceshipRepository);
    }
}
