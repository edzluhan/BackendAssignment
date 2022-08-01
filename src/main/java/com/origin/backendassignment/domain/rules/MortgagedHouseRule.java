package com.origin.backendassignment.domain.rules;

import com.origin.backendassignment.domain.House;
import com.origin.backendassignment.domain.UserProfile;

import java.util.HashMap;

import static com.origin.backendassignment.domain.InsuranceType.DISABILITY;
import static com.origin.backendassignment.domain.InsuranceType.HOME;
import static java.util.Objects.nonNull;

public class MortgagedHouseRule implements Rule {
    @Override
    public void apply(HashMap<String, Integer> partialScore) {
        partialScore.put(HOME, nonNull(partialScore.get(HOME)) ? partialScore.get(HOME) + 1 : null);
        partialScore.put(DISABILITY, nonNull(partialScore.get(DISABILITY)) ? partialScore.get(DISABILITY) + 1 : null);
    }

    @Override
    public boolean matches(UserProfile profile) {
        return nonNull(profile.getHouse()) &&
                House.OwnershipStatus.MORTGAGED.equals(profile.getHouse().getOwnershipStatus());
    }

}
