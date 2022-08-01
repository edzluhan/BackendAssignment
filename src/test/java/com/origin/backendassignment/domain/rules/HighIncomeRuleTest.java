package com.origin.backendassignment.domain.rules;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static com.origin.backendassignment.domain.InsuranceType.AUTO;
import static com.origin.backendassignment.domain.InsuranceType.DISABILITY;
import static com.origin.backendassignment.domain.InsuranceType.HOME;
import static com.origin.backendassignment.domain.InsuranceType.LIFE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class HighIncomeRuleTest {
    private final HighIncomeRule highIncomeRule = new HighIncomeRule();

    @Test
    void reducesAllScoresByOne() {
        HashMap<String, Integer> scores = new HashMap<>();

        scores.put(AUTO, 1);
        scores.put(DISABILITY, 2);
        scores.put(HOME, 3);
        scores.put(LIFE, 0);

        highIncomeRule.apply(scores);

        assertEquals(0, scores.get(AUTO));
        assertEquals(1, scores.get(DISABILITY));
        assertEquals(2, scores.get(HOME));
        assertEquals(-1, scores.get(LIFE));
    }

    @Test
    void doesNothingWhenScoreIsNull() {
        HashMap<String, Integer> scores = new HashMap<>();

        scores.put(AUTO, null);
        scores.put(DISABILITY, null);
        scores.put(HOME, null);
        scores.put(LIFE, null);

        highIncomeRule.apply(scores);

        assertNull(scores.get(AUTO));
        assertNull(scores.get(DISABILITY));
        assertNull(scores.get(HOME));
        assertNull(scores.get(LIFE));
    }
}