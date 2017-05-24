package com.oasis.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Email implements Model<Email>{
    private int id = 0;
    private StringProperty email = new SimpleStringProperty();

    public Email() {
    }

    public Email(int id, String email) {
        this.id = id;
        this.email.setValue(email);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Email.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        Email ee = (Email) obj;
        if (ee.getId() != getId()) {
            return false;
        }
        if (ee.getEmail() != getEmail()) {
            return false;
        }

        return true;
    }

    @Override
    public Email clone() {
        return new Email(id, getEmail());
    }

    public boolean isEmpty(){
        if (!"".equals(getEmail())){
            return false;
        }

        return true;
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
