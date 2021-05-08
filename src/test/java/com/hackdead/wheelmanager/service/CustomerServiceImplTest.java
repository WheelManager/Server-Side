package com.hackdead.wheelmanager.service;

import com.hackdead.wheelmanager.entities.Customer;
import com.hackdead.wheelmanager.repository.ICustomerRepository;
import com.hackdead.wheelmanager.service.impl.CustomerServiceImpl;
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
public class CustomerServiceImplTest {
    @Mock
    private ICustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    public void saveTest(){
        Customer customer = new Customer(1L, "Juan", "asdf", "jose@gmail.com", "Jose", "Carlos", "url_image.com", "12345678", "Male", new Date());

        given(customerRepository.save(customer)).willReturn(customer);

        Customer savedCustomer = null;
        try{
            savedCustomer = customerService.save(customer);
        }catch (Exception e){
        }
        assertThat(savedCustomer).isNotNull();
        verify(customerRepository).save(any(Customer.class));
    }
    @Test
    void findByIdTest() throws Exception {
        Long id = 1L;
        Customer customer = new Customer(1L, "Juan", "asdf", "jose@gmail.com", "Jose", "Carlos", "url_image.com", "12345678", "Male", new Date());

        given(customerRepository.findById(id)).willReturn(Optional.of(customer));

        Optional<Customer> expected = customerService.getById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void findAllTest() throws Exception {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer(1L, "Juan", "asdf", "jose@gmail.com", "Jose", "Carlos", "url_image.com", "12345678", "Male", new Date()));
        customerList.add(new Customer(2L, "Juan", "asdf", "jose@gmail.com", "Jose", "Carlos", "url_image.com", "12345678", "Male", new Date()));
        customerList.add(new Customer(3L, "Juan", "asdf", "jose@gmail.com", "Jose", "Carlos", "url_image.com", "12345678", "Male", new Date()));
        customerList.add(new Customer(4L, "Juan", "asdf", "jose@gmail.com", "Jose", "Carlos", "url_image.com", "12345678", "Male", new Date()));

        given(customerRepository.findAll()).willReturn(customerList);
        List<Customer> expected = customerService.getAll();
        assertEquals(expected, customerList);
    }

    @Test
    void deleteTest() throws Exception {
        Long id = 1L;
        customerService.delete(id);
        verify(customerRepository, times(1)).deleteById(id);
    }
}
