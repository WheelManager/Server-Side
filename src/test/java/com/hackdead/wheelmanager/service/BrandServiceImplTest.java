package com.hackdead.wheelmanager.service;

import com.hackdead.wheelmanager.entities.Brand;
import com.hackdead.wheelmanager.repository.IBrandRepository;
import com.hackdead.wheelmanager.service.impl.BrandServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BrandServiceImplTest {
    @Mock
    private IBrandRepository brandRepository;
    @InjectMocks
    private BrandServiceImpl brandService;

    @Test
    public void saveTest(){
        Brand brand=new Brand(1L, "Gzuk");
        given(brandRepository.save(brand)).willReturn(brand);

        Brand savedBrand = null;
        try{
            savedBrand = brandService.save(brand);
        }catch (Exception e){
        }
        assertThat(savedBrand).isNotNull();
        verify(brandRepository).save(any(Brand.class));
    }

    @Test
    void findByIdTest() throws Exception {
        Long id = 1L;
        Brand brand = new Brand(1L, "Gzuk");

        given(brandRepository.findById(id)).willReturn(Optional.of(brand));

        Optional<Brand> expected = brandService.getById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void findAllTest() throws Exception {
        List<Brand> brandList = new ArrayList<>();
        brandList.add(new Brand(1L, "Gzuk"));
        brandList.add(new Brand(2L, "Monark"));
        brandList.add(new Brand(3L, "Mark"));
        brandList.add(new Brand(4L, "BMX"));

        given(brandRepository.findAll()).willReturn(brandList);
        List<Brand> expected = brandService.getAll();
        assertEquals(expected, brandList);
    }

    @Test
    void deleteTest() throws Exception {
        Long id = 1L;
        brandService.delete(id);
        verify(brandRepository, times(1)).deleteById(id);
    }
}
