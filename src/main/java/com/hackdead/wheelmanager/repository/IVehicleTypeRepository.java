package com.hackdead.wheelmanager.repository;

import com.hackdead.wheelmanager.entities.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVehicleTypeRepository extends JpaRepository<VehicleType, Long> {
    List<VehicleType> findByTypeName(String typeName);
}
