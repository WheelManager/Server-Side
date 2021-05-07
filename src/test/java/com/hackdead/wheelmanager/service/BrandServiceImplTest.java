package com.hackdead.wheelmanager.service;

import com.hackdead.wheelmanager.entities.Brand;
import com.hackdead.wheelmanager.repository.IBrandRepository;
import com.hackdead.wheelmanager.service.impl.BrandServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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
}
