package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.Client;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;


    @Test(expected = EntityNotFoundException.class)
    public void findByIdentification() {
        String id = "id-inexistente";

        clientService.findByIdentification(id);
    }


    @Test
    public void save() {
        Client client = new Client();
        client.setIdentification("new-id");
        client.setEmail("test@client.com");
        client.setFullName("Test Client");

        Client newClient = clientService.save(client);

        Assert.assertEquals(client.getIdentification(), newClient.getIdentification());
        Assert.assertEquals(client.getFullName(), newClient.getFullName());
        Assert.assertEquals(client.getEmail(), newClient.getEmail());
    }

    @Test
    public void update() {
        final String otherEmail = "other@email.com";
        Client client = new Client();
        client.setIdentification("new-id-1");
        client.setEmail("test@client.com");
        client.setFullName("Test Client");

        Client newClient = clientService.save(client);
        newClient.setEmail(otherEmail);

        newClient = clientService.save(newClient);

        Assert.assertEquals(otherEmail, newClient.getEmail());
    }

}