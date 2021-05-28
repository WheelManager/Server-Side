package com.hackdead.wheelmanager.repository;

import com.hackdead.wheelmanager.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByLongitudeAndLatitude(Double longitude, Double latitude);
}
