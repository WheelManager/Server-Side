package com.hackdead.wheelmanager.service.impl;

import com.hackdead.wheelmanager.entities.Customer;
import com.hackdead.wheelmanager.repository.ICustomerRepository;
import com.hackdead.wheelmanager.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    @Transactional
    public Customer save(Customer customer) throws Exception {
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getAll() throws Exception {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getById(Long id) throws Exception {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> findByUsername(String username) throws Exception {
        return customerRepository.findByUsername(username);
    }

    @Override
    public List<Customer> findByName(String name) throws Exception {
        return customerRepository.findByName(name);
    }

    @Override
    public Optional<Customer> findByDni(String dni) throws Exception {
        return customerRepository.findByDni(dni);
    }
}
