package com.example.demo.validation.emailValidor;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.Set;

public class EmailValidation implements ConstraintValidator<ValidEmail, String> {
    private static final Set<String> VALID_DOMAINS = new HashSet<>();

    static {
        VALID_DOMAINS.add("gmail.com");
        VALID_DOMAINS.add("yahoo.com");
        // Add more valid domains if needed
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true; // Use @NotEmpty or @NotNull to handle null or empty validation
        }
        String[] parts = value.split("@");
        if (parts.length != 2) {
            return false;
        }
        String domain = parts[1];
        return VALID_DOMAINS.contains(domain);
    }
}