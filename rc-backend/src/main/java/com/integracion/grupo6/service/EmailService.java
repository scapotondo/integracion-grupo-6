package com.integracion.grupo6.service;

public interface EmailService {

    void sendMessage(String to, String subject, String text);

}
