package com.rsa.spaceships.infrastructure.controllers;

import com.rsa.spaceships.application.services.RecordingService;
import com.rsa.spaceships.domain.models.Recording;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recording")
public class RecordingController {

    private final RecordingService service;

    public RecordingController(RecordingService service) {
        this.service = service;
    }

    @GetMapping("/{name}")
    public Iterable<Recording> findBySpaceshipNameLike(@PathVariable String name) {
        return service.findBySpaceshipNameLike(name);
    }
}
