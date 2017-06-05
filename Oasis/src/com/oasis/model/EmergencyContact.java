package com.oasis.model;

import com.oasis.common.Session;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EmergencyContact implements Model<EmergencyContact> {
    private int id = 0;
    private StringProperty name = new SimpleStringProperty();
    private StringProperty relationship = new SimpleStringProperty();
    private StringProperty telephone = new SimpleStringProperty();
    private StringProperty address = new SimpleStringProperty();

    public EmergencyContact() {
    }

    public EmergencyContact(int id, String name, String relationship, String telephone,
                            String address) {
        this.id = id;
        this.name.setValue(name);
        this.relationship.setValue(relationship);
        this.telephone.setValue(telephone);
        this.address.setValue(address);
    }

    @Override
    public boolean equals(Object obj) {
        EmergencyContact ec = (EmergencyContact) obj;
        if (ec.getId() != getId()) {
            return false;
        }
        if (!(ec.getName() == null ? getName() == null : !ec.getName().equals(getName()))) {
            return false;
        }
        if (!(ec.getRelationship() == null ? getRelationship() == null : !ec.getRelationship().equals(getRelationship()))) {
            return false;
        }
        if (!(ec.getTelephone() == null ? getTelephone() == null : !ec.getTelephone().equals(getTelephone()))) {
            return false;
        }
        if (!(ec.getAddress() == null ? getAddress() == null : !ec.getAddress().equals(getAddress()))) {
            return false;
        }

        return true;
    }

    @Override
    public EmergencyContact clone() {
        return Session.cloner.deepClone(this);
    }

    public boolean isEmpty() {
        if (null != getName() || !getName().isEmpty()) {
            return false;
        }
        if (null != getRelationship() || !getRelationship().isEmpty()) {
            return false;
        }
        if (null != getTelephone() || !getTelephone().isEmpty()) {
            return false;
        }
        if (null != getAddress() || !getAddress().isEmpty()) {
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

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getRelationship() {
        return relationship.get();
    }

    public void setRelationship(String relationship) {
        this.relationship.set(relationship);
    }

    public StringProperty relationshipProperty() {
        return relationship;
    }

    public String getTelephone() {
        return telephone.get();
    }

    public void setTelephone(String telephone) {
        this.telephone.set(telephone);
    }

    public StringProperty telephoneProperty() {
        return telephone;
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public StringProperty addressProperty() {
        return address;
    }
}
