package com.oasis.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Patient {
    private int id = 0;
    private StringProperty nic = new SimpleStringProperty();
    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty middleName = new SimpleStringProperty();
    private StringProperty lastName = new SimpleStringProperty();
    private ObjectProperty<Gender> gender = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> dob = new SimpleObjectProperty<>();
    private ObjectProperty<BloodGroup> bloodGroupObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Ethnicity> ethnicityObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Date> addedDateObjectProperty = new SimpleObjectProperty<>();
    private StringProperty description = new SimpleStringProperty();
    private ArrayList<Telephone> telephoneArrayList = new ArrayList<>();
    private ArrayList<Address> addressArrayList = new ArrayList<>();
    private ArrayList<Email> emailArrayList = new ArrayList<>();
    private ArrayList<EmergencyContact> emergencyContactArrayList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNic() {
        return nic.get();
    }

    public StringProperty nicProperty() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic.set(nic);
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

    public Gender getGender() {
        return gender.get();
    }

    public ObjectProperty<Gender> genderProperty() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender.set(gender);
    }

    public LocalDate getDob() {
        return dob.get();
    }

    public ObjectProperty<LocalDate> dobProperty() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob.set(dob);
    }

    public BloodGroup getBloodGroupObjectProperty() {
        return bloodGroupObjectProperty.get();
    }

    public ObjectProperty<BloodGroup> bloodGroupObjectPropertyProperty() {
        return bloodGroupObjectProperty;
    }

    public void setBloodGroupObjectProperty(BloodGroup bloodGroupObjectProperty) {
        this.bloodGroupObjectProperty.set(bloodGroupObjectProperty);
    }

    public Ethnicity getEthnicityObjectProperty() {
        return ethnicityObjectProperty.get();
    }

    public ObjectProperty<Ethnicity> ethnicityObjectPropertyProperty() {
        return ethnicityObjectProperty;
    }

    public void setEthnicityObjectProperty(Ethnicity ethnicityObjectProperty) {
        this.ethnicityObjectProperty.set(ethnicityObjectProperty);
    }

    public Date getAddedDateObjectProperty() {
        return addedDateObjectProperty.get();
    }

    public ObjectProperty<Date> addedDateObjectPropertyProperty() {
        return addedDateObjectProperty;
    }

    public void setAddedDateObjectProperty(Date addedDateObjectProperty) {
        this.addedDateObjectProperty.set(addedDateObjectProperty);
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

    public ArrayList<Telephone> getTelephoneArrayList() {
        return telephoneArrayList;
    }

    public void setTelephoneArrayList(ArrayList<Telephone> telephoneArrayList) {
        this.telephoneArrayList = telephoneArrayList;
    }

    public ArrayList<Address> getAddressArrayList() {
        return addressArrayList;
    }

    public void setAddressArrayList(ArrayList<Address> addressArrayList) {
        this.addressArrayList = addressArrayList;
    }

    public ArrayList<Email> getEmailArrayList() {
        return emailArrayList;
    }

    public void setEmailArrayList(ArrayList<Email> emailArrayList) {
        this.emailArrayList = emailArrayList;
    }

    public ArrayList<EmergencyContact> getEmergencyContactArrayList() {
        return emergencyContactArrayList;
    }

    public void setEmergencyContactArrayList(ArrayList<EmergencyContact> emergencyContactArrayList) {
        this.emergencyContactArrayList = emergencyContactArrayList;
    }
}
