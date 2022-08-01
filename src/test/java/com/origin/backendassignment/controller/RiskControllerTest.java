package com.origin.backendassignment.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RiskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void returns200AndAllIneligible() throws Exception {
        mockMvc.perform(
                        post("/risk")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content("{\n" +
                                        "  \"age\": 60,\n" +
                                        "  \"dependents\": 2,\n" +
                                        "  \"house\": null,\n" +
                                        "  \"income\": 0,\n" +
                                        "  \"marital_status\": \"married\",\n" +
                                        "  \"risk_questions\": [1, 1, 1],\n" +
                                        "  \"vehicle\": null\n" +
                                        "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.auto").value("ineligible"))
                .andExpect(jsonPath("$.disability").value("ineligible"))
                .andExpect(jsonPath("$.home").value("ineligible"))
                .andExpect(jsonPath("$.life").value("ineligible"));
    }

    @Test
    void returns200AndIneligibleForDisability() throws Exception {
        mockMvc.perform(
                        post("/risk")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content("{\n" +
                                        "  \"age\": 35,\n" +
                                        "  \"dependents\": 2,\n" +
                                        "  \"house\": {\"ownership_status\": \"owned\"},\n" +
                                        "  \"income\": 0,\n" +
                                        "  \"marital_status\": \"married\",\n" +
                                        "  \"risk_questions\": [0, 1, 0],\n" +
                                        "  \"vehicle\": {\"year\": 2018}\n" +
                                        "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.auto").value("regular"))
                .andExpect(jsonPath("$.disability").value("ineligible"))
                .andExpect(jsonPath("$.home").value("economic"))
                .andExpect(jsonPath("$.life").value("regular"));
    }

    @Test
    void returns200AndIneligibleForAuto() throws Exception {
        mockMvc.perform(
                        post("/risk")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content("{\n" +
                                        "  \"age\": 35,\n" +
                                        "  \"dependents\": 2,\n" +
                                        "  \"house\": {\"ownership_status\": \"owned\"},\n" +
                                        "  \"income\": 30000,\n" +
                                        "  \"marital_status\": \"married\",\n" +
                                        "  \"risk_questions\": [0, 1, 0]\n" +
                                        "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.auto").value("ineligible"))
                .andExpect(jsonPath("$.disability").value("economic"))
                .andExpect(jsonPath("$.home").value("economic"))
                .andExpect(jsonPath("$.life").value("regular"));
    }

    @Test
    void returns200AndIneligibleForHome() throws Exception {
        mockMvc.perform(
                        post("/risk")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content("{\n" +
                                        "  \"age\": 35,\n" +
                                        "  \"dependents\": 2,\n" +
                                        "  \"income\": 30000,\n" +
                                        "  \"marital_status\": \"married\",\n" +
                                        "  \"risk_questions\": [0, 1, 0],\n" +
                                        "  \"vehicle\": {\"year\": 2018}\n" +
                                        "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.auto").value("regular"))
                .andExpect(jsonPath("$.disability").value("economic"))
                .andExpect(jsonPath("$.home").value("ineligible"))
                .andExpect(jsonPath("$.life").value("regular"));
    }

    @Test
    void returns200AndIneligibleForDisabilityAndLife() throws Exception {
        mockMvc.perform(
                        post("/risk")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content("{\n" +
                                        "  \"age\": 60,\n" +
                                        "  \"dependents\": 2,\n" +
                                        "  \"house\": {\"ownership_status\": \"owned\"},\n" +
                                        "  \"income\": 200000,\n" +
                                        "  \"marital_status\": \"married\",\n" +
                                        "  \"risk_questions\": [0, 1, 0],\n" +
                                        "  \"vehicle\": {\"year\": 2018}\n" +
                                        "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.auto").value("regular"))
                .andExpect(jsonPath("$.disability").value("ineligible"))
                .andExpect(jsonPath("$.home").value("regular"))
                .andExpect(jsonPath("$.life").value("ineligible"));
    }

    @Test
    void returns200AndAllEconomic() throws Exception {
        mockMvc.perform(
                        post("/risk")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content("{\n" +
                                        "  \"age\": 28,\n" +
                                        "  \"dependents\": 0,\n" +
                                        "  \"house\": {\"ownership_status\": \"owned\"},\n" +
                                        "  \"income\": 300000,\n" +
                                        "  \"marital_status\": \"single\",\n" +
                                        "  \"risk_questions\": [0, 0, 0],\n" +
                                        "  \"vehicle\": {\"year\": 2000}\n" +
                                        "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.auto").value("economic"))
                .andExpect(jsonPath("$.disability").value("economic"))
                .andExpect(jsonPath("$.home").value("economic"))
                .andExpect(jsonPath("$.life").value("economic"));
    }

    @Test
    void returns200AndAllRegular() throws Exception {
        mockMvc.perform(
                        post("/risk")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content("{\n" +
                                        "  \"age\": 35,\n" +
                                        "  \"dependents\": 1,\n" +
                                        "  \"house\": {\"ownership_status\": \"mortgaged\"},\n" +
                                        "  \"income\": 300000,\n" +
                                        "  \"marital_status\": \"single\",\n" +
                                        "  \"risk_questions\": [1, 1, 0],\n" +
                                        "  \"vehicle\": {\"year\": 2019}\n" +
                                        "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.auto").value("regular"))
                .andExpect(jsonPath("$.disability").value("regular"))
                .andExpect(jsonPath("$.home").value("regular"))
                .andExpect(jsonPath("$.life").value("regular"));
    }

    @Test
    void returns200AndAllResponsible() throws Exception {
        mockMvc.perform(
                        post("/risk")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content("{\n" +
                                        "  \"age\": 41,\n" +
                                        "  \"dependents\": 2,\n" +
                                        "  \"house\": {\"ownership_status\": \"mortgaged\"},\n" +
                                        "  \"income\": 30000,\n" +
                                        "  \"marital_status\": \"married\",\n" +
                                        "  \"risk_questions\": [1, 1, 0],\n" +
                                        "  \"vehicle\": {\"year\": 2018}\n" +
                                        "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.auto").value("responsible"))
                .andExpect(jsonPath("$.disability").value("responsible"))
                .andExpect(jsonPath("$.home").value("responsible"))
                .andExpect(jsonPath("$.life").value("responsible"));
    }

    @Test
    void returns400forInvalidFields() throws Exception {
        mockMvc.perform(
                        post("/risk")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content("{\n" +
                                        "  \"age\": -35,\n" +
                                        "  \"dependents\": -2,\n" +
                                        "  \"house\": {\"ownership_status\": \"owned\"},\n" +
                                        "  \"income\": -1,\n" +
                                        "  \"marital_status\": \"married\",\n" +
                                        "  \"risk_questions\": [0, 1, 0, 1],\n" +
                                        "  \"vehicle\": {\"year\": null}\n" +
                                        "}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.messages", hasSize(5)));
    }
}