package com.hackdead.wheelmanager.service;

import com.hackdead.wheelmanager.entities.VehicleType;

import java.util.List;

public interface IVehicleTypeService extends CrudService<VehicleType> {
    List<VehicleType> findByTypeName(String typeName) throws Exception;
}
