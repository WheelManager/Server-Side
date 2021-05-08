package com.hackdead.wheelmanager.controller;

import com.hackdead.wheelmanager.entities.Address;
import com.hackdead.wheelmanager.service.impl.AddressServiceImpl;
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

@WebMvcTest(controllers = AddressController.class)
@ActiveProfiles("test")
public class AddressControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AddressServiceImpl addressService;

    private List<Address> addressList;

    @BeforeEach
    void setUp() {
        addressList = new ArrayList<>();
        addressList.add(new Address(1L, 103.5,105.3,"San Jose 247"));
        addressList.add(new Address(2L, 103.5,105.3,"San Jose 247"));
        addressList.add(new Address(3L, 103.5,105.3,"San Jose 247"));
        addressList.add(new Address(4L, 103.5,105.3,"San Jose 247"));
    }

    @Test
    void findAllBrands() throws Exception {
        given(addressService.getAll()).willReturn(addressList);
        mockMvc.perform(get("/api/addresses")).andExpect(status().isOk());
    }

    @Test
    void findAddressById() throws Exception {
        Long AddressId = 1L;
        Address address = new Address(1L, 103.5,105.3,"San Jose 247");

        given(addressService.getById(AddressId)).willReturn(Optional.of(address));

        mockMvc.perform(get("/api/addresses/{id}", address.getId()))
                .andExpect(status().isOk());
    }
}
