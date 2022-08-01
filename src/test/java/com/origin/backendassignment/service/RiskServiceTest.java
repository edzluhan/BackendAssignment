package com.origin.backendassignment.service;

import com.origin.backendassignment.domain.House;
import com.origin.backendassignment.domain.InsuranceRange;
import com.origin.backendassignment.domain.UserProfile;
import com.origin.backendassignment.domain.Vehicle;
import com.origin.backendassignment.response.RiskResponse;
import org.junit.jupiter.api.Test;

import static com.origin.backendassignment.domain.House.OwnershipStatus.MORTGAGED;
import static com.origin.backendassignment.domain.House.OwnershipStatus.OWNED;
import static com.origin.backendassignment.domain.InsuranceRange.ECONOMIC;
import static com.origin.backendassignment.domain.InsuranceRange.INELIGIBLE;
import static com.origin.backendassignment.domain.InsuranceRange.REGULAR;
import static com.origin.backendassignment.domain.InsuranceRange.RESPONSIBLE;
import static com.origin.backendassignment.domain.MaritalStatus.MARRIED;
import static com.origin.backendassignment.domain.MaritalStatus.SINGLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RiskServiceTest {

    private final RiskService service = new RiskService();

    @Test
    void ineligibleForDisabilityWhenNoIncome() {
        UserProfile userProfile = UserProfile.builder()
                .riskQuestions(new Integer[]{0, 1, 0})
                .dependents(0)
                .age(30)
                .income(0)
                .build();

        InsuranceRange disability = service.calculateRisk(userProfile).getDisability();
        assertEquals(INELIGIBLE, disability);
    }

    @Test
    void ineligibleForAutoWhenHasNoVehicle() {
        UserProfile userProfile = UserProfile.builder()
                .riskQuestions(new Integer[]{0, 1, 0})
                .income(10)
                .age(30)
                .dependents(0)
                .vehicle(null)
                .build();

        InsuranceRange auto = service.calculateRisk(userProfile).getAuto();
        assertEquals(INELIGIBLE, auto);
    }

    @Test
    void ineligibleForHomeWhenHasNoHouse() {
        UserProfile userProfile = UserProfile.builder()
                .riskQuestions(new Integer[]{0, 1, 0})
                .income(10)
                .dependents(0)
                .age(30)
                .house(null)
                .build();

        InsuranceRange home = service.calculateRisk(userProfile).getHome();
        assertEquals(INELIGIBLE, home);
    }

    @Test
    void ineligibleForDisabilityAndLifeWhenHas60OrMore() {
        UserProfile userProfile = UserProfile.builder()
                .riskQuestions(new Integer[]{0, 1, 0})
                .income(10)
                .dependents(0)
                .age(60)
                .maritalStatus(SINGLE)
                .build();

        RiskResponse response = service.calculateRisk(userProfile);
        InsuranceRange disability = response.getDisability();
        InsuranceRange life = response.getLife();
        assertEquals(INELIGIBLE, disability);
        assertEquals(INELIGIBLE, life);
    }

    @Test
    void ineligibleForAll() {
        UserProfile userProfile = UserProfile.builder()
                .age(60)
                .dependents(2)
                .house(null)
                .income(0)
                .maritalStatus(MARRIED)
                .riskQuestions(new Integer[]{0, 1, 0})
                .vehicle(null)
                .build();

        RiskResponse expectedResponse = RiskResponse.builder()
                .auto(INELIGIBLE)
                .disability(INELIGIBLE)
                .home(INELIGIBLE)
                .life(INELIGIBLE)
                .build();

        assertEquals(expectedResponse, service.calculateRisk(userProfile));
    }

    @Test
    void allEconomic() {
        UserProfile userProfile = UserProfile.builder()
                .age(28)
                .dependents(0)
                .house(new House(OWNED))
                .income(300000)
                .maritalStatus(SINGLE)
                .riskQuestions(new Integer[]{0, 0, 0})
                .vehicle(new Vehicle(2000))
                .build();

        RiskResponse expectedResponse = RiskResponse.builder()
                .auto(ECONOMIC)
                .disability(ECONOMIC)
                .home(ECONOMIC)
                .life(ECONOMIC)
                .build();

        assertEquals(expectedResponse, service.calculateRisk(userProfile));
    }

    @Test
    void allRegular() {
        UserProfile userProfile = UserProfile.builder()
                .age(35)
                .dependents(5)
                .house(new House(MORTGAGED))
                .income(300000)
                .maritalStatus(SINGLE)
                .riskQuestions(new Integer[]{1, 1, 0})
                .vehicle(new Vehicle(2019))
                .build();

        RiskResponse expectedResponse = RiskResponse.builder()
                .auto(REGULAR)
                .disability(REGULAR)
                .home(REGULAR)
                .life(REGULAR)
                .build();

        assertEquals(expectedResponse, service.calculateRisk(userProfile));
    }

    @Test
    void allResponsible() {
        UserProfile userProfile = UserProfile.builder()
                .age(41)
                .dependents(3)
                .house(new House(MORTGAGED))
                .income(50000)
                .maritalStatus(MARRIED)
                .riskQuestions(new Integer[]{0, 1, 1})
                .vehicle(new Vehicle(2018))
                .build();

        RiskResponse expectedResponse = RiskResponse.builder()
                .auto(RESPONSIBLE)
                .disability(RESPONSIBLE)
                .home(RESPONSIBLE)
                .life(RESPONSIBLE)
                .build();

        assertEquals(expectedResponse, service.calculateRisk(userProfile));
    }
}