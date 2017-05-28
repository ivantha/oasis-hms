package com.oasis.ui;

import com.oasis.controller.Controller;
import javafx.scene.Parent;

public class UI {
    private final Parent parent;
    private final Controller controller;

    public UI(Parent parent, Controller controller) {
        this.parent = parent;
        this.controller = controller;
    }

    public Parent getParent() {
        return parent;
    }

    public Controller getController() {
        return controller;
    }
}
