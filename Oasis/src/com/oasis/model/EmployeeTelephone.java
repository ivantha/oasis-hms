package com.oasis.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EmployeeTelephone implements Model<EmployeeTelephone>{
    private int id;
    private StringProperty telephone = new SimpleStringProperty();

    public EmployeeTelephone() {
    }

    public EmployeeTelephone(int id, String telephone) {
        this.id = id;
        this.telephone.setValue(telephone);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!EmployeeTelephone.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        EmployeeTelephone et = (EmployeeTelephone) obj;
        if (et.getId() != getId()) {
            return false;
        }
        if (et.getTelephone() != getTelephone()) {
            return false;
        }

        return true;
    }

    @Override
    public EmployeeTelephone clone() {
        return new EmployeeTelephone(getId(), getTelephone());
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
