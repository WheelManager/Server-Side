package com.hackdead.wheelmanager.service.impl;

import com.hackdead.wheelmanager.entities.Brand;
import com.hackdead.wheelmanager.repository.IBrandRepository;
import com.hackdead.wheelmanager.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BrandServiceImpl implements IBrandService {

    @Autowired
    private IBrandRepository brandRepository;

    @Override
    @Transactional
    public Brand save(Brand brand) throws Exception {
        return brandRepository.save(brand);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        brandRepository.deleteById(id);
    }

    @Override
    public List<Brand> getAll() throws Exception {
        return brandRepository.findAll();
    }

    @Override
    public Optional<Brand> getById(Long id) throws Exception {
        return brandRepository.findById(id);
    }

    @Override
    public List<Brand> findByBrandName(String brandName) throws Exception {
        return brandRepository.findByBrandName(brandName);
    }
}
