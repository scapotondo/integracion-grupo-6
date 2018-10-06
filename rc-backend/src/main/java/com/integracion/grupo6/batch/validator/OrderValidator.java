package com.integracion.grupo6.batch.validator;

import com.integracion.grupo6.dto.OrderIntegrationDTO;
import com.integracion.grupo6.exception.InvalidOrderFormatException;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class OrderValidator {

    public void validate(OrderIntegrationDTO dto) throws InvalidOrderFormatException {
        validateId(dto.getId());
        validateProduct(dto.getProductId(), dto.getProductDescription());
        validateClient(dto.getClientIdentification(), dto.getClientFullName(), dto.getClientEmail());
        validateAmount(dto.getAmount());
        validateEtaDate(dto.getEtaDate());
    }

    private void validateId(Long id) throws InvalidOrderFormatException {
        if (isNullOrZero(id)) {
            throw new InvalidOrderFormatException("Order Id Invalido");
        }
    }

    private void validateProduct(String productId, String productDescription) throws InvalidOrderFormatException {
        if (isNullOrEmpty(productId)) {
            throw new InvalidOrderFormatException("Product Id Invalido");
        }
        if (isNullOrEmpty(productDescription)) {
            throw new InvalidOrderFormatException("Product Description Invalido");
        }
    }

    private void validateClient(String clientIdentification, String clientFullName, String clientEmail)
            throws InvalidOrderFormatException {
        if (isNullOrEmpty(clientIdentification)) {
            throw new InvalidOrderFormatException("Client identification Invalido");
        }
        if (isNullOrEmpty(clientFullName)) {
            throw new InvalidOrderFormatException("Client fullName Invalido");
        }
        if (isNullOrEmpty(clientEmail)) {
            throw new InvalidOrderFormatException("Client email Invalido");
        }
    }

    private void validateAmount(String amount) throws InvalidOrderFormatException {
        if (isNullOrEmpty(amount)) {
            throw new InvalidOrderFormatException("Amount Invalido");
        }
    }

    private void validateEtaDate(String etaDate) throws InvalidOrderFormatException {
        if (isNullOrEmpty(etaDate)) {
            throw new InvalidOrderFormatException("EtaDate Invalido");
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            format.parse(etaDate);
        } catch (ParseException ex) {
            throw new InvalidOrderFormatException("EtaDate Invalido");
        }
    }

    private boolean isNullOrZero(Long number) {
        return number == null || number == 0L;
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

}
