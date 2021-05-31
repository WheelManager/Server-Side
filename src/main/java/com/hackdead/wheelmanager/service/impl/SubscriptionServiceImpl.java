package com.hackdead.wheelmanager.service.impl;

import com.hackdead.wheelmanager.entities.Subscription;
import com.hackdead.wheelmanager.repository.ISubscriptionRepository;
import com.hackdead.wheelmanager.service.ISubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SubscriptionServiceImpl implements ISubscriptionService {
    @Autowired
    private ISubscriptionRepository subscriptionRepository;

    @Override
    @Transactional
    public Subscription save(Subscription subscription) throws Exception {
        return subscriptionRepository.save(subscription);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        subscriptionRepository.deleteById(id);
    }

    @Override
    public List<Subscription> getAll() throws Exception {
        return subscriptionRepository.findAll();
    }

    @Override
    public Optional<Subscription> getById(Long id) throws Exception {
        return subscriptionRepository.findById(id);
    }

    @Override
    public List<Subscription> findSubscriptionByStartDate(Date startDate) throws Exception {
        return subscriptionRepository.findAllWithStartDate(startDate);
    }
}
