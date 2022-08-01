package com.origin.backendassignment.domain.rules;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static com.origin.backendassignment.domain.InsuranceType.DISABILITY;
import static com.origin.backendassignment.domain.InsuranceType.HOME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MortgagedHouseRuleTest {
    private final MortgagedHouseRule mortgagedHouseRule = new MortgagedHouseRule();

    @Test
    void increasesHomeAndDisabilityScores() {
        HashMap<String, Integer> scores = new HashMap<>();

        scores.put(DISABILITY, 1);
        scores.put(HOME, 1);

        mortgagedHouseRule.apply(scores);

        assertEquals(2, scores.get(DISABILITY));
        assertEquals(2, scores.get(HOME));
    }

    @Test
    void doesNothingWhenScoreIsNull() {
        HashMap<String, Integer> scores = new HashMap<>();

        scores.put(DISABILITY, null);
        scores.put(HOME, null);

        mortgagedHouseRule.apply(scores);

        assertNull(scores.get(DISABILITY));
        assertNull(scores.get(HOME));
    }
}