package com.iswarya.business;

import com.iswarya.business.exception.DuplicateEmployeeException;
import com.iswarya.business.exception.EmployeeNotFoundException;
import com.iswarya.business.exception.InvalidSalaryException;
import com.iswarya.business.model.Developer;
import com.iswarya.business.model.Employee;
import com.iswarya.business.model.Manager;
import com.iswarya.business.service.BusinessManagementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BusinessManagementServiceTest {

    private BusinessManagementService service;
    private Developer developer;
    private Manager manager;

    @BeforeEach
    void setUp() throws DuplicateEmployeeException {

        service = new BusinessManagementService();

        developer = new Developer(
                101,
                "Arun",
                60000,
                Set.of("Java", "SQL"),
                "Java"
        );

        manager = new Manager(
                102,
                "Priya",
                80000,
                Set.of("Leadership", "Planning"),
                5
        );

        service.addEmployee(developer);
        service.addEmployee(manager);
    }

    @Test
    void shouldAddEmployeeSuccessfully() {

        assertEquals(
                2,
                service.getEmployeeCount()
        );
    }

    @Test
    void shouldFindEmployeeById()
            throws EmployeeNotFoundException {

        Employee employee =
                service.findEmployeeById(101);

        assertEquals(
                "Arun",
                employee.getName()
        );

        assertEquals(
                "Developer",
                employee.getRole()
        );
    }

    @Test
    void shouldRejectDuplicateEmployeeId() {

        Developer duplicate = new Developer(
                101,
                "Another Developer",
                50000,
                Set.of("Python"),
                "Python"
        );

        assertThrows(
                DuplicateEmployeeException.class,
                () -> service.addEmployee(duplicate)
        );
    }

    @Test
    void shouldThrowExceptionWhenEmployeeDoesNotExist() {

        assertThrows(
                EmployeeNotFoundException.class,
                () -> service.findEmployeeById(999)
        );
    }

    @Test
    void shouldFilterEmployeesByMinimumSalary() {

        List<Employee> employees =
                service.findEmployeesByMinimumSalary(70000);

        assertEquals(
                1,
                employees.size()
        );

        assertEquals(
                "Priya",
                employees.get(0).getName()
        );
    }

    @Test
    void shouldFilterDevelopersUsingGenericMethod() {

        List<Developer> developers =
                service.filterEmployees(
                        Developer.class,
                        developer -> developer.getSalary() >= 50000
                );

        assertEquals(
                1,
                developers.size()
        );

        assertEquals(
                "Arun",
                developers.get(0).getName()
        );
    }

    @Test
    void shouldSortEmployeesBySalary() {

        List<Employee> employees =
                service.sortEmployees(
                        (first, second) ->
                                Double.compare(
                                        first.getSalary(),
                                        second.getSalary()
                                )
                );

        assertEquals(
                "Arun",
                employees.get(0).getName()
        );

        assertEquals(
                "Priya",
                employees.get(1).getName()
        );
    }

    @Test
    void shouldGroupEmployeesByRole() {

        Map<String, List<Employee>> grouped =
                service.groupEmployeesByRole();

        assertEquals(
                1,
                grouped.get("Developer").size()
        );

        assertEquals(
                1,
                grouped.get("Manager").size()
        );
    }

    @Test
    void shouldCalculateTotalSalary() {

        assertEquals(
                140000,
                service.calculateTotalSalary()
        );
    }

    @Test
    void shouldCalculateTotalBonus() {

        assertEquals(
                25000,
                service.calculateTotalBonus()
        );
    }

    @Test
    void shouldRemoveEmployee()
            throws EmployeeNotFoundException {

        service.removeEmployee(101);

        assertEquals(
                1,
                service.getEmployeeCount()
        );

        assertThrows(
                EmployeeNotFoundException.class,
                () -> service.findEmployeeById(101)
        );
    }

    @Test
    void shouldDemonstratePolymorphism() {

        Employee developerEmployee = developer;
        Employee managerEmployee = manager;

        assertEquals(
                "Developer",
                developerEmployee.getRole()
        );

        assertEquals(
                "Manager",
                managerEmployee.getRole()
        );

        assertEquals(
                9000,
                developerEmployee.calculateBonus()
        );

        assertEquals(
                16000,
                managerEmployee.calculateBonus()
        );
    }

    @Test
    void shouldRejectNegativeSalary() {

        assertThrows(
                InvalidSalaryException.class,
                () -> new Developer(
                        103,
                        "Invalid Employee",
                        -5000,
                        Set.of("Java"),
                        "Java"
                )
        );
    }

    @Test
    void shouldRejectNegativeSalaryDuringUpdate() {

        assertThrows(
                InvalidSalaryException.class,
                () -> developer.updateSalary(-1000)
        );
    }
}