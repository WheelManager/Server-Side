package com.hackdead.wheelmanager.service;

import com.hackdead.wheelmanager.entities.Status;
import com.hackdead.wheelmanager.repository.IStatusRepository;
import com.hackdead.wheelmanager.service.impl.StatusServiceImpl;
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
public class StatusServiceImplTest {
    @Mock
    private IStatusRepository statusRepository;
    @InjectMocks
    private StatusServiceImpl statusService;

    @Test
    public void saveTest(){
        Status status = new Status(1L, "Occupied");

        given(statusRepository.save(status)).willReturn(status);

        Status savedStatus = null;
        try{
            savedStatus = statusService.save(status);
        }catch (Exception e){
        }
        assertThat(savedStatus).isNotNull();
        verify(statusRepository).save(any(Status.class));
    }
    @Test
    void findByIdTest() throws Exception {
        Long id = 1L;
        Status status = new Status(1L, "Occupied");

        given(statusRepository.findById(id)).willReturn(Optional.of(status));

        Optional<Status> expected = statusService.getById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void findAllTest() throws Exception {
        List<Status> statusList = new ArrayList<>();
        statusList.add(new Status(1L, "Occupied"));
        statusList.add(new Status(2L, "Free"));

        given(statusRepository.findAll()).willReturn(statusList);
        List<Status> expected = statusService.getAll();
        assertEquals(expected, statusList);
    }

    @Test
    void deleteTest() throws Exception {
        Long id = 1L;
        statusService.delete(id);
        verify(statusRepository, times(1)).deleteById(id);
    }
}
