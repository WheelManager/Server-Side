package com.hackdead.wheelmanager.controller;

import com.hackdead.wheelmanager.entities.Brand;
import com.hackdead.wheelmanager.service.impl.BrandServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BrandController.class)
@ActiveProfiles("test")
public class BrandControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BrandServiceImpl brandService;

    private List<Brand> brandList;

    @BeforeEach
    void setUp() {
        brandList = new ArrayList<>();
        brandList.add(new Brand(1L, "Gzuk"));
        brandList.add(new Brand(2L, "Monark"));
        brandList.add(new Brand(3L, "Mark"));
        brandList.add(new Brand(4L, "BMX"));
    }

    @Test
    void findAllBrands() throws Exception {
        given(brandService.getAll()).willReturn(brandList);
        mockMvc.perform(get("/api/brands")).andExpect(status().isOk());
    }

    @Test
    void findBrandById() throws Exception {
        Long BrandId = 1L;
        Brand brand = new Brand(1L, "Gzuk");

        given(brandService.getById(BrandId)).willReturn(Optional.of(brand));

        mockMvc.perform(get("/api/brands/{id}", brand.getId()))
                .andExpect(status().isOk());
    }
}