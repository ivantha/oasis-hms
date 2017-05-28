package com.oasis.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Speciality implements Model<Speciality>{
    private int id = 0;
    private StringProperty name = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();

    public Speciality() {
    }

    public Speciality(int id, String name, String description) {
        this.id = id;
        this.name.setValue(name);
        this.description.setValue(description);
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Speciality.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        Speciality s = (Speciality) obj;
        if(s.getId() != getId()){
            return false;
        }
        if(s.getName() != getName()){
            return false;
        }
        if(s.getDescription() != getDescription()){
            return false;
        }

        return true;
    }

    @Override
    public Speciality clone() {
        return new Speciality(getId(), getName(), getDescription());
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
