package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.Client;

import javax.persistence.EntityNotFoundException;

public interface ClientService {

    Client getByIdentification(String identification) throws EntityNotFoundException;

    boolean exists(String identification);

    Client save(Client client);

}
