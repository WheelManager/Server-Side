package com.hackdead.wheelmanager.service.impl;

import com.hackdead.wheelmanager.entities.Status;
import com.hackdead.wheelmanager.repository.IStatusRepository;
import com.hackdead.wheelmanager.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class StatusServiceImpl implements IStatusService {
    @Autowired
    private IStatusRepository statusRepository;

    @Override
    @Transactional
    public Status save(Status status) throws Exception {
        return statusRepository.save(status);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        statusRepository.deleteById(id);
    }

    @Override
    public List<Status> getAll() throws Exception {
        return statusRepository.findAll();
    }

    @Override
    public Optional<Status> getById(Long id) throws Exception {
        return statusRepository.findById(id);
    }

    @Override
    public List<Status> findByStatusName(String statusName) throws Exception {
        return statusRepository.findByStatusName(statusName);
    }
}
