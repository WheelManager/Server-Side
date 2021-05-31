package com.hackdead.wheelmanager.repository;

import com.hackdead.wheelmanager.entities.RentalActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRentalActivityRepository extends JpaRepository<RentalActivity, Long> {

}
