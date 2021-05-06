package com.hackdead.wheelmanager.service.impl;

import com.hackdead.wheelmanager.entities.VehicleType;
import com.hackdead.wheelmanager.repository.IVehicleTypeRepository;
import com.hackdead.wheelmanager.service.IVehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class VehicleTypeServiceImpl implements IVehicleTypeService {

    @Autowired
    private IVehicleTypeRepository VehicleTypeRepository;

    @Override
    @Transactional
    public VehicleType save(VehicleType vehicleType) throws Exception {
        return VehicleTypeRepository.save(vehicleType);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        VehicleTypeRepository.deleteById(id);
    }

    @Override
    public List<VehicleType> getAll() throws Exception {
        return VehicleTypeRepository.findAll();
    }
    @Override
    public Optional<VehicleType> getById(Long id) throws Exception {
        return VehicleTypeRepository.findById(id);
    }
    @Override
    public List<VehicleType> findByName(String name) throws Exception {
        return VehicleTypeRepository.findByName(name);
    }
}