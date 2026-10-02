package com.example.transportmanagement.repository;

import com.example.transportmanagement.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}

