package com.origin.backendassignment.response;

import com.origin.backendassignment.domain.InsuranceRange;
import com.origin.backendassignment.domain.InsuranceType;
import lombok.Builder;
import lombok.Value;

import java.util.Map;

import static com.origin.backendassignment.domain.InsuranceRange.getFor;
import static com.origin.backendassignment.domain.InsuranceType.AUTO;
import static com.origin.backendassignment.domain.InsuranceType.DISABILITY;
import static com.origin.backendassignment.domain.InsuranceType.HOME;
import static com.origin.backendassignment.domain.InsuranceType.LIFE;

@Value
@Builder
public class RiskResponse {
    private InsuranceRange auto;
    private InsuranceRange disability;
    private InsuranceRange home;
    private InsuranceRange life;

    public static RiskResponse from(Map<String, Integer> risks) {
        return RiskResponse.builder()
                .auto(getFor(risks.get(AUTO)))
                .disability(getFor(risks.get(DISABILITY)))
                .home(getFor(risks.get(HOME)))
                .life(getFor(risks.get(LIFE)))
                .build();
    }
}
