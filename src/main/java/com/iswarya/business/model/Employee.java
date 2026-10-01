package com.iswarya.business.model;

import com.iswarya.business.exception.InvalidSalaryException;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class Employee {

    private final int id;
    private final String name;
    private double salary;
    private final Set<String> skills;

    protected Employee(
            int id,
            String name,
            double salary,
            Set<String> skills) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Employee ID must be positive"
            );
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Employee name cannot be empty"
            );
        }

        if (salary < 0) {
            throw new InvalidSalaryException(salary);
        }

        this.id = id;
        this.name = name;
        this.salary = salary;

        this.skills = skills == null
                ? new HashSet<>()
                : new HashSet<>(skills);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public Set<String> getSkills() {
        return Set.copyOf(skills);
    }

    public void updateSalary(double salary) {

        if (salary < 0) {
            throw new InvalidSalaryException(salary);
        }

        this.salary = salary;
    }

    public void addSkill(String skill) {

        if (skill == null || skill.isBlank()) {
            throw new IllegalArgumentException(
                    "Skill cannot be empty"
            );
        }

        skills.add(skill);
    }

    public abstract String getRole();

    public abstract double calculateBonus();

    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }

        if (!(object instanceof Employee employee)) {
            return false;
        }

        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {

        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", role='" + getRole() + '\'' +
                ", skills=" + skills +
                '}';
    }
}