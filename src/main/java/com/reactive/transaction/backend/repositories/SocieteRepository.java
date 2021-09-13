package com.reactive.transaction.backend.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.reactive.transaction.backend.entities.Societe;

@Repository
public interface SocieteRepository extends ReactiveMongoRepository<Societe, String> {
}
