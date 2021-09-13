package com.reactive.transaction.backend.entities;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_WRITE;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
@Document
public class Transaction {
    @Id
    private final String id;
    private final Instant date;
    private final double price;
    private final Societe societe;
}
