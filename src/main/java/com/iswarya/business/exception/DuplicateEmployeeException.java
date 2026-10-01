package com.iswarya.business.exception;

public class DuplicateEmployeeException extends BusinessException {

    public DuplicateEmployeeException(int employeeId) {
        super("Employee with ID " + employeeId + " already exists");
    }
}