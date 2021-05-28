package com.hackdead.wheelmanager.repository;

import com.hackdead.wheelmanager.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByVehicleName(String vehicleName);
}
