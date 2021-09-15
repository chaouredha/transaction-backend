package com.reactive.transaction.backend.services;

import static java.time.Duration.ofMillis;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.reactive.transaction.backend.domain.EventDTO;
import com.reactive.transaction.backend.domain.SocieteDTO;
import com.reactive.transaction.backend.domain.TransactionDTO;
import com.reactive.transaction.backend.entities.Societe;
import com.reactive.transaction.backend.entities.Transaction;
import com.reactive.transaction.backend.repositories.TransactionRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final WebClient webClient;

    public TransactionService(TransactionRepository transactionRepository,
            @Qualifier("webClient") WebClient webClient) {
        this.transactionRepository = transactionRepository;
        this.webClient = webClient;
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

    public Flux<EventDTO> events(String id) {
        Flux<EventDTO> eventsTransaction = transactionSocieteStream(id)
                .map(transaction -> new EventDTO(
                        transaction.getDate(),
                        transaction.getId(),
                        transaction.getPrice()
                ));
        Flux<EventDTO> events = webClient
                .get()
                .uri("/event/stream-events/" + id)
                .retrieve()
                .bodyToFlux(EventDTO.class);
        return Flux.merge(eventsTransaction, events);
    }
}
