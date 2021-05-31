package com.hackdead.wheelmanager.controller;

import com.hackdead.wheelmanager.entities.Customer;
import com.hackdead.wheelmanager.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CustomerController.class)
@ActiveProfiles("test")
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerServiceImpl customerService;

    private List<Customer> customerList;

    @BeforeEach
    void setUp(){
        customerList=new ArrayList<>();
         customerList.add(new Customer(1L, "Juan", "asdf", "jose@gmail.com", "Jose", "Carlos", "url_image.com", "12345678", "Male", new Date()));
        customerList.add(new Customer(2L, "Juan", "asdf", "jose@gmail.com", "Jose", "Carlos", "url_image.com", "12345678", "Male", new Date()));
        customerList.add(new Customer(3L, "Juan", "asdf", "jose@gmail.com", "Jose", "Carlos", "url_image.com", "12345678", "Male", new Date()));
        customerList.add(new Customer(4L, "Juan", "asdf", "jose@gmail.com", "Jose", "Carlos", "url_image.com", "12345678", "Male", new Date()));
    }

    @Test
    void findAllCustomers() throws Exception {
        given(customerService.getAll()).willReturn(customerList);
        mockMvc.perform(get("/api/customers")).andExpect(status().isOk());
    }

    @Test
    void findCustomerById() throws Exception {
        Long CustomerId = 1L;
        Customer customer = new Customer(1L, "Juan", "asdf", "jose@gmail.com", "Jose", "Carlos", "url_image.com", "12345678", "Male", new Date());

        given(customerService.getById(CustomerId)).willReturn(Optional.of(customer));

        mockMvc.perform(get("/api/customers/{id}", customer.getId())).andExpect(status().isOk());
    }
}
