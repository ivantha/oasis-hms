package com.oasis.model;

import java.util.ArrayList;

public class Physician {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private PhysicianDesignation physicianDesignation;
    private ArrayList<PhysicianTelephone> physicianTelephoneArrayList = new ArrayList<>();

    public Physician(int id, String firstName, String middleName, String lastName, PhysicianDesignation physicianDesignation) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.physicianDesignation = physicianDesignation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
