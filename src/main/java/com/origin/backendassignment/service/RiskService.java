package com.origin.backendassignment.service;

import com.origin.backendassignment.domain.UserProfile;
import com.origin.backendassignment.domain.rules.AgeBetweenThirtyAndFortyRule;
import com.origin.backendassignment.domain.rules.HasDependentsRule;
import com.origin.backendassignment.domain.rules.HasNewVehicleRule;
import com.origin.backendassignment.domain.rules.HasNoHouseRule;
import com.origin.backendassignment.domain.rules.HasNoVehicleRule;
import com.origin.backendassignment.domain.rules.HighIncomeRule;
import com.origin.backendassignment.domain.rules.IncomeIsZeroRule;
import com.origin.backendassignment.domain.rules.IsMarriedRule;
import com.origin.backendassignment.domain.rules.MortgagedHouseRule;
import com.origin.backendassignment.domain.rules.OlderThanSixtyRule;
import com.origin.backendassignment.domain.rules.Rule;
import com.origin.backendassignment.domain.rules.YoungerThanThirtyRule;
import com.origin.backendassignment.response.RiskResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.origin.backendassignment.domain.InsuranceType.AUTO;
import static com.origin.backendassignment.domain.InsuranceType.DISABILITY;
import static com.origin.backendassignment.domain.InsuranceType.HOME;
import static com.origin.backendassignment.domain.InsuranceType.LIFE;

@Service
public class RiskService {

    public RiskResponse calculateRisk(UserProfile profile) {

        Integer baseScore = profile.calculateBaseRisk();

        HashMap<String, Integer> partialScore = new HashMap<>();
        partialScore.put(AUTO, baseScore);
        partialScore.put(DISABILITY, baseScore);
        partialScore.put(HOME, baseScore);
        partialScore.put(LIFE, baseScore);

        List<Rule> rules = new ArrayList<>();
        rules.add(new IncomeIsZeroRule());
        rules.add(new HasNoVehicleRule());
        rules.add(new HasNoHouseRule());
        rules.add(new OlderThanSixtyRule());
        rules.add(new YoungerThanThirtyRule());
        rules.add(new AgeBetweenThirtyAndFortyRule());
        rules.add(new HighIncomeRule());
        rules.add(new MortgagedHouseRule());
        rules.add(new HasDependentsRule());
        rules.add(new IsMarriedRule());
        rules.add(new HasNewVehicleRule());

        rules.stream()
                .filter(rule -> rule.matches(profile))
                .forEach(rule -> rule.apply(partialScore));

        return RiskResponse.from(partialScore);
    }


}
