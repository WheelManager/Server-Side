package com.hackdead.wheelmanager.service.impl;

import com.hackdead.wheelmanager.entities.Reservation;
import com.hackdead.wheelmanager.repository.IReservationRepository;
import com.hackdead.wheelmanager.service.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ReservationServiceImpl implements IReservationService {
    @Autowired
    private IReservationRepository reservationRepository;


    @Override
    @Transactional
    public Reservation save(Reservation reservation) throws Exception {
        return reservationRepository.save(reservation);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        reservationRepository.deleteById(id);
    }

    @Override
    public List<Reservation> getAll() throws Exception {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> getById(Long id) throws Exception {
        return reservationRepository.findById(id);
    }

    @Override
    public List<Reservation> findBetweenDate(Date startDate, Date endDate) throws Exception {
        return reservationRepository.find(startDate, endDate);
    }
}
