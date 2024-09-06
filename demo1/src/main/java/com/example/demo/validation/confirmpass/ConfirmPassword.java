package com.example.demo.validation.confirmpass;
import com.example.demo.DTO.RegisterCustomerRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ConfirmPassword implements ConstraintValidator<PasswordConfirmation, RegisterCustomerRequest> {
    @Override
    public boolean isValid(RegisterCustomerRequest user, ConstraintValidatorContext context) {
        if (user.getPassword() == null || user.getConfirmPassword() == null) {
            return false; // Handle the null case, you might want to change this based on your needs
        }
        return user.getPassword().equals(user.getConfirmPassword());
    }
}