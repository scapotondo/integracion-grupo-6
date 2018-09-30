package com.integracion.grupo6;

import com.integracion.grupo6.ftp.ReclamosFtpServer;
import com.integracion.grupo6.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManagerFactory;

@SpringBootApplication
@Configuration
@EnableScheduling
@EnableTransactionManagement
public class Grupo6Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Grupo6Application.class, args);
        context.getBean(ReclamosFtpServer.class).listen();
    }

}
