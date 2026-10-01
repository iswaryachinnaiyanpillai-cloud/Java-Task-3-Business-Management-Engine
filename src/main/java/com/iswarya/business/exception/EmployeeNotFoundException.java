package com.iswarya.business.exception;

public class EmployeeNotFoundException extends BusinessException {

    public EmployeeNotFoundException(int employeeId) {
        super("Employee with ID " + employeeId + " was not found");
    }
}