package com.hackdead.wheelmanager.service;

import com.hackdead.wheelmanager.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService extends CrudService<Customer> {
    List<Customer> findByUsername(String username) throws Exception;

    List<Customer> findByName(String name) throws Exception;

    Optional<Customer> findByDni(String dni) throws Exception;
}
