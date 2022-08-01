package com.origin.backendassignment.domain.rules;

import com.origin.backendassignment.domain.UserProfile;

import java.util.HashMap;

import static com.origin.backendassignment.domain.InsuranceType.HOME;
import static java.util.Objects.isNull;

public class HasNoHouseRule implements Rule {
    @Override
    public void apply(HashMap<String, Integer> partialScore) {
        partialScore.put(HOME, null);
    }

    @Override
    public boolean matches(UserProfile profile) {
        return isNull(profile.getHouse());
    }
}
