package com.oasis.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Telephone implements Model<Telephone>{
    private int id = 0;
    private StringProperty telephone = new SimpleStringProperty();

    public Telephone() {
    }

    public Telephone(int id, String telephone) {
        this.id = id;
        this.telephone.setValue(telephone);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Telephone.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        Telephone et = (Telephone) obj;
        if (et.getId() != getId()) {
            return false;
        }
        if (et.getTelephone() != getTelephone()) {
            return false;
        }

        return true;
    }

    @Override
    public Telephone clone() {
        return new Telephone(getId(), getTelephone());
    }

    public boolean isEmpty(){
        if (!"".equals(getTelephone())){
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
