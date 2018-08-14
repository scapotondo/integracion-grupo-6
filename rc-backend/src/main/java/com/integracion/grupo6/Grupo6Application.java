package com.integracion.grupo6;

import com.integracion.grupo6.ftp.ReclamosFtpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Grupo6Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Grupo6Application.class, args);
        context.getBean(ReclamosFtpServer.class).listen();
    }
}
