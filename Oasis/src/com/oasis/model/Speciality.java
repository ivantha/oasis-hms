package com.oasis.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Speciality {
    private int id;
    private StringProperty name = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();

    public Speciality() {
    }

    public Speciality(int id, String name, String description) {
        this.id = id;
        this.name.setValue(name);
        this.description.setValue(description);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
}
