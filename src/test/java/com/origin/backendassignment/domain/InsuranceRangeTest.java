package com.origin.backendassignment.domain;

import org.junit.jupiter.api.Test;

import static com.origin.backendassignment.domain.InsuranceRange.ECONOMIC;
import static com.origin.backendassignment.domain.InsuranceRange.INELIGIBLE;
import static com.origin.backendassignment.domain.InsuranceRange.REGULAR;
import static com.origin.backendassignment.domain.InsuranceRange.RESPONSIBLE;
import static com.origin.backendassignment.domain.InsuranceRange.getFor;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InsuranceRangeTest {

    @Test
    void returnsResponsibleForThreeAndAbove() {
        InsuranceRange three = getFor(3);
        InsuranceRange above = getFor(4);

        assertEquals(RESPONSIBLE, three);
        assertEquals(RESPONSIBLE, above);
    }

    @Test
    void returnsRegularForOneAndTwo() {
        InsuranceRange one = getFor(1);
        InsuranceRange two = getFor(2);

        assertEquals(REGULAR, one);
        assertEquals(REGULAR, two);
    }

    @Test
    void returnsEconomicForZeroAndBelow() {
        InsuranceRange zero = getFor(0);
        InsuranceRange below = getFor(-2);

        assertEquals(ECONOMIC, zero);
        assertEquals(ECONOMIC, below);
    }

    @Test
    void returnsIneligibleForNull() {
        InsuranceRange nullValue = getFor(null);

        assertEquals(INELIGIBLE, nullValue);
    }
}