package com.hackdead.wheelmanager.controller;

import com.hackdead.wheelmanager.entities.Brand;
import com.hackdead.wheelmanager.entities.Status;
import com.hackdead.wheelmanager.service.impl.BrandServiceImpl;
import com.hackdead.wheelmanager.service.impl.StatusServiceImpl;
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

@WebMvcTest(controllers = StatusController.class)
@ActiveProfiles("test")
public class StatusControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StatusServiceImpl statusService;

    private List<Status> statusList;

    @BeforeEach
    void setUp() {
        statusList = new ArrayList<>();
        statusList.add(new Status(1L, "Occupied"));
        statusList.add(new Status(2L, "Free"));
    }

    @Test
    void findAllStatus() throws Exception {
        given(statusService.getAll()).willReturn(statusList);
        mockMvc.perform(get("/api/statuses")).andExpect(status().isOk());
    }

    @Test
    void findStatusById() throws Exception {
        Long StatudId = 1L;
        Status status = new Status(1L, "Occupied");

        given(statusService.getById(StatudId)).willReturn(Optional.of(status));

        mockMvc.perform(get("/api/statuses/{id}", status.getId()))
                .andExpect(status().isOk());
    }
}
