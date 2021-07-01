package com.hackdead.wheelmanager.repository;

import com.hackdead.wheelmanager.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("Select r from Reservation r where r.startDate between :startDate and :endDate")
    List<Reservation> find(@Param("startDate") Date startDate,
                           @Param("endDate") Date endDate);
}