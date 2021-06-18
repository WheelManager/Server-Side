package com.hackdead.wheelmanager.repository;

import com.hackdead.wheelmanager.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByUsername(String username);

    List<Customer> findByName(String name);

    Optional<Customer> findByDni(String dni);
}
