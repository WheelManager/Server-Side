package com.hackdead.wheelmanager.service;

import com.hackdead.wheelmanager.entities.CreditCard;

import java.util.List;

public interface ICreditCardService extends CrudService<CreditCard> {
    List<CreditCard> findCreditCardsByCardNumber(String cardNumber);
}
