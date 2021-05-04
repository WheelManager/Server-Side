package com.hackdead.wheelmanager.service;

import com.hackdead.wheelmanager.entities.Status;

import java.util.List;

public interface IStatusService extends CrudService<Status>{
    public List<Status> findByName(String name) throws Exception;
}
