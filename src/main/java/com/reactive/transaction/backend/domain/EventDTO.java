package com.reactive.transaction.backend.domain;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class EventDTO {
    private final Instant instant;
    private final String societeId;
    private final double value;

}
