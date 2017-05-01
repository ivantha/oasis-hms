package com.oasis.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PhysicianTelephone implements Model<PhysicianTelephone> {
    private int id;
    private StringProperty telephone = new SimpleStringProperty();

    public PhysicianTelephone() {
    }

    public PhysicianTelephone(int id, String telephone) {
        this.id = id;
        this.telephone.setValue(telephone);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!PhysicianTelephone.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        PhysicianTelephone pt = (PhysicianTelephone) obj;
        if (pt.getId() != getId()) {
            return false;
        }
        if (pt.getTelephone() != getTelephone()) {
            return false;
        }

        return true;
    }

    @Override
    public PhysicianTelephone clone() {
        return new PhysicianTelephone(id, getTelephone());
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
