package com.hackdead.wheelmanager.service;

import com.hackdead.wheelmanager.entities.Vehicle;

import java.util.List;

public interface IVehicleService extends CrudService<Vehicle> {
    List<Vehicle> findByVehicleName(String vehicleName);
}
