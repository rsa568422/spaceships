package com.rsa.spaceships.infrastructure.configurations;

import com.rsa.spaceships.application.services.AppearanceService;
import com.rsa.spaceships.application.services.RecordingService;
import com.rsa.spaceships.application.services.SpaceshipService;
import com.rsa.spaceships.application.services.UserService;
import com.rsa.spaceships.domain.repositories.AppearanceRepository;
import com.rsa.spaceships.domain.repositories.RecordingRepository;
import com.rsa.spaceships.domain.repositories.SpaceshipRepository;
import com.rsa.spaceships.domain.repositories.UserRepository;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
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

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

    @Bean
    public OpenAPI openAPI() {
        var info = new Info()
                .title("Spaceship - W2M")
                .version("0.0.1-SNAPSHOT")
                .description("Proyecto para prueba técnica de W2M")
                .termsOfService("https://github.com/rsa568422/spaceships");
        return new OpenAPI().info(info);
    }
}
