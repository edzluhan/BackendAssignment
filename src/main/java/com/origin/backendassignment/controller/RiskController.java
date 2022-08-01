package com.origin.backendassignment.controller;

import com.origin.backendassignment.domain.UserProfile;
import com.origin.backendassignment.response.RiskResponse;
import com.origin.backendassignment.service.RiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
public class RiskController {

    @Autowired
    private RiskService riskService;

    @PostMapping("/risk")
    public ResponseEntity<RiskResponse> calculateRisk(@RequestBody @Valid UserProfile request) {
        return ResponseEntity.ok().body(riskService.calculateRisk(request));
    }
}
