package com.origin.backendassignment.domain.rules;

import com.origin.backendassignment.domain.UserProfile;

import java.util.HashMap;

import static com.origin.backendassignment.domain.InsuranceType.DISABILITY;
import static com.origin.backendassignment.domain.InsuranceType.LIFE;

public class OlderThanSixtyRule implements Rule {
    @Override
    public void apply(HashMap<String, Integer> partialScore) {
        partialScore.put(DISABILITY, null);
        partialScore.put(LIFE, null);
    }

    @Override
    public boolean matches(UserProfile profile) {
        return profile.getAge() >= 60;
    }
}
