package com.example.transportmanagement.service;

import com.example.transportmanagement.entity.Vehicle;
import com.example.transportmanagement.exception.ResourceNotFoundException;
import com.example.transportmanagement.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Vehicle not found with id: " + id
                        )
                );
    }

    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Vehicle not found with id: " + id
                        )
                );

        vehicle.setVehicleNumber(updatedVehicle.getVehicleNumber());
        vehicle.setType(updatedVehicle.getType());
        vehicle.setCapacity(updatedVehicle.getCapacity());

        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long id) {

        if (!vehicleRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Vehicle not found with id: " + id
            );
        }

        vehicleRepository.deleteById(id);
    }
}