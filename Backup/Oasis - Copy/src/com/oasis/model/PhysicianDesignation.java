package com.oasis.model;

import com.oasis.common.Session;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PhysicianDesignation implements Model<PhysicianDesignation> {
    private int id = 0;
    private StringProperty name = new SimpleStringProperty();

    public PhysicianDesignation(int id, String name) {
        this.id = id;
        this.name.setValue(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!PhysicianDesignation.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        PhysicianDesignation pd = (PhysicianDesignation) obj;
        if (pd.getId() != getId()) {
            return false;
        }
        if (pd.getName() != getName()) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public PhysicianDesignation clone() {
        return Session.cloner.deepClone(this);
    }

    public boolean isEmpty(){
        return false;
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
