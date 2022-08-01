package com.origin.backendassignment.domain.rules;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static com.origin.backendassignment.domain.InsuranceType.AUTO;
import static com.origin.backendassignment.domain.InsuranceType.DISABILITY;
import static com.origin.backendassignment.domain.InsuranceType.HOME;
import static com.origin.backendassignment.domain.InsuranceType.LIFE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AgeBetweenThirtyAndFortyRuleTest {
    private final AgeBetweenThirtyAndFortyRule ageBetweenThirtyAndFortyRule = new AgeBetweenThirtyAndFortyRule();

    @Test
    void reducesAllScoresByOne() {
        HashMap<String, Integer> scores = new HashMap<>();

        scores.put(AUTO, 1);
        scores.put(DISABILITY, 2);
        scores.put(HOME, 3);
        scores.put(LIFE, 0);

        ageBetweenThirtyAndFortyRule.apply(scores);

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

        ageBetweenThirtyAndFortyRule.apply(scores);

        assertNull(scores.get(AUTO));
        assertNull(scores.get(DISABILITY));
        assertNull(scores.get(HOME));
        assertNull(scores.get(LIFE));
    }
}