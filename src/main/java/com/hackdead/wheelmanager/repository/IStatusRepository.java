package com.hackdead.wheelmanager.repository;

import com.hackdead.wheelmanager.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStatusRepository extends JpaRepository<Status, Long> {
    List<Status> findByStatusName(String statusName);
}
