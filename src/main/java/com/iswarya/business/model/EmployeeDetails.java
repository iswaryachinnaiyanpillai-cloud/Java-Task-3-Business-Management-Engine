package com.iswarya.business.model;

public record EmployeeDetails(
        int employeeId,
        String email,
        String phoneNumber
) {

    public EmployeeDetails {

        if (employeeId <= 0) {
            throw new IllegalArgumentException(
                    "Employee ID must be positive"
            );
        }

        if (email == null || email.isBlank() || !email.contains("@")) {
            throw new IllegalArgumentException(
                    "A valid email address is required"
            );
        }

        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new IllegalArgumentException(
                    "Phone number cannot be empty"
            );
        }
    }
}