package com.hackdead.wheelmanager.service.impl;

import com.hackdead.wheelmanager.entities.RentalActivity;
import com.hackdead.wheelmanager.repository.IRentalActivityRepository;
import com.hackdead.wheelmanager.service.IRentalActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RentalActivityServiceImpl implements IRentalActivityService {
    @Autowired
    private IRentalActivityRepository rentalActivityRepository;

    @Override
    @Transactional
    public RentalActivity save(RentalActivity rentalActivity) throws Exception {
        return rentalActivityRepository.save(rentalActivity);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        rentalActivityRepository.deleteById(id);
    }

    @Override
    public List<RentalActivity> getAll() throws Exception {
        return rentalActivityRepository.findAll();
    }

    @Override
    public Optional<RentalActivity> getById(Long id) throws Exception {
        return rentalActivityRepository.findById(id);
    }
}
