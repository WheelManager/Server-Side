package com.hackdead.wheelmanager.service;

import com.hackdead.wheelmanager.entities.Brand;

import java.util.List;

public interface IBrandService extends CrudService<Brand> {
    List<Brand> findByBrandName(String brandName) throws Exception;
}
