package com.oasis.configuration;

public class ConfigurationFile {
    private String stageTitle;

    public ConfigurationFile(ConfigurationHandler configurationHandler) {
        this.stageTitle = configurationHandler.getProperty("stage_title");
    }

    public String getStageTitle() {
        return stageTitle;
    }

    public void setStageTitle(String stageTitle) {
        this.stageTitle = stageTitle;
    }
}
