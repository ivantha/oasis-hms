package com.oasis.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Race implements Model<Race>{
    private int id;
    private StringProperty name = new SimpleStringProperty();

    public Race() {
    }

    public Race(int id, String name) {
        this.id = id;
        this.name.setValue(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Race.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        Race r = (Race) obj;
        if(r.getId() != getId()){
            return false;
        }
        if(r.getName() != getName()){
            return false;
        }

        return true;
    }

    @Override
    public Race clone() {
        return new Race(getId(), getName());
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
