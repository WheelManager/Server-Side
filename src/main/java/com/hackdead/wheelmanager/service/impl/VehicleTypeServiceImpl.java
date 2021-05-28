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
    private IVehicleTypeRepository vehicleTypeRepository;

    @Override
    @Transactional
    public VehicleType save(VehicleType vehicleType) throws Exception {
        return vehicleTypeRepository.save(vehicleType);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        vehicleTypeRepository.deleteById(id);
    }

    @Override
    public List<VehicleType> getAll() throws Exception {
        return vehicleTypeRepository.findAll();
    }

    @Override
    public Optional<VehicleType> getById(Long id) throws Exception {
        return vehicleTypeRepository.findById(id);
    }

    @Override
    public List<VehicleType> findByTypeName(String typeName) throws Exception {
        return vehicleTypeRepository.findByTypeName(typeName);
    }
}