package com.rsa.spaceships.infrastructure.controllers;

import com.rsa.spaceships.application.services.SpaceshipService;
import com.rsa.spaceships.domain.models.Spaceship;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.Objects;

@RestController
@RequestMapping("/spaceship")
public class SpaceshipController {

    private final SpaceshipService service;

    public SpaceshipController(SpaceshipService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Spaceship> findAll(@PageableDefault(size = 20) Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Spaceship findById(@PathVariable("id") Integer id) {
        return service.findById(id)
                .orElseThrow(() -> new NoSuchElementException("SpaceshipService::findById -> spaceship not found"));
    }

    @GetMapping("/name/{name}")
    public Iterable<Spaceship> findByNameLike(@PathVariable("name") String name) {
        return service.findByNameLike(name);
    }

    @GetMapping("/recording/{name}")
    public Iterable<Spaceship> findByRecordingNameLike(@PathVariable("name") String name) {
        return service.findByRecordingNameLike(name);
    }

    @PostMapping
    public Spaceship create(@RequestBody Spaceship spaceship) {
        if (Objects.nonNull(spaceship.getId())) {
            spaceship.setId(null);
        }
        return service.save(spaceship);
    }

    @PutMapping
    public Spaceship update(@RequestBody Spaceship spaceship) {
        if (service.findById(spaceship.getId()).isEmpty()) {
            throw new NoSuchElementException("SpaceshipService::update -> spaceship not found");
        }
        return service.save(spaceship);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        if (service.findById(id).isEmpty()) {
            throw new NoSuchElementException("SpaceshipService::deleteById -> spaceship not found");
        }
        service.deleteById(id);
    }
}
