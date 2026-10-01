package com.iswarya.business.exception;

public class InvalidSalaryException extends IllegalArgumentException {

    public InvalidSalaryException(double salary) {
        super("Salary cannot be negative: " + salary);
    }
}