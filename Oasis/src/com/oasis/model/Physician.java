package com.oasis.model;

import com.oasis.common.Session;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Physician implements Model<Physician> {
    private int id = 0;
    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty middleName = new SimpleStringProperty();
    private StringProperty lastName = new SimpleStringProperty();
    private ObjectProperty<PhysicianDesignation> physicianDesignationObjectProperty = new SimpleObjectProperty<>();
    private ArrayList<Telephone> telephoneArrayList = new ArrayList<>();

    public Physician() {
    }

    public Physician(int id, String firstName, String middleName, String lastName, PhysicianDesignation physicianDesignation) {
        this.id = id;
        this.firstName.setValue(firstName);
        this.middleName.setValue(middleName);
        this.lastName.setValue(lastName);
        this.physicianDesignationObjectProperty.setValue(physicianDesignation);
    }

    @Override
    public String toString() {
        return getFirstName() + (getMiddleName() != null ? " " + getMiddleName() : "") + " " + getLastName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Physician.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        Physician p = (Physician) obj;
        if (p.getId() != getId()) {
            return false;
        }
        if (p.getFirstName() != getFirstName()) {
            return false;
        }
        if (p.getMiddleName() != getMiddleName()) {
            return false;
        }
        if (p.getLastName() != getLastName()) {
            return false;
        }
        if (!p.getPhysicianDesignationObjectProperty().equals(getPhysicianDesignationObjectProperty())) {
            return false;
        }
        if (!(p.getTelephoneArrayList().isEmpty() ? getTelephoneArrayList().isEmpty() :
                p.getTelephoneArrayList().equals(getTelephoneArrayList()))) {
            return false;
        }

        return true;
    }

    @Override
    public Physician clone() {
        return Session.cloner.deepClone(this);
    }

    public boolean isEmpty() {
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName.get();
    }

    public void setMiddleName(String middleName) {
        this.middleName.set(middleName);
    }

    public StringProperty middleNameProperty() {
        return middleName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public PhysicianDesignation getPhysicianDesignationObjectProperty() {
        return physicianDesignationObjectProperty.get();
    }

    public void setPhysicianDesignationObjectProperty(PhysicianDesignation physicianDesignationObjectProperty) {
        this.physicianDesignationObjectProperty.set(physicianDesignationObjectProperty);
    }

    public ObjectProperty<PhysicianDesignation> physicianDesignationObjectPropertyProperty() {
        return physicianDesignationObjectProperty;
    }

    public ArrayList<Telephone> getTelephoneArrayList() {
        return telephoneArrayList;
    }

    public void setTelephoneArrayList(ArrayList<Telephone> telephoneArrayList) {
        this.telephoneArrayList = telephoneArrayList;
    }
}
