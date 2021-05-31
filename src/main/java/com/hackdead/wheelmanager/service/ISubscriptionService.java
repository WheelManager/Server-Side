package com.hackdead.wheelmanager.service;

import com.hackdead.wheelmanager.entities.Subscription;

import java.util.Date;
import java.util.List;

public interface ISubscriptionService extends CrudService<Subscription> {
    List<Subscription> findSubscriptionByStartDate(Date startDate) throws Exception;
}
