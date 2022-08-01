package com.origin.backendassignment.response;

import lombok.Value;

import java.util.List;

@Value
public class ErrorResponse {
    List<String> messages;
}
