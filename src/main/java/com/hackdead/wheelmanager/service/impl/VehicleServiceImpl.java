package com.hackdead.wheelmanager.service.impl;

import com.hackdead.wheelmanager.entities.Vehicle;
import com.hackdead.wheelmanager.repository.IVehicleRepository;
import com.hackdead.wheelmanager.service.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class VehicleServiceImpl implements IVehicleService {
    @Autowired
    private IVehicleRepository vehicleRepository;

    @Override
    @Transactional
    public Vehicle save(Vehicle vehicle) throws Exception {
        return vehicleRepository.save(vehicle);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        vehicleRepository.deleteById(id);
    }

    @Override
    public List<Vehicle> getAll() throws Exception {
        return vehicleRepository.findAll();
    }

    @Override
    public Optional<Vehicle> getById(Long id) throws Exception {
        return vehicleRepository.findById(id);
    }



    @Override
    public List<Vehicle> findByVehicleName(String vehicleName) {
        return vehicleRepository.findByVehicleName(vehicleName);
    }
}
