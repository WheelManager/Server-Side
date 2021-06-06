package com.hackdead.wheelmanager.service.impl;

import com.hackdead.wheelmanager.entities.CreditCard;
import com.hackdead.wheelmanager.repository.ICreditCardRepository;
import com.hackdead.wheelmanager.service.ICreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CreditCardServiceImpl implements ICreditCardService {
    @Autowired
    private ICreditCardRepository creditCardRepository;

    @Override
    @Transactional
    public CreditCard save(CreditCard creditCard) throws Exception {
        return creditCardRepository.save(creditCard);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        creditCardRepository.deleteById(id);
    }

    @Override
    public List<CreditCard> getAll() throws Exception {
        return creditCardRepository.findAll();
    }

    @Override
    public Optional<CreditCard> getById(Long id) throws Exception {
        return creditCardRepository.findById(id);
    }

    @Override
    public List<CreditCard> findCreditCardsByCardNumber(String cardNumber) {
        return creditCardRepository.findCreditCardsByCardNumber(cardNumber);
    }
}
