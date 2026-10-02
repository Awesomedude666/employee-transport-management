package com.example.transportmanagement.controller;

import com.example.transportmanagement.entity.Ride;
import com.example.transportmanagement.service.RideService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rides")
public class RideController {

    private final RideService rideService;

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @PostMapping
    public Ride createRide(
            @RequestParam Long employeeId,
            @RequestParam Long driverId,
            @RequestParam Long vehicleId,
            @RequestParam String pickupLocation,
            @RequestParam String destination) {

        return rideService.createRide(
                employeeId,
                driverId,
                vehicleId,
                pickupLocation,
                destination
        );
    }

    @GetMapping
    public List<Ride> getAllRides() {
        return rideService.getAllRides();
    }

    @GetMapping("/{id}")
    public Ride getRideById(@PathVariable Long id) {
        return rideService.getRideById(id);
    }

    @PutMapping("/{id}/cancel")
    public Ride cancelRide(@PathVariable Long id) {
        return rideService.cancelRide(id);
    }

    @GetMapping("/employee/{employeeId}")
    public List<Ride> getRidesByEmployee(
            @PathVariable Long employeeId) {

        return rideService.getRidesByEmployee(employeeId);
    }

    @PutMapping("/{id}/complete")
    public Ride completeRide(@PathVariable Long id) {
        return rideService.completeRide(id);
    }
}