package com.hackdead.wheelmanager.service;

import com.hackdead.wheelmanager.entities.VehicleType;

import java.util.List;

public interface IVehicleTypeService extends CrudService<VehicleType>{
    public List<VehicleType> findByName(String name) throws Exception;
}
