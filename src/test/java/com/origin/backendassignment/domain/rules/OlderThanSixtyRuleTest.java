package com.origin.backendassignment.domain.rules;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static com.origin.backendassignment.domain.InsuranceType.AUTO;
import static com.origin.backendassignment.domain.InsuranceType.DISABILITY;
import static com.origin.backendassignment.domain.InsuranceType.HOME;
import static com.origin.backendassignment.domain.InsuranceType.LIFE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class OlderThanSixtyRuleTest {
    private final OlderThanSixtyRule olderThanSixtyRule = new OlderThanSixtyRule();

    @Test
    void nullifiesDisabilityAndLifeScores() {
        HashMap<String, Integer> scores = new HashMap<>();

        scores.put(AUTO, 1);
        scores.put(DISABILITY, 1);
        scores.put(HOME, 1);
        scores.put(LIFE, 1);

        olderThanSixtyRule.apply(scores);

        assertNull(scores.get(DISABILITY));
        assertNull(scores.get(LIFE));
        assertEquals(1, scores.get(AUTO));
        assertEquals(1, scores.get(HOME));
    }
}