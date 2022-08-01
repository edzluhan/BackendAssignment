package com.origin.backendassignment.domain.rules;

import com.origin.backendassignment.domain.UserProfile;

import java.util.HashMap;

import static java.util.Objects.nonNull;

public class HighIncomeRule implements Rule {
    private static final Integer HIGH_INCOME = 200000;

    @Override
    public void apply(HashMap<String, Integer> partialScore) {
        partialScore.forEach((insurance, risk) -> partialScore.put(insurance, nonNull(risk) ? risk - 1 : null));
    }

    @Override
    public boolean matches(UserProfile profile) {
        return profile.getIncome() > HIGH_INCOME;
    }
}
