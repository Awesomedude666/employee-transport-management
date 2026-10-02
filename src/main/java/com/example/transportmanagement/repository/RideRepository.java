package com.example.transportmanagement.repository;

import com.example.transportmanagement.entity.Ride;
import com.example.transportmanagement.entity.RideStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {

    @Query("""
            SELECT COUNT(r)
            FROM Ride r
            WHERE r.vehicle.id = :vehicleId
            AND r.status = :status
            """)
    long countByVehicleAndStatus(
            @Param("vehicleId") Long vehicleId,
            @Param("status") RideStatus status
    );

    // we don't need to write query for this, Spring data jpa can interpret it
    // by its name (findBy + employee + id) and gives us the correct query,
    // as employee is a part of ride, (from @manyToOne).
    List<Ride> findByEmployeeId(Long employeeId);
}