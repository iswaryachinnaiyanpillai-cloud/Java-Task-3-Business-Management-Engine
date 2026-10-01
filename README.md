# Business Management Engine

A Core Java business management engine demonstrating object-oriented programming, Java Collections Framework, Generics, Streams, Lambdas, custom exception handling, Java records, and JUnit 5 testing.

## Overview

This project implements a modular business management system for managing employees and applying common business operations such as:

- Adding employees
- Finding employees by ID
- Removing employees
- Filtering employees
- Sorting employees
- Grouping employees by role
- Calculating total salaries
- Calculating employee bonuses
- Validating business rules through custom exceptions

The project is implemented using **Java 17** and follows a clean package-based structure.

## Features

### 1. Object-Oriented Programming

The project demonstrates:

- Encapsulation
- Inheritance
- Abstraction
- Polymorphism

The abstract `Employee` class provides common employee behavior, while specialized classes such as `Developer` and `Manager` implement role-specific behavior.

### 2. Java Records

The `EmployeeDetails` record demonstrates Java 17 record-based immutable data modeling.

It stores:

- Employee ID
- Email
- Phone number

Validation is performed through the record's compact constructor.

### 3. Collections Framework

The project uses multiple Java Collections:

- `List` for maintaining employees
- `Set` for employee skills
- `Map` for employee lookup and role grouping

### 4. Generics

A generic filtering method is implemented to safely filter employees by their specific subclass type.

Example:

```java
List<Developer> developers =
        service.filterEmployees(
                Developer.class,
                developer -> developer.getSalary() >= 50000
        );