package com.hackdead.wheelmanager.service;

import com.hackdead.wheelmanager.entities.Offer;

import java.util.List;

public interface IOfferService extends CrudService<Offer> {
    List<Offer> findByOfferName(String offerName) throws Exception;
}
