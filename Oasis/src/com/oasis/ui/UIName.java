package com.oasis.ui;

public enum UIName {
    DASHBOARD("/com/oasis/ui/view/Dashboard.fxml");

    private final String location;

    UIName(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
