package com.hackdead.wheelmanager.repository;

import com.hackdead.wheelmanager.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Long> {
    public List<Customer> findByUsername(String username);
    public List<Customer> findByName(String name);
    public Customer findByDni(String dni);
}
