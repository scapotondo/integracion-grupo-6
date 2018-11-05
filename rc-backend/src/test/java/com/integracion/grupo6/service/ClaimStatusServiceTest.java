package com.integracion.grupo6.service;

import com.integracion.grupo6.service.ClaimStatusService;
import com.integracion.grupo6.domain.ClaimStatus;
import com.integracion.grupo6.dto.ClaimStatusDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClaimStatusServiceTest {

    @Autowired
    private ClaimStatusService claimStatusService;

    @Test
    public void findCreatedStatusTest() {
        ClaimStatus status = claimStatusService.findCreatedStatus();

        Assert.assertNotNull(status);
    }

    @Test
    public void findCanceledStatusTest() {
        ClaimStatus status = claimStatusService.findCanceledStatus();

        Assert.assertNotNull(status);
    }

    @Test
    public void findClosingStatusTest() {
        ClaimStatus status = claimStatusService.findClosingStatus();

        Assert.assertNotNull(status);
    }

    @Test
    public void findAllTest() {
        List<ClaimStatusDTO> statuses = claimStatusService.findAll();

        Assert.assertNotNull(statuses);
        Assert.assertFalse(statuses.isEmpty());
    }

}