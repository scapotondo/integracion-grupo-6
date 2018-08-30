package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.Client;
import com.integracion.grupo6.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client findByIdentification(String identification) {
        Optional<Client> optionalClient = clientRepository.findById(identification);
        if(optionalClient.isPresent()) {
            return optionalClient.get();
        } else {
            throw new EntityNotFoundException("No se encontro el Client con Identification " + identification);
        }
    }

    @Override
    public boolean exists(String identification) {
        try {
            findByIdentification(identification);
            return true;
        } catch (EntityNotFoundException ex) {
            return false;
        }
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }
}
