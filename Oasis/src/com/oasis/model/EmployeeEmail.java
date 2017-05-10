package com.oasis.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EmployeeEmail {
    private int id;
    private StringProperty email = new SimpleStringProperty();

    public EmployeeEmail() {
    }

    public EmployeeEmail(int id, String email) {
        this.id = id;
        this.email.setValue(email);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!EmployeeEmail.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        EmployeeEmail ee = (EmployeeEmail) obj;
        if (ee.getId() != getId()) {
            return false;
        }
        if (ee.getEmail() != getEmail()) {
            return false;
        }

        return true;
    }

    @Override
    public EmployeeEmail clone() {
        return new EmployeeEmail(id, getEmail());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}
