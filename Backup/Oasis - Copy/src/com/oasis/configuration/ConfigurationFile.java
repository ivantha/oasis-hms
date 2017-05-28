package com.oasis.configuration;

public class ConfigurationFile {
    private ConfigurationHandler configurationHandler;

    private String stageTitle;

    //Database
    private String serverIP;
    private String userName;
    private String password;
    private String dbName;

    //Tab buttons
    private String tabButton1;
    private String tabButton2;
    private String tabButton3;
    private String tabButton4;
    private String tabButton5;
    private String tabButton6;
    private String tabButton7;

    public ConfigurationFile(ConfigurationHandler configurationHandler) {
        this.configurationHandler = configurationHandler;

        this.stageTitle = configurationHandler.getProperty("stage_title");
        this.serverIP = configurationHandler.getProperty("server_ip");
        this.userName = configurationHandler.getProperty("user_name");
        this.password = configurationHandler.getProperty("password");
        this.dbName = configurationHandler.getProperty("db_name");

        this.tabButton1 = configurationHandler.getProperty("tab_button_1");
        this.tabButton2 = configurationHandler.getProperty("tab_button_2");
        this.tabButton3 = configurationHandler.getProperty("tab_button_3");
        this.tabButton4 = configurationHandler.getProperty("tab_button_4");
        this.tabButton5 = configurationHandler.getProperty("tab_button_5");
        this.tabButton6 = configurationHandler.getProperty("tab_button_6");
        this.tabButton7 = configurationHandler.getProperty("tab_button_7");
    }

    public String getStageTitle() {
        return stageTitle;
    }

    public void setStageTitle(String stageTitle) {
        this.stageTitle = stageTitle;
        configurationHandler.setProperty("stage_title", stageTitle);
    }

    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
        configurationHandler.setProperty("server_ip", serverIP);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        configurationHandler.setProperty("user_name", userName);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        configurationHandler.setProperty("password", password);
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
        configurationHandler.setProperty("db_name", dbName);

    }

    public String getTabButton1() {
        return tabButton1;
    }

    public void setTabButton1(String tabButton1) {
        this.tabButton1 = tabButton1;
        configurationHandler.setProperty("tab_button_1", tabButton1);
    }

    public String getTabButton2() {
        return tabButton2;
    }

    public void setTabButton2(String tabButton2) {
        this.tabButton2 = tabButton2;
        configurationHandler.setProperty("tab_button_2", tabButton2);
    }

    public String getTabButton3() {
        return tabButton3;
    }

    public void setTabButton3(String tabButton3) {
        this.tabButton3 = tabButton3;
        configurationHandler.setProperty("tab_button_3", tabButton3);
    }

    public String getTabButton4() {
        return tabButton4;
    }

    public void setTabButton4(String tabButton4) {
        this.tabButton4 = tabButton4;
        configurationHandler.setProperty("tab_button_4", tabButton4);
    }

    public String getTabButton5() {
        return tabButton5;
    }

    public void setTabButton5(String tabButton5) {
        this.tabButton5 = tabButton5;
        configurationHandler.setProperty("tab_button_5", tabButton5);
    }

    public String getTabButton6() {
        return tabButton6;
    }

    public void setTabButton6(String tabButton6) {
        this.tabButton6 = tabButton6;
        configurationHandler.setProperty("tab_button_6", tabButton6);
    }

    public String getTabButton7() {
        return tabButton7;
    }

    public void setTabButton7(String tabButton7) {
        this.tabButton7 = tabButton7;
        configurationHandler.setProperty("tab_button_7", tabButton7);
    }

    public void setDefaultConfig(){
        configurationHandler.setDefaultConfigurations();
    }
}
