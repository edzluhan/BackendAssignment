package com.origin.backendassignment.domain.rules;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static com.origin.backendassignment.domain.InsuranceType.DISABILITY;
import static com.origin.backendassignment.domain.InsuranceType.LIFE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class IsMarriedRuleTest {
    private final IsMarriedRule isMarriedRule = new IsMarriedRule();

    @Test
    void reducesDisabilityScoreByOneAndIncreasesLifeScoreByOne() {
        HashMap<String, Integer> scores = new HashMap<>();

        scores.put(DISABILITY, 1);
        scores.put(LIFE, 1);

        isMarriedRule.apply(scores);

        assertEquals(0, scores.get(DISABILITY));
        assertEquals(2, scores.get(LIFE));
    }

    @Test
    void doesNothingWhenScoreIsNull() {
        HashMap<String, Integer> scores = new HashMap<>();

        scores.put(DISABILITY, null);
        scores.put(LIFE, null);

        isMarriedRule.apply(scores);

        assertNull(scores.get(DISABILITY));
        assertNull(scores.get(LIFE));
    }
}