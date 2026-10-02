# Employee Transport Management System

A backend REST API built with Java and Spring Boot for managing employees, drivers, vehicles, and employee ride bookings.

The project demonstrates a layered backend architecture using Spring Boot, Spring Data JPA, Hibernate, and MySQL, along with business rules such as vehicle capacity validation, ride cancellation, ride completion, request validation, and centralized exception handling.

---

## Features

- Employee management
    - Create employee
    - Get all employees
    - Get employee by ID
    - Update employee
    - Delete employee

- Driver management
    - Create driver
    - Get all drivers
    - Get driver by ID
    - Update driver
    - Delete driver

- Vehicle management
    - Create vehicle
    - Get all vehicles
    - Get vehicle by ID
    - Update vehicle
    - Delete vehicle

- Ride management
    - Book a ride
    - Get all rides
    - Get ride by ID
    - Get rides for a specific employee
    - Cancel a ride
    - Complete a ride

- Business logic
    - Vehicle capacity validation
    - Driver and vehicle assignment
    - Ride status management
    - Prevention of invalid ride state transitions

- Validation
    - Employee input validation
    - Driver input validation
    - Vehicle input validation

- Exception handling
    - Resource not found handling
    - Booking conflict handling
    - Validation error handling
    - Centralized exception responses

---

## Tech Stack

### Backend

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Maven

### Database

- MySQL

### API Testing

- Postman

### Development Tools

- IntelliJ IDEA
- Git
- GitHub

---

## Architecture

The application follows a layered architecture:

```text
Client / Postman
       |
       v
   Controller
       |
       v
    Service
       |
       v
   Repository
       |
       v
 Spring Data JPA
       |
       v
   Hibernate
       |
       v
     MySQL