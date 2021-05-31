package com.hackdead.wheelmanager.service;

import com.hackdead.wheelmanager.entities.Address;
import com.hackdead.wheelmanager.repository.IAddressRepository;
import com.hackdead.wheelmanager.service.impl.AddressServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AddressServiceImplTest {
    @Mock
    private IAddressRepository addressRepository;
    @InjectMocks
    private AddressServiceImpl addressService;

    @Test
    public void saveTest(){
        Address address = new Address(1L, 103.5,105.3,"San Jose 247");
        given(addressRepository.save(address)).willReturn(address);

        Address savedAddress = null;
        try{
            savedAddress = addressService.save(address);
        }catch (Exception e){
        }
        assertThat(savedAddress).isNotNull();
        verify(addressRepository).save(any(Address.class));
    }

    @Test
    void findByIdTest() throws Exception {
        Long id = 1L;
        Address address = new Address(1L, 103.5,105.3,"San Jose 247");

        given(addressRepository.findById(id)).willReturn(Optional.of(address));

        Optional<Address> expected = addressService.getById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void findAllTest() throws Exception {
        List<Address> addressList = new ArrayList<>();
        addressList.add(new Address(1L, 103.5,105.3,"San Jose 247"));
        addressList.add(new Address(2L, 103.5,105.3,"San Jose 247"));
        addressList.add(new Address(3L, 103.5,105.3,"San Jose 247"));
        addressList.add(new Address(4L, 103.5,105.3,"San Jose 247"));

        given(addressRepository.findAll()).willReturn(addressList);
        List<Address> expected = addressService.getAll();
        assertEquals(expected, addressList);
    }

    @Test
    void deleteTest() throws Exception {
        Long id = 1L;
        addressService.delete(id);
        verify(addressRepository, times(1)).deleteById(id);
    }
}
