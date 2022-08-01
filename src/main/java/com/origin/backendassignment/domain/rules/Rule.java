package com.origin.backendassignment.domain.rules;

import com.origin.backendassignment.domain.UserProfile;

import java.util.HashMap;

public interface Rule {
    void apply(HashMap<String, Integer> partialScore);
    boolean matches(UserProfile profile);
}
