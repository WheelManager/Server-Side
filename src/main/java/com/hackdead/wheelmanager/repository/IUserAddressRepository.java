package com.hackdead.wheelmanager.repository;

import com.hackdead.wheelmanager.entities.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IUserAddressRepository extends JpaRepository<UserAddress, Long> {
}
