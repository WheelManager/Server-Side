package com.hackdead.wheelmanager.service.impl;

import com.hackdead.wheelmanager.entities.Offer;
import com.hackdead.wheelmanager.repository.IOfferRepository;
import com.hackdead.wheelmanager.service.IOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OfferServiceImpl implements IOfferService {

    @Autowired
    private IOfferRepository offerRepository;

    @Override
    @Transactional
    public Offer save(Offer offer) throws Exception {
        return offerRepository.save(offer);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        offerRepository.deleteById(id);
    }

    @Override
    public List<Offer> getAll() throws Exception {
        return offerRepository.findAll();
    }

    @Override
    public Optional<Offer> getById(Long id) throws Exception {
        return offerRepository.findById(id);
    }

    @Override
    public List<Offer> findByOfferName(String offerName) throws Exception {
        return offerRepository.findByOfferName(offerName);
    }
}
