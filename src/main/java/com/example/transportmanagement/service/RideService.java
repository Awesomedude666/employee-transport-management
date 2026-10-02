package com.example.transportmanagement.service;

import com.example.transportmanagement.entity.*;
import com.example.transportmanagement.exception.ResourceNotFoundException;
import com.example.transportmanagement.exception.BookingConflictException;
import com.example.transportmanagement.repository.DriverRepository;
import com.example.transportmanagement.repository.EmployeeRepository;
import com.example.transportmanagement.repository.RideRepository;
import com.example.transportmanagement.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RideService {

    private final RideRepository rideRepository;
    private final EmployeeRepository employeeRepository;
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;

    public RideService(
            RideRepository rideRepository,
            EmployeeRepository employeeRepository,
            DriverRepository driverRepository,
            VehicleRepository vehicleRepository) {

        this.rideRepository = rideRepository;
        this.employeeRepository = employeeRepository;
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public Ride createRide(
            Long employeeId,
            Long driverId,
            Long vehicleId,
            String pickupLocation,
            String destination) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with id: " + employeeId
                        ));

        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Driver not found with id: " + driverId
                        ));

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Vehicle not found with id: " + vehicleId
                        ));

        long currentBookings =
                rideRepository.countByVehicleAndStatus(
                        vehicleId,
                        RideStatus.BOOKED
                );

        if (currentBookings >= vehicle.getCapacity()) {
            throw new BookingConflictException(
                    "Vehicle capacity is full"
            );
        }

        Ride ride = new Ride();

        ride.setEmployee(employee);
        ride.setDriver(driver);
        ride.setVehicle(vehicle);
        ride.setPickupLocation(pickupLocation);
        ride.setDestination(destination);
        ride.setStatus(RideStatus.BOOKED);
        ride.setBookingTime(LocalDateTime.now());

        return rideRepository.save(ride);
    }

    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    public Ride getRideById(Long id) {
        return rideRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Ride not found with id: " + id
                        ));
    }

    public Ride cancelRide(Long id) {

        Ride ride = rideRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Ride not found with id: " + id
                        ));

        if (ride.getStatus().equals(RideStatus.CANCELLED)) {
            return ride;
        }

        ride.setStatus(RideStatus.CANCELLED);

        return rideRepository.save(ride);
    }

    public List<Ride> getRidesByEmployee(Long employeeId) {

        if (!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException(
                    "Employee not found with id: " + employeeId
            );
        }

        return rideRepository.findByEmployeeId(employeeId);
    }

    public Ride completeRide(Long id) {

        Ride ride = rideRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Ride not found with id: " + id
                        ));

        if (ride.getStatus() == RideStatus.CANCELLED) {
            throw new BookingConflictException(
                    "Cancelled ride cannot be completed"
            );
        }

        if (ride.getStatus() == RideStatus.COMPLETED) {
            throw new BookingConflictException(
                    "Ride is already completed"
            );
        }

        ride.setStatus(RideStatus.COMPLETED);

        return rideRepository.save(ride);
    }


}