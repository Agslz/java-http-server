package com.ags.httpserver;

import com.ags.httpserver.config.Configuration;
import com.ags.httpserver.config.ConfigurationManager;
import com.ags.httpserver.core.ServerListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Driver Class for the Http Server
 */
public class HttpServer {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

    public static void main(String[] args) {

        LOGGER.info("Server Starting");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        LOGGER.info("Using Webroot: {}", conf.getWebroot());
        LOGGER.info("Using Port: {}", conf.getPort());


        ServerListenerThread serverListenerThread = null;
        try {
            serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot());
            serverListenerThread.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
