package com.origin.backendassignment.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Value
public class Vehicle {
    @NotNull
    @PositiveOrZero(message = "Vehicle year must be an integer equal or greater than 0")
    private Integer year;

    @JsonCreator
    public Vehicle(Integer year) {
        this.year = year;
    }

    public boolean isNew() {
        return LocalDateTime.now().getYear() - year <= 5;
    }
}
