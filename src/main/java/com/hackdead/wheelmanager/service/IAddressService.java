package com.hackdead.wheelmanager.service;

import com.hackdead.wheelmanager.entities.Address;

import java.util.List;

public interface IAddressService extends CrudService<Address> {
    List<Address> findByLongitudeAndLatitude(Double longitude, Double latitude) throws Exception;
}
