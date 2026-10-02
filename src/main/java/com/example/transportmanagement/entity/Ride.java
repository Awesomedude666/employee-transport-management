package com.example.transportmanagement.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Vehicle vehicle;

    private String pickupLocation;
    private String destination;

    @Enumerated(EnumType.STRING)
    private RideStatus status;

    private LocalDateTime bookingTime;

    public Ride() {
    }

    public Ride(Employee employee,
                Driver driver,
                Vehicle vehicle,
                String pickupLocation,
                String destination,
                RideStatus status,
                LocalDateTime bookingTime) {

        this.employee = employee;
        this.driver = driver;
        this.vehicle = vehicle;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.status = status;
        this.bookingTime = bookingTime;
    }

    public Long getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }
}