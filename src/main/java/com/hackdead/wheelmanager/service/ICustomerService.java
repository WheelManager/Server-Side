package com.hackdead.wheelmanager.service;

import com.hackdead.wheelmanager.entities.Customer;

import java.util.List;

public interface ICustomerService extends CrudService<Customer>{
    public List<Customer> findByUsername(String username) throws Exception;
    public List<Customer> findByName(String name) throws Exception;
    public Customer findByDni(String dni) throws Exception;
}
