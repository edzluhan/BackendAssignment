package com.origin.backendassignment.domain.rules;

import com.origin.backendassignment.domain.UserProfile;

import java.util.HashMap;

import static com.origin.backendassignment.domain.InsuranceType.AUTO;
import static java.util.Objects.nonNull;

public class HasNewVehicleRule implements Rule {
    @Override
    public void apply(HashMap<String, Integer> partialScore) {
        partialScore.put(AUTO, nonNull(partialScore.get(AUTO)) ? partialScore.get(AUTO) + 1 : null);
    }

    @Override
    public boolean matches(UserProfile profile) {
        return nonNull(profile.getVehicle()) && profile.getVehicle().isNew();
    }
}
