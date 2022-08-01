package com.origin.backendassignment.domain.rules;

import com.origin.backendassignment.domain.UserProfile;

import java.util.HashMap;

import static java.util.Objects.nonNull;

public class AgeBetweenThirtyAndFortyRule implements Rule {
    @Override
    public void apply(HashMap<String, Integer> partialScore) {
        partialScore.forEach((insurance, risk) -> partialScore.put(insurance, nonNull(risk) ? risk - 1 : null));
    }

    @Override
    public boolean matches(UserProfile profile) {
        return 30 <= profile.getAge() && profile.getAge() <= 40;
    }
}
