package com.hackdead.wheelmanager.service;

import com.hackdead.wheelmanager.entities.Reservation;

import java.util.Date;
import java.util.List;

public interface IReservationService extends CrudService<Reservation> {
    List<Reservation> findBetweenDate(Date startDate, Date endDate) throws Exception;
}
