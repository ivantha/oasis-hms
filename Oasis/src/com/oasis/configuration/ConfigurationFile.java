package com.oasis.configuration;

public class ConfigurationFile {
    private String stageTitle;

    //Database
    private String serverIP;
    private String userName;
    private String password;
    private String dbName;

    public ConfigurationFile(ConfigurationHandler configurationHandler) {
        this.stageTitle = configurationHandler.getProperty("stage_title");
        this.serverIP = configurationHandler.getProperty("server_ip");
        this.userName = configurationHandler.getProperty("user_name");
        this.password = configurationHandler.getProperty("password");
        this.dbName = configurationHandler.getProperty("db_name");
    }

    public String getStageTitle() {
        return stageTitle;
    }

    public void setStageTitle(String stageTitle) {
        this.stageTitle = stageTitle;
    }

    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
