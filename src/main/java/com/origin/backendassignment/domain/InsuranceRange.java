package com.origin.backendassignment.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import static java.util.Objects.isNull;

public enum InsuranceRange {
    @JsonProperty("ineligible")
    INELIGIBLE("ineligible"),
    @JsonProperty("economic")
    ECONOMIC("economic"),
    @JsonProperty("regular")
    REGULAR("regular"),
    @JsonProperty("responsible")
    RESPONSIBLE("responsible");

    private final String value;

    InsuranceRange(String value) {
        this.value = value;
    }

    public static InsuranceRange getFor(Integer risk) {
        if (isNull(risk)) {
            return INELIGIBLE;
        }

        if (risk >= 3) {
            return RESPONSIBLE;
        }

        if (risk >= 1) {
            return REGULAR;
        }

        return ECONOMIC;
    }
}
