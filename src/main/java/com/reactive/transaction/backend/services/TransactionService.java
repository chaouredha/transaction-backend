package com.reactive.transaction.backend.services;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.reactive.transaction.backend.domain.SocieteDTO;
import com.reactive.transaction.backend.domain.TransactionDTO;
import com.reactive.transaction.backend.entities.Societe;
import com.reactive.transaction.backend.entities.Transaction;
import com.reactive.transaction.backend.repositories.SocieteRepository;
import com.reactive.transaction.backend.repositories.TransactionRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Flux<TransactionDTO> transactions() {
        return transactionRepository.findAll()
                .map(this::mapTransaction);
    }

    public Mono<TransactionDTO> transaction(String id) {
        return transactionRepository.findById(id)
                .map(this::mapTransaction);
    }

    public Mono<TransactionDTO> save(TransactionDTO transaction) {
        return transactionRepository.save(
                        new Transaction(
                                null,
                                transaction.getDate(),
                                transaction.getPrice(),
                                new Societe(
                                        transaction.getSociete().getName(),
                                        transaction.getSociete().getName(),
                                        transaction.getSociete().getPrice()
                                )
                        )
                )
                .map(this::mapTransaction);
    }

    public Mono<TransactionDTO> update(String id, TransactionDTO transaction) {
        return transactionRepository.save(
                        new Transaction(
                                id,
                                transaction.getDate(),
                                transaction.getPrice(),
                                new Societe(
                                        transaction.getSociete().getName(),
                                        transaction.getSociete().getName(),
                                        transaction.getSociete().getPrice()
                                )
                        )
                )
                .map(this::mapTransaction);

    }

    private TransactionDTO mapTransaction(Transaction transaction) {
        return new TransactionDTO(
                transaction.getId(),
                transaction.getDate(),
                transaction.getPrice(),
                new SocieteDTO(
                        transaction.getSociete().getName(),
                        transaction.getSociete().getPrice()
                ));
    }

    public Mono<Void> delete(String id) {
        return transactionRepository.deleteById(id);
    }

    public Flux<TransactionDTO> transactionSociete(String id) {
        return transactionRepository.findBySocieteId(id)
                .map(this::mapTransaction);
    }

    public Flux<TransactionDTO> transactionSocieteStream(String id) {
        return transactionRepository.findBySocieteId(id)
                .map(this::mapTransaction)
                .delayElements(ofMillis(200));
    }
}
