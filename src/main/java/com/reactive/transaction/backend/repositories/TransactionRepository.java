package com.reactive.transaction.backend.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.reactive.transaction.backend.entities.Societe;
import com.reactive.transaction.backend.entities.Transaction;

import reactor.core.publisher.Flux;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {

    Flux<Transaction> findBySocieteId(String id);

    Flux<Transaction> findBySociete(Societe societe);
}
