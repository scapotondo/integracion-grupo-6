package com.integracion.grupo6.batch.validator;

import com.integracion.grupo6.dto.OrderIntegrationDTO;
import com.integracion.grupo6.exception.InvalidOrderFormatException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderValidatorTest {

    @Autowired
    private OrderValidator orderValidator;

    @Test
    public void validate() throws InvalidOrderFormatException {
        OrderIntegrationDTO dto = new OrderIntegrationDTO();
        dto.setId(515151L);
        dto.setClientEmail("email@domain.com");
        dto.setClientFullName("Hombre Araña");
        dto.setClientIdentification("35111222");
        dto.setProductId("123456");
        dto.setProductDescription("Empanada");
        dto.setAmount("1010.50");
        dto.setEtaDate("20180822");

        orderValidator.validate(dto);
    }

    @Test(expected = InvalidOrderFormatException.class)
    public void validateId() throws InvalidOrderFormatException {
        OrderIntegrationDTO dto = new OrderIntegrationDTO();
        dto.setId(null);

        orderValidator.validate(dto);
    }

    @Test(expected = InvalidOrderFormatException.class)
    public void validateProductId() throws InvalidOrderFormatException {
        OrderIntegrationDTO dto = new OrderIntegrationDTO();
        dto.setId(515151L);
        dto.setProductId("");

        orderValidator.validate(dto);
    }

    @Test(expected = InvalidOrderFormatException.class)
    public void validateProductDescription() throws InvalidOrderFormatException {
        OrderIntegrationDTO dto = new OrderIntegrationDTO();
        dto.setId(515151L);
        dto.setProductId("555");
        dto.setProductDescription(null);

        orderValidator.validate(dto);
    }

    @Test(expected = InvalidOrderFormatException.class)
    public void validateClient() throws InvalidOrderFormatException {
        OrderIntegrationDTO dto = new OrderIntegrationDTO();
        dto.setId(515151L);
        dto.setProductId("555");
        dto.setProductDescription("Empanada");
        dto.setClientIdentification(null);

        orderValidator.validate(dto);
    }

    @Test(expected = InvalidOrderFormatException.class)
    public void validateAmount() throws InvalidOrderFormatException {
        OrderIntegrationDTO dto = new OrderIntegrationDTO();
        dto.setId(515151L);
        dto.setProductId("555");
        dto.setProductDescription("Empanada");
        dto.setClientIdentification("123567");
        dto.setClientFullName("Maxi Gazquez");
        dto.setClientEmail("empa@nada.com");
        dto.setAmount("");

        orderValidator.validate(dto);
    }

    @Test(expected = InvalidOrderFormatException.class)
    public void validateEtaDate() throws InvalidOrderFormatException {
        OrderIntegrationDTO dto = new OrderIntegrationDTO();
        dto.setId(515151L);
        dto.setProductId("555");
        dto.setProductDescription("Empanada");
        dto.setClientIdentification("123567");
        dto.setClientFullName("Maxi Gazquez");
        dto.setClientEmail("empa@nada.com");
        dto.setAmount("555");
        dto.setEtaDate("fecha-invalida");

        orderValidator.validate(dto);
    }
}