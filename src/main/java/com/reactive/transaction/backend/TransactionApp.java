package com.reactive.transaction.backend;

import static java.lang.Math.random;

import java.time.Instant;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.reactive.transaction.backend.entities.Transaction;
import com.reactive.transaction.backend.repositories.SocieteRepository;
import com.reactive.transaction.backend.repositories.TransactionRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class TransactionApp {

    public static void main(String[] args) {
        SpringApplication.run(TransactionApp.class, args);
    }

/*
    @Bean
    CommandLineRunner start(SocieteRepository societeRepository,
            TransactionRepository transactionRepository) {
        return arg -> {
            Stream.of("SG", "AWB", "BMCE", "AXA")
                    .forEach(societe -> {
                        societeRepository.findById(societe)
                                .subscribe(s -> {
                                    IntStream.range(0, 100)
                                            .mapToObj(i -> new Transaction(
                                                    null,
                                                    Instant.now(),
                                                    s.getPrice() * (1 + random() * 12 - 6) / 100,
                                                    s
                                            ))
                                            .forEach(transaction -> transactionRepository.save(transaction)
                                                    .subscribe(t -> System.out.println(t.toString())));
                                });
                    });
        };
    }
*/

}
