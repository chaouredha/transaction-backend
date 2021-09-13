package com.reactive.transaction.backend.domain;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_WRITE;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class TransactionDTO {
    private String id;
    private final Instant date;
    private final double price;
    @JsonProperty(access = WRITE_ONLY)
    private final SocieteDTO societe;
}
