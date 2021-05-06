package com.hackdead.wheelmanager.repository;

import com.hackdead.wheelmanager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    public List<User> findByUsername(String username);
}
