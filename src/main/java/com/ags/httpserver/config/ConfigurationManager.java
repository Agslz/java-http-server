package com.ags.httpserver.config;

import com.ags.httpserver.utils.Json;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {

    private static ConfigurationManager myConfigurationManager;
    private static Configuration myCurrentConfiguration;

    private ConfigurationManager() {
    }

    public static ConfigurationManager getInstance() {
        if (myConfigurationManager == null)
            myConfigurationManager = new ConfigurationManager();
        return myConfigurationManager;
    }

    /**
     * Loads a configuration file from the specified file path.
     *
     * @param filePath The path to the configuration file to load.
     * @throws HttpConfigurationException If an error occurs during the configuration loading process,
     *                                     including file not found, I/O errors, or JSON parsing errors.
     *                                     This exception wraps the underlying exceptions for better error handling.
     */
    public void loadConfigurationFile(String filePath) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }
        StringBuffer sb = new StringBuffer();
        int i;
        try{
            while (( i = fileReader.read()) != -1){
                sb.append((char) i);
            }
        }catch (IOException e){
            throw new HttpConfigurationException(e);
        }
        JsonNode conf = null;
        try {
            conf = Json.parse(sb.toString());
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing the configuration file", e);
        }
        try {
            myCurrentConfiguration = Json.fromJson(conf, Configuration.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing the configuration file, internal", e);
        }
    }

    /**
     * Returns the Current loaded Configuration
     */
    public Configuration getCurrentConfiguration() {
        if(myCurrentConfiguration == null){
         throw new HttpConfigurationException("No current configuration set.");
        }
        return myCurrentConfiguration;
    }
}
