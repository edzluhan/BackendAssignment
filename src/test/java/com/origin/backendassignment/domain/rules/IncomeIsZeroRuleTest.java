package com.origin.backendassignment.domain.rules;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static com.origin.backendassignment.domain.InsuranceType.AUTO;
import static com.origin.backendassignment.domain.InsuranceType.DISABILITY;
import static com.origin.backendassignment.domain.InsuranceType.HOME;
import static com.origin.backendassignment.domain.InsuranceType.LIFE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class IncomeIsZeroRuleTest {
    private final IncomeIsZeroRule incomeIsZeroRule = new IncomeIsZeroRule();

    @Test
    void nullifiesDisabilityScore() {
        HashMap<String, Integer> scores = new HashMap<>();

        scores.put(AUTO, 2);
        scores.put(DISABILITY, 2);
        scores.put(HOME, 2);
        scores.put(LIFE, 2);

        incomeIsZeroRule.apply(scores);

        assertNull(scores.get(DISABILITY));
        assertEquals(2, scores.get(LIFE));
        assertEquals(2, scores.get(AUTO));
        assertEquals(2, scores.get(HOME));
    }
}