package com.ags.httpserver;

import com.ags.httpserver.config.Configuration;
import com.ags.httpserver.config.ConfigurationManager;

/**
 *
 * Driver Class for the Http Server
 *
 */
public class HttpServer {

    public static void main(String[] args) {

        System.out.println("Server Starting...");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");

        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Using Port: "+ conf.getPort());
        System.out.println("Using Webroot: "+conf.getWebroot());

    }


}
