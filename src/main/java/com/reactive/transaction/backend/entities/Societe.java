package com.reactive.transaction.backend.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Value;

@Value
@Document
public class Societe {
    @Id
    private final String id;
    private final String name;
    private final double price;
}
