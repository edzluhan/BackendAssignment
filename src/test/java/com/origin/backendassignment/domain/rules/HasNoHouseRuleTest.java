package com.origin.backendassignment.domain.rules;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static com.origin.backendassignment.domain.InsuranceType.AUTO;
import static com.origin.backendassignment.domain.InsuranceType.DISABILITY;
import static com.origin.backendassignment.domain.InsuranceType.HOME;
import static com.origin.backendassignment.domain.InsuranceType.LIFE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class HasNoHouseRuleTest {
    private final HasNoHouseRule hasNoHouseRule = new HasNoHouseRule();

    @Test
    void nullifiesHomeScore() {
        HashMap<String, Integer> scores = new HashMap<>();

        scores.put(AUTO, 1);
        scores.put(DISABILITY, 2);
        scores.put(HOME, 3);
        scores.put(LIFE, 0);

        hasNoHouseRule.apply(scores);

        assertNull(scores.get(HOME));
        assertEquals(1, scores.get(AUTO));
        assertEquals(2, scores.get(DISABILITY));
        assertEquals(0, scores.get(LIFE));
    }
}