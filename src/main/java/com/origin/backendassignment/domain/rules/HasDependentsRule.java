package com.origin.backendassignment.domain.rules;

import com.origin.backendassignment.domain.UserProfile;

import java.util.HashMap;

import static com.origin.backendassignment.domain.InsuranceType.DISABILITY;
import static com.origin.backendassignment.domain.InsuranceType.LIFE;
import static java.util.Objects.nonNull;

public class HasDependentsRule implements Rule {
    @Override
    public void apply(HashMap<String, Integer> partialScore) {
        partialScore.put(DISABILITY, nonNull(partialScore.get(DISABILITY)) ? partialScore.get(DISABILITY) + 1 : null);
        partialScore.put(LIFE, nonNull(partialScore.get(LIFE)) ? partialScore.get(LIFE) + 1 : null);
    }

    public boolean matches(UserProfile profile) {
        return profile.getDependents() > 0;
    }
}
