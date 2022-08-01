package com.origin.backendassignment.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Arrays;

@Value
@Builder
public class UserProfile {
    @NotNull(message = "age must not be null")
    @Min(value = 0, message = "Age must be an integer equal or greater than 0")
    private Integer age;
    @NotNull(message = "dependents must not be null")
    @PositiveOrZero(message = "dependents must be an integer equal or greater than 0")
    private Integer dependents;
    @Valid
    private House house;
    @NotNull(message = "income must not be null")
    @PositiveOrZero(message = "Income must be an integer equal or greater than 0")
    private Integer income;
    @NotNull(message = "marital_status must not be null")
    private MaritalStatus maritalStatus;
    @NotNull(message = "risk_questions must not be null")
    @Size(min = 3, max = 3, message = "risk_questions must have exactly 3 elements")
    private Integer[] riskQuestions;
    @Valid
    private Vehicle vehicle;

    @JsonCreator
    public UserProfile(
            @JsonProperty("age") Integer age,
            @JsonProperty("dependents") Integer dependents,
            @JsonProperty("house") House house,
            @JsonProperty("income") Integer income,
            @JsonProperty("marital_status") MaritalStatus maritalStatus,
            @JsonProperty("risk_questions") Integer[] riskQuestions,
            @JsonProperty("vehicle") Vehicle vehicle) {
        this.age = age;
        this.dependents = dependents;
        this.house = house;
        this.income = income;
        this.maritalStatus = maritalStatus;
        this.riskQuestions = riskQuestions;
        this.vehicle = vehicle;
    }

    public Integer calculateBaseRisk() {
        return Arrays.stream(riskQuestions).reduce(0, Integer::sum);
    }
}
