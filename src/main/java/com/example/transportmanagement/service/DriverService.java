package com.example.transportmanagement.service;

import com.example.transportmanagement.entity.Driver;
import com.example.transportmanagement.exception.ResourceNotFoundException;
import com.example.transportmanagement.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {
    private final DriverRepository driverRepository;

    DriverService(DriverRepository driverRepository){
        this.driverRepository = driverRepository;
    }

    public Driver createDriver(Driver driver){
        return driverRepository.save(driver);
    }

    public List<Driver> getAllDrivers(){
        return driverRepository.findAll();
    }

    public Driver getDriverById(Long id){
        return driverRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Driver not found with id : " + id)
                );
    }

    public Driver updateDriver(Long id, Driver updatedDriver) {

        Driver driver = driverRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Driver not found with id: " + id
                        )
                );

        driver.setName(updatedDriver.getName());
        driver.setPhone(updatedDriver.getPhone());
        driver.setLicenseNumber(updatedDriver.getLicenseNumber());

        return driverRepository.save(driver);
    }

    public void deleteDriver(Long id) {

        if (!driverRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Driver not found with id: " + id
            );
        }

        driverRepository.deleteById(id);
    }

}
