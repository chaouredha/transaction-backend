package com.reactive.transaction.backend.services;

import static reactor.test.StepVerifier.create;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.reactive.transaction.backend.domain.SocieteDTO;
import com.reactive.transaction.backend.repositories.SocieteRepository;

@DataMongoTest
@RunWith(SpringRunner.class)
public class SocieteServiceTest {
    List<SocieteDTO> societes = List.of(
            new SocieteDTO("SG", 350.9015778659964),
            new SocieteDTO("BMCE", 673.1383733731053),
            new SocieteDTO("AWB", 989.8814653980525),
            new SocieteDTO("AXA", 475.90093705736444)
    );

    @Autowired
    private SocieteRepository societeRepository;

    @InjectMocks
    private SocieteService societeService;

    @Before
    public void initialize() {
        societeService = new SocieteService(societeRepository);
    }

    @Test
    public void societes() {
        create(societeService.societes())
                .expectNextSequence(societes)
                .verifyComplete();
    }

    @Test
    public void societeById() {
        create(societeService.societeById("SG"))
                .expectNext(new SocieteDTO("SG", 350.9015778659964))
                .verifyComplete();
    }

}

