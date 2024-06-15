package com.rsa.spaceships.infrastructure.controllers;


import com.rsa.spaceships.application.services.AppearanceService;
import com.rsa.spaceships.domain.models.Appearance;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appearance")
public class AppearanceController {

    private final AppearanceService service;

    public AppearanceController(AppearanceService service) {
        this.service = service;
    }

    @PostMapping
    public Appearance create(@RequestBody Appearance appearance) {
        return service.save(appearance);
    }
}
