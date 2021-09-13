package com.reactive.transaction.backend.controllers;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reactive.transaction.backend.domain.SocieteDTO;
import com.reactive.transaction.backend.services.SocieteService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/societes")
public class SocieteController {

    private final SocieteService societeService;

    public SocieteController(SocieteService societeService) {
        this.societeService = societeService;
    }

    @GetMapping
    public Flux<SocieteDTO> societes() {
        return societeService.societes();
    }

    @GetMapping("{id}")
    public Mono<SocieteDTO> societe(@PathVariable String id) {
        return societeService.societeById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Mono<SocieteDTO> save(@RequestBody SocieteDTO societe) {
        return societeService.save(societe);
    }

    @PutMapping("/{id}")
    public Mono<SocieteDTO> update(@PathVariable String id,
            @RequestBody SocieteDTO societe) {
        return societeService.update(id, societe);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return societeService.delete(id);
    }
}
