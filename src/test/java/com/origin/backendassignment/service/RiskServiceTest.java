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
import static com.origin.backendassignment.domain.MaritalStatus.MARRIED;
import static com.origin.backendassignment.domain.MaritalStatus.SINGLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RiskServiceTest {

    private final RiskService service = new RiskService();

    @Test
    void ineligibleForDisabilityWhenNoIncome() {
        UserProfile request = UserProfile.builder()
                .riskQuestions(new Integer[]{0, 1, 0})
                .dependents(0)
                .age(30)
                .income(0)
                .build();

        InsuranceRange disability = service.calculateRisk(request).getDisability();
        assertEquals(INELIGIBLE, disability);
    }

    @Test
    void ineligibleForAutoWhenHasNoVehicle() {
        UserProfile request = UserProfile.builder()
                .riskQuestions(new Integer[]{0, 1, 0})
                .income(10)
                .age(30)
                .dependents(0)
                .vehicle(null)
                .build();

        InsuranceRange auto = service.calculateRisk(request).getAuto();
        assertEquals(INELIGIBLE, auto);
    }

    @Test
    void ineligibleForHomeWhenHasNoHouse() {
        UserProfile request = UserProfile.builder()
                .riskQuestions(new Integer[]{0, 1, 0})
                .income(10)
                .dependents(0)
                .age(30)
                .house(null)
                .build();

        InsuranceRange home = service.calculateRisk(request).getHome();
        assertEquals(INELIGIBLE, home);
    }

    @Test
    void ineligibleForDisabilityAndLifeWhenHas60OrMore() {
        UserProfile request = UserProfile.builder()
                .riskQuestions(new Integer[]{0, 1, 0})
                .income(10)
                .dependents(0)
                .age(60)
                .maritalStatus(SINGLE)
                .build();

        RiskResponse response = service.calculateRisk(request);
        InsuranceRange disability = response.getDisability();
        InsuranceRange life = response.getLife();
        assertEquals(INELIGIBLE, disability);
        assertEquals(INELIGIBLE, life);
    }

    @Test
    void reducesAllRisksByTwoWhenYoungerThanThirty() {
        UserProfile request = UserProfile.builder()
                .riskQuestions(new Integer[]{0, 1, 0})
                .income(10)
                .dependents(0)
                .age(29)
                .house(new House(OWNED))
                .build();

        RiskResponse response = service.calculateRisk(request);

        RiskResponse expectedResponse = RiskResponse.builder()
                .auto(INELIGIBLE)
                .disability(ECONOMIC)
                .home(ECONOMIC)
                .life(ECONOMIC)
                .build();

        assertEquals(expectedResponse, response);
    }

    @Test
    void reducesAllRisksByOneWhenAgeEqualsOrHigherThanThirty() {
        UserProfile request = UserProfile.builder()
                .riskQuestions(new Integer[]{0, 1, 0})
                .income(10)
                .dependents(0)
                .age(30)
                .house(new House(OWNED))
                .maritalStatus(SINGLE)
                .build();

        RiskResponse response = service.calculateRisk(request);

        RiskResponse expectedResponse = RiskResponse.builder()
                .auto(INELIGIBLE)
                .disability(ECONOMIC)
                .home(ECONOMIC)
                .life(ECONOMIC)
                .build();

        assertEquals(expectedResponse, response);
    }

    @Test
    void reducesAllRisksByOneWhenAgeLowerOrEqualsThanForty() {
        UserProfile request = UserProfile.builder()
                .riskQuestions(new Integer[]{0, 1, 0})
                .income(10)
                .dependents(0)
                .age(40)
                .house(new House(OWNED))
                .build();

        RiskResponse response = service.calculateRisk(request);

        RiskResponse expectedResponse = RiskResponse.builder()
                .auto(INELIGIBLE)
                .disability(ECONOMIC)
                .home(ECONOMIC)
                .life(ECONOMIC)
                .build();

        assertEquals(expectedResponse, response);
    }

    @Test
    void reducesAllRisksByOneWhenIncomeIsHigherThan200k() {
        UserProfile request = UserProfile.builder()
                .riskQuestions(new Integer[]{0, 1, 0})
                .income(200001)
                .dependents(0)
                .age(20)
                .house(new House(OWNED))
                .maritalStatus(SINGLE)
                .build();

        RiskResponse response = service.calculateRisk(request);

        RiskResponse expectedResponse = RiskResponse.builder()
                .auto(INELIGIBLE)
                .disability(ECONOMIC)
                .home(ECONOMIC)
                .life(ECONOMIC)
                .build();

        assertEquals(expectedResponse, response);
    }

    @Test
    void increaseHomeAndDisabilityRisksWhenHouseIsMortgaged() {
        UserProfile request = UserProfile.builder()
                .riskQuestions(new Integer[]{0, 1, 0})
                .income(10)
                .dependents(0)
                .age(20)
                .house(new House(MORTGAGED))
                .maritalStatus(SINGLE)
                .build();

        RiskResponse response = service.calculateRisk(request);

        RiskResponse expectedResponse = RiskResponse.builder()
                .auto(INELIGIBLE)
                .disability(ECONOMIC)
                .home(ECONOMIC)
                .life(ECONOMIC)
                .build();

        assertEquals(expectedResponse, response);
    }

    @Test
    void increaseDisabilityAndLifeWhenHasDependents() {
        UserProfile request = UserProfile.builder()
                .riskQuestions(new Integer[]{0, 1, 0})
                .income(10)
                .dependents(1)
                .age(20)
                .house(new House(MORTGAGED))
                .maritalStatus(SINGLE)
                .build();

        RiskResponse response = service.calculateRisk(request);

        RiskResponse expectedResponse = RiskResponse.builder()
                .auto(INELIGIBLE)
                .disability(REGULAR)
                .home(ECONOMIC)
                .life(ECONOMIC)
                .build();

        assertEquals(expectedResponse, response);
    }

    @Test
    void increaseLifeAndReduceDisabilityWhenMarried() {
        UserProfile request = UserProfile.builder()
                .riskQuestions(new Integer[]{0, 1, 0})
                .income(10)
                .dependents(0)
                .age(20)
                .house(new House(OWNED))
                .maritalStatus(MARRIED)
                .build();

        RiskResponse response = service.calculateRisk(request);

        RiskResponse expectedResponse = RiskResponse.builder()
                .auto(INELIGIBLE)
                .disability(ECONOMIC)
                .home(ECONOMIC)
                .life(ECONOMIC)
                .build();

        assertEquals(expectedResponse, response);
    }

    @Test
    void increaseAutoWhenVehicleIsNew() {
        UserProfile request = UserProfile.builder()
                .riskQuestions(new Integer[]{0, 1, 0})
                .income(10)
                .dependents(0)
                .age(20)
                .house(new House(OWNED))
                .maritalStatus(SINGLE)
                .vehicle(new Vehicle(2020))
                .build();

        RiskResponse response = service.calculateRisk(request);

        RiskResponse expectedResponse = RiskResponse.builder()
                .auto(ECONOMIC)
                .disability(ECONOMIC)
                .home(ECONOMIC)
                .life(ECONOMIC)
                .build();

        assertEquals(expectedResponse, response);
    }

    @Test
    void givenScenario() {
        UserProfile request = UserProfile.builder()
                .age(35)
                .dependents(2)
                .house(new House(OWNED))
                .income(0)
                .maritalStatus(MARRIED)
                .riskQuestions(new Integer[]{0, 1, 0})
                .vehicle(new Vehicle(2018))
                .build();

        RiskResponse expectedResponse = RiskResponse.builder()
                .auto(REGULAR)
                .disability(INELIGIBLE)
                .home(ECONOMIC)
                .life(REGULAR)
                .build();

        assertEquals(expectedResponse, service.calculateRisk(request));
    }
}