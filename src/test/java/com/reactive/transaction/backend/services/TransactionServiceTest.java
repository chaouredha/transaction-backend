package com.reactive.transaction.backend.services;

import static reactor.test.StepVerifier.create;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.reactive.transaction.backend.repositories.TransactionRepository;

@DataMongoTest
@RunWith(SpringRunner.class)
public class TransactionServiceTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @Before
    public void initialize() {
        transactionService = new TransactionService(transactionRepository);
    }

    @Test
    public void transaction() {
        create(transactionService.transaction("613f6805eff8750f4aa48c8b"))
                .expectNextCount(1)
                .verifyComplete();
    }

}
