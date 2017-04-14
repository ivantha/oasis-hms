package com.oasis.ui;

import org.jetbrains.annotations.Contract;

public enum UIName {
    DASHBOARD("/com/oasis/ui/view/Dashboard.fxml");

    private final String location;

    UIName(String location) {
        this.location = location;
    }

    @Contract(pure = true)
    public String getLocation() {
        return location;
    }
}
