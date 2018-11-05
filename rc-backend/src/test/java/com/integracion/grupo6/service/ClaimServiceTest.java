package com.integracion.grupo6.service;

import com.integracion.grupo6.adapter.ClaimTypeAdapter;
import com.integracion.grupo6.domain.Claim;
import com.integracion.grupo6.dto.ClaimDTO;
import com.integracion.grupo6.dto.ClaimResolutionDTO;
import com.integracion.grupo6.exception.ClaimCreationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.persistence.EntityNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClaimServiceTest {

    private final static String TEST_USERNAME = "usuario-test";

    @Autowired
    private ClaimService claimService;

    @Autowired
    private ClaimTypeService claimTypeService;

    @Test
    public void create() throws ClaimCreationException {
        ClaimDTO dto = new ClaimDTO();

        dto.setId(null);
        dto.setOrderId(7777L);
        dto.setType(claimTypeService.findAll().get(0));
        dto.setClientIdentification("77777777");
        dto.setOrigin(null);

        Claim claim = claimService.create(dto, TEST_USERNAME);

        Assert.assertNotNull(claim);
    }

    @Test(expected = ClaimCreationException.class)
    public void createInvalidClaimType() throws ClaimCreationException {
        ClaimDTO dto = new ClaimDTO();

        dto.setId(null);
        dto.setOrderId(99999L);
        dto.setType(claimTypeService.findAll().get(0));
        dto.setClientIdentification("77777777");
        dto.setOrigin(null);

        claimService.create(dto, TEST_USERNAME);
    }

    @Test(expected = EntityNotFoundException.class)
    public void resolveInvalid() {
        ClaimResolutionDTO dto = new ClaimResolutionDTO();
        dto.setId_pedido("no-existe");
        dto.setFecha_entrega("01/01/2018");
        claimService.resolveClaimEndpooint(dto);
    }
    
}