package com.example.transportmanagement.controller;

import com.example.transportmanagement.entity.Driver;
import com.example.transportmanagement.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    private final DriverService driverService;
    public DriverController(DriverService driverService){
        this.driverService = driverService;
    }

    @PostMapping
    public Driver createDriver(@Valid @RequestBody Driver driver){
        return driverService.createDriver(driver);
    }

    @GetMapping
    public List<Driver> getAllDrivers(){
        return driverService.getAllDrivers();
    }

    @GetMapping("/{id}")
    public Driver getDriverById(@PathVariable Long id){
        return driverService.getDriverById(id);
    }

    @PutMapping("/{id}")
    public Driver updateDriver(
            @PathVariable Long id,
            @Valid @RequestBody Driver driver) {

        return driverService.updateDriver(id, driver);
    }

    @DeleteMapping("/{id}")
    public void deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
    }

}
