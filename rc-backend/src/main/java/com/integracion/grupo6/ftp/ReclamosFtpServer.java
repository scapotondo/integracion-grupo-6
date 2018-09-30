package com.integracion.grupo6.ftp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.listener.Listener;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.ftpserver.*;

import java.io.File;

@Component
public class ReclamosFtpServer {

    protected final Log logger = LogFactory.getLog(this.getClass());

    private static final int DEFAULT_PORT = 2221;
    private static final String DEFAULT_USER = "reclamos";
    private static final String DEFAULT_PASSWORD = "reclamos";


    @Value("${ftpserver.port}")
    private String ftpPort;

    @Value("${ftpserver.user.reclamos.homedirectory}")
    private String homeDir;

    public void listen() {
        try {
            getFtpServer().start();
            logger.info(String.format("Corriendo server FTP en localhost:%d, user: '%s', password: '%s', home-dir: '%s'", safeGetPort(), DEFAULT_USER, DEFAULT_PASSWORD, homeDir));
        } catch (FtpException ex) {
            logger.warn("Error corriendo servidor FTP", ex);
        }
    }

    private FtpServer getFtpServer() throws FtpException {
        FtpServerFactory serverFactory = new FtpServerFactory();

        serverFactory.addListener("default", getListener());
        serverFactory.setUserManager(getUserManager());
        FtpServer server = serverFactory.createServer();
        return server;
    }

    private Listener getListener() {
        int port = safeGetPort();
        ListenerFactory listenerFactory = new ListenerFactory();
        listenerFactory.setPort(port);
        return listenerFactory.createListener();
    }

    private UserManager getUserManager() {
        PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
        userManagerFactory.setFile(new File("application.properties"));
        return userManagerFactory.createUserManager();
    }

    private int safeGetPort() {
        try {
            return Integer.parseInt(ftpPort);
        } catch (Exception ex) {
            logger.warn("Error leyendo puerto para FTP, se va a utilizar el default " + DEFAULT_PORT);
            return DEFAULT_PORT;
        }
    }

}
