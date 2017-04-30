package com.oasis.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Physician {
    private int id;
    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty middleName = new SimpleStringProperty();
    private StringProperty lastName = new SimpleStringProperty();
    private PhysicianDesignation physicianDesignation;
    private ArrayList<PhysicianTelephone> physicianTelephoneArrayList = new ArrayList<>();

    public Physician(int id, String firstName, String middleName, String lastName, PhysicianDesignation physicianDesignation) {
        this.id = id;
        this.firstName.setValue(firstName);
        this.middleName.setValue(middleName);
        this.lastName.setValue(lastName);
        this.physicianDesignation = physicianDesignation;
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

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getMiddleName() {
        return middleName.get();
    }

    public StringProperty middleNameProperty() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName.set(middleName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public PhysicianDesignation getPhysicianDesignation() {
        return physicianDesignation;
    }

    public void setPhysicianDesignation(PhysicianDesignation physicianDesignation) {
        this.physicianDesignation = physicianDesignation;
    }

    public ArrayList<PhysicianTelephone> getPhysicianTelephoneArrayList() {
        return physicianTelephoneArrayList;
    }

    public void setPhysicianTelephoneArrayList(ArrayList<PhysicianTelephone> physicianTelephoneArrayList) {
        this.physicianTelephoneArrayList = physicianTelephoneArrayList;
    }
}
