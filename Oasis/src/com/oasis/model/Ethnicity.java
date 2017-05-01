package com.oasis.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ethnicity {
    private int id;
    private StringProperty name = new SimpleStringProperty();

    public Ethnicity() {
    }

    public Ethnicity(int id, String name) {
        this.id = id;
        this.name.setValue(name);
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
}
