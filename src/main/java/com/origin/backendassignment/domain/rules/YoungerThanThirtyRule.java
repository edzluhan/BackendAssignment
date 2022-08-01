package com.origin.backendassignment.domain.rules;

import com.origin.backendassignment.domain.UserProfile;

import java.util.HashMap;

import static java.util.Objects.nonNull;

public class YoungerThanThirtyRule implements Rule {
    @Override
    public void apply(HashMap<String, Integer> partialScore) {
        partialScore.forEach((insurance, risk) -> partialScore.put(insurance, nonNull(risk) ? risk - 2 : null));
    }

    @Override
    public boolean matches(UserProfile profile) {
        return profile.getAge() < 30;
    }
}
