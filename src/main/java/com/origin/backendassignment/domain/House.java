package com.origin.backendassignment.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class House {

    @NotNull(message = "ownership_status must not be null")
    private OwnershipStatus ownershipStatus;

    @JsonCreator
    public House(@JsonProperty("ownership_status") OwnershipStatus ownershipStatus) {
        this.ownershipStatus = ownershipStatus;
    }

    public enum OwnershipStatus {
        @JsonProperty("owned") OWNED("owned"),
        @JsonProperty("mortgaged") MORTGAGED("mortgaged");

        private String status;

        OwnershipStatus(String status) {
            this.status = status;
        }

    }
}
