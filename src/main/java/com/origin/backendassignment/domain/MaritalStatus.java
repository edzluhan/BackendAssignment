package com.origin.backendassignment.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum MaritalStatus {
    @JsonProperty("single") SINGLE("single"),
    @JsonProperty("married") MARRIED("married");

    private String status;

    MaritalStatus(String status) {
        this.status = status;
    }
}
