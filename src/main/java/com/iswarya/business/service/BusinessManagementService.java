package com.iswarya.business.service;

import com.iswarya.business.exception.DuplicateEmployeeException;
import com.iswarya.business.exception.EmployeeNotFoundException;
import com.iswarya.business.model.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BusinessManagementService {

    private final List<Employee> employees;
    private final Map<Integer, Employee> employeeById;

    public BusinessManagementService() {
        this.employees = new ArrayList<>();
        this.employeeById = new java.util.HashMap<>();
    }

    /**
     * Adds an employee to the business.
     *
     * @throws DuplicateEmployeeException when the ID already exists
     */
    public void addEmployee(Employee employee)
            throws DuplicateEmployeeException {

        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }

        if (employeeById.containsKey(employee.getId())) {
            throw new DuplicateEmployeeException(employee.getId());
        }

        employees.add(employee);
        employeeById.put(employee.getId(), employee);
    }

    /**
     * Finds an employee by ID.
     *
     * @throws EmployeeNotFoundException when the employee does not exist
     */
    public Employee findEmployeeById(int employeeId)
            throws EmployeeNotFoundException {

        Employee employee = employeeById.get(employeeId);

        if (employee == null) {
            throw new EmployeeNotFoundException(employeeId);
        }

        return employee;
    }

    /**
     * Returns all employees.
     */
    public List<Employee> getAllEmployees() {
        return List.copyOf(employees);
    }

    /**
     * Generic filtering method using Predicate and Streams.
     */
    public <T extends Employee> List<T> filterEmployees(
            Class<T> employeeType,
            Predicate<T> condition) {

        return employees.stream()
                .filter(employee -> employeeType.isInstance(employee))
                .map(employee -> employeeType.cast(employee))
                .filter(condition)
                .toList();
    }

    /**
     * Sorts employees using a supplied Comparator.
     */
    public List<Employee> sortEmployees(
            Comparator<Employee> comparator) {

        return employees.stream()
                .sorted(comparator)
                .toList();
    }

    /**
     * Returns employees whose salary is at least the supplied amount.
     */
    public List<Employee> findEmployeesByMinimumSalary(
            double minimumSalary) {

        return employees.stream()
                .filter(employee -> employee.getSalary() >= minimumSalary)
                .toList();
    }

    /**
     * Groups employees by their role.
     */
    public Map<String, List<Employee>> groupEmployeesByRole() {

        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getRole));
    }

    /**
     * Calculates the total salary expense.
     */
    public double calculateTotalSalary() {

        return employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    /**
     * Calculates the total bonus expense.
     */
    public double calculateTotalBonus() {

        return employees.stream()
                .mapToDouble(Employee::calculateBonus)
                .sum();
    }

    /**
     * Removes an employee from the system.
     */
    public void removeEmployee(int employeeId)
            throws EmployeeNotFoundException {

        Employee employee = findEmployeeById(employeeId);

        employees.remove(employee);
        employeeById.remove(employeeId);
    }

    /**
     * Returns the number of registered employees.
     */
    public int getEmployeeCount() {
        return employees.size();
    }
}