package com.hackdead.wheelmanager.repository;

import com.hackdead.wheelmanager.entities.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICreditCardRepository extends JpaRepository<CreditCard, Long> {
    List<CreditCard> findCreditCardsByCardNumber(String cardNumber);
}
