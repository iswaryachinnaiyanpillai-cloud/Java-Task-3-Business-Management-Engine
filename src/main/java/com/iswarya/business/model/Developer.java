package com.iswarya.business.model;

import java.util.Set;

public class Developer extends Employee {

    private final String programmingLanguage;

    public Developer(
            int id,
            String name,
            double salary,
            Set<String> skills,
            String programmingLanguage) {

        super(id, name, salary, skills);

        if (programmingLanguage == null || programmingLanguage.isBlank()) {
            throw new IllegalArgumentException(
                    "Programming language cannot be empty");
        }

        this.programmingLanguage = programmingLanguage;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    @Override
    public String getRole() {
        return "Developer";
    }

    @Override
    public double calculateBonus() {
        return getSalary() * 0.15;
    }
}