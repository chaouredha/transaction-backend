package com.reactive.transaction.backend.controllers;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reactive.transaction.backend.domain.EventDTO;
import com.reactive.transaction.backend.domain.TransactionDTO;
import com.reactive.transaction.backend.services.TransactionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    Flux<TransactionDTO> transactions() {
        return transactionService.transactions();
    }

    @GetMapping(value = "/stream", produces = TEXT_EVENT_STREAM_VALUE)
    Flux<TransactionDTO> streamTransactions() {
        return transactionService.transactions();
    }

    @GetMapping("/{id}")
    Mono<TransactionDTO> transaction(@PathVariable String id) {
        return transactionService.transaction(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    Mono<TransactionDTO> save(@RequestBody TransactionDTO transaction) {
        return transactionService.save(transaction);
    }

    @PutMapping("/{id}")
    Mono<TransactionDTO> update(@PathVariable String id,
            @RequestBody TransactionDTO transaction) {
        return transactionService.update(id, transaction);
    }

    @DeleteMapping("/{id}")
    Mono<Void> delete(@PathVariable String id) {
        return transactionService.delete(id);
    }

    @GetMapping(value = "/societe/{id}")
    Flux<TransactionDTO> transactionSociete(@PathVariable String id) {
        return transactionService.transactionSociete(id);
    }

    @GetMapping(value = "/streamSociete/{id}", produces = TEXT_EVENT_STREAM_VALUE)
    Flux<TransactionDTO> transactionSocieteStream(@PathVariable String id) {
        return transactionService.transactionSocieteStream(id);
    }

    @GetMapping(value = "/events/{id}", produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<EventDTO> events(@PathVariable String id) {
        return transactionService.events(id);
    }
}
