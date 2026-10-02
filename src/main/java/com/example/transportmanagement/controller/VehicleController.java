package com.example.transportmanagement.controller;

import com.example.transportmanagement.entity.Vehicle;
import com.example.transportmanagement.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public Vehicle createVehicle(@Valid @RequestBody Vehicle vehicle) {
        return vehicleService.createVehicle(vehicle);
    }

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id);
    }

    @PutMapping("/{id}")
    public Vehicle updateVehicle(
            @PathVariable Long id,
            @Valid @RequestBody Vehicle vehicle) {

        return vehicleService.updateVehicle(id, vehicle);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }
}