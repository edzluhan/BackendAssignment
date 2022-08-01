package com.origin.backendassignment.request;

import com.origin.backendassignment.domain.InsuranceRange;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InsuranceRangeTest {

    @Test
    void ineligibleWhenNull() {
        assertEquals(InsuranceRange.INELIGIBLE, InsuranceRange.getFor(null));
    }

    @Test
    void economicWhenZeroOrNegative() {
        assertEquals(InsuranceRange.ECONOMIC, InsuranceRange.getFor(-1));
        assertEquals(InsuranceRange.ECONOMIC, InsuranceRange.getFor(0));
    }

    @Test
    void regularWhenOneOrTwo() {
        assertEquals(InsuranceRange.REGULAR, InsuranceRange.getFor(1));
        assertEquals(InsuranceRange.REGULAR, InsuranceRange.getFor(2));
    }

    @Test
    void responsibleWhenThreeOrGreater() {
        assertEquals(InsuranceRange.RESPONSIBLE, InsuranceRange.getFor(3));
        assertEquals(InsuranceRange.RESPONSIBLE, InsuranceRange.getFor(4));
    }
}