package com.hackdead.wheelmanager.service.impl;

import com.hackdead.wheelmanager.entities.Address;
import com.hackdead.wheelmanager.repository.IAddressRepository;
import com.hackdead.wheelmanager.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private IAddressRepository addressRepository;

    @Override
    @Transactional
    public Address save(Address address) throws Exception {
        return addressRepository.save(address);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        addressRepository.deleteById(id);
    }

    @Override
    public List<Address> getAll() throws Exception {
        return addressRepository.findAll();
    }

    @Override
    public Optional<Address> getById(Long id) throws Exception {
        return addressRepository.findById(id);
    }

    @Override
    public List<Address> findByLongitudeAndLatitude(Double longitude, Double latitude) throws Exception {
        return addressRepository.findByLongitudeAndLatitude(longitude, latitude);
    }
}
