package com.oasis.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Physician implements Model<Physician>{
    private int id;
    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty middleName = new SimpleStringProperty();
    private StringProperty lastName = new SimpleStringProperty();
    private ObjectProperty<PhysicianDesignation> physicianDesignationObjectProperty = new SimpleObjectProperty<>();
    private ArrayList<PhysicianTelephone> physicianTelephoneArrayList = new ArrayList<>();

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
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Physician.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        Physician p = (Physician) obj;
        if(p.getId() != getId()){
            return false;
        }
        if(p.getFirstName() != getFirstName()){
            return false;
        }
        if(p.getMiddleName() != getMiddleName()){
            return false;
        }
        if(p.getLastName() != getLastName()){
            return false;
        }
        if(!p.getPhysicianDesignationObjectProperty().equals(getPhysicianDesignationObjectProperty())){
            return false;
        }
        if (!p.getPhysicianTelephoneArrayList().get(0).equals(getPhysicianTelephoneArrayList().get(0))){
            return false;
        }

        return true;
    }

    @Override
    public Physician clone(){
        Physician clonedPhysician = new Physician(id, getFirstName(), getMiddleName(), getLastName(),
                getPhysicianDesignationObjectProperty().clone());
        for(PhysicianTelephone physicianTelephone: physicianTelephoneArrayList) {
            clonedPhysician.getPhysicianTelephoneArrayList().add(physicianTelephone.clone());
        }
        return clonedPhysician;
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

    public PhysicianDesignation getPhysicianDesignationObjectProperty() {
        return physicianDesignationObjectProperty.get();
    }

    public ObjectProperty<PhysicianDesignation> physicianDesignationObjectPropertyProperty() {
        return physicianDesignationObjectProperty;
    }

    public void setPhysicianDesignationObjectProperty(PhysicianDesignation physicianDesignationObjectProperty) {
        this.physicianDesignationObjectProperty.set(physicianDesignationObjectProperty);
    }

    public ArrayList<PhysicianTelephone> getPhysicianTelephoneArrayList() {
        return physicianTelephoneArrayList;
    }

    public void setPhysicianTelephoneArrayList(ArrayList<PhysicianTelephone> physicianTelephoneArrayList) {
        this.physicianTelephoneArrayList = physicianTelephoneArrayList;
    }
}
