package com.reactive.transaction.backend.services;

import org.springframework.stereotype.Service;

import com.reactive.transaction.backend.domain.SocieteDTO;
import com.reactive.transaction.backend.entities.Societe;
import com.reactive.transaction.backend.repositories.SocieteRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SocieteService {
    private final SocieteRepository societeRepository;

    public SocieteService(SocieteRepository societeRepository) {
        this.societeRepository = societeRepository;
    }

    public Flux<SocieteDTO> societes() {
        return societeRepository.findAll()
                .map(this::mapSociete);
    }

    public Mono<SocieteDTO> societeById(String id) {
        return societeRepository.findById(id)
                .map(this::mapSociete);
    }

    public Mono<SocieteDTO> save(SocieteDTO societe) {
        return societeRepository.save(new Societe(null, societe.getName(), societe.getPrice()))
                .map(this::mapSociete);
    }

    public Mono<Void> delete(String id) {
        return societeRepository.deleteById(id);
    }

    public Mono<SocieteDTO> update(String id, SocieteDTO societe) {
        return societeRepository.save(new Societe(id, societe.getName(), societe.getPrice()))
                .map(this::mapSociete);
    }

    private SocieteDTO mapSociete(Societe societe) {
        return new SocieteDTO(societe.getName(), societe.getPrice());
    }
}
