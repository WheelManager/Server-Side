package com.hackdead.wheelmanager.repository;

import com.hackdead.wheelmanager.entities.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICreditCardRepository extends JpaRepository<CreditCard, Long> {
    List<CreditCard> findById(String id);
}
