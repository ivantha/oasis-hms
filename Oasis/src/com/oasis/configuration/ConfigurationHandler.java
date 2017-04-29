package com.oasis.configuration;

import java.io.*;
import java.util.Properties;

public class ConfigurationHandler {
    private final String path = "config.properties";
    private FileInputStream fileInputStream = null;
    private Properties properties = null;

    public ConfigurationHandler() {
        this.createIfNotExists();
        this.initialize();

        if (properties.isEmpty()) {
            assert properties.isEmpty();
            this.setDefaultConfigurations();
        }
    }

    private void createIfNotExists() {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initialize() {
        try {
            fileInputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        properties = new Properties();
        try {
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setDefaultConfigurations() {
        this.setProperty("stage_title", "Oasis - Hospital Management System");
        this.setProperty("server_ip", "localhost");
        this.setProperty("user_name", "root");
        this.setProperty("password", "");
        this.setProperty("db_name", "oasisdb");
    }

    public void setProperty(String property, String value) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        properties.setProperty(property, value);
        try {
            properties.store(out, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String property) {
        return properties.getProperty(property);
    }
}
