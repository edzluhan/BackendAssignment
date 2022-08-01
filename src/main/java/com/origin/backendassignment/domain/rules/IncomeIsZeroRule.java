package com.origin.backendassignment.domain.rules;

import com.origin.backendassignment.domain.UserProfile;

import java.util.HashMap;

import static com.origin.backendassignment.domain.InsuranceType.DISABILITY;

public class IncomeIsZeroRule implements Rule {

    @Override
    public void apply(HashMap<String, Integer> partialScore) {
        partialScore.put(DISABILITY, null);
    }

    @Override
    public boolean matches(UserProfile profile) {
        return profile.getIncome() == 0;
    }
}
