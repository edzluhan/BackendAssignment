package com.origin.backendassignment.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class House {

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
