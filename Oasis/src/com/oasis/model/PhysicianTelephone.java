package com.oasis.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PhysicianTelephone {
    private int id;
    private StringProperty telephone = new SimpleStringProperty();

    public PhysicianTelephone(int id, String telephone) {
        this.id = id;
        this.telephone.setValue(telephone);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone.get();
    }

    public StringProperty telephoneProperty() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone.set(telephone);
    }
}
