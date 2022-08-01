package com.origin.backendassignment.domain.rules;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static com.origin.backendassignment.domain.InsuranceType.AUTO;
import static com.origin.backendassignment.domain.InsuranceType.DISABILITY;
import static com.origin.backendassignment.domain.InsuranceType.HOME;
import static com.origin.backendassignment.domain.InsuranceType.LIFE;
import static org.junit.jupiter.api.Assertions.*;

class HasNoVehicleRuleTest {
    private final HasNoVehicleRule hasNoVehicleRule = new HasNoVehicleRule();

    @Test
    void nullifiesAutoScore() {
        HashMap<String, Integer> scores = new HashMap<>();

        scores.put(AUTO, 1);
        scores.put(DISABILITY, 2);
        scores.put(HOME, 3);
        scores.put(LIFE, 0);

        hasNoVehicleRule.apply(scores);

        assertNull(scores.get(AUTO));
        assertEquals(2, scores.get(DISABILITY));
        assertEquals(3, scores.get(HOME));
        assertEquals(0, scores.get(LIFE));
    }
}