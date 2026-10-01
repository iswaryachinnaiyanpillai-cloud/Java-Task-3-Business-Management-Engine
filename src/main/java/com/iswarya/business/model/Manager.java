package com.iswarya.business.model;

import java.util.Set;

public class Manager extends Employee {

    private final int teamSize;

    public Manager(
            int id,
            String name,
            double salary,
            Set<String> skills,
            int teamSize) {

        super(id, name, salary, skills);

        if (teamSize < 0) {
            throw new IllegalArgumentException("Team size cannot be negative");
        }

        this.teamSize = teamSize;
    }

    public int getTeamSize() {
        return teamSize;
    }

    @Override
    public String getRole() {
        return "Manager";
    }

    @Override
    public double calculateBonus() {
        return getSalary() * 0.20;
    }
}