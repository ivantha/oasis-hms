package com.oasis.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class Admission implements Model<Admission> {
    private int id = 0;
    private ObjectProperty<Patient> patientObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Physician> physicianObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Doctor> admissionConsultantObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Doctor> leadingConsultantObjectProperty = new SimpleObjectProperty<>();
    private StringProperty cause = new SimpleStringProperty();
    private ObjectProperty<Date> admissionDateObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Date> releaseDateObjectProperty = new SimpleObjectProperty<>();

    public Admission() {
    }

    public Admission(int id, Patient patient, Physician physician, Doctor admissionConsultant,
                     Doctor leadingConsultant, String cause, Date admissionDate, Date releaseDate) {
        this.id = id;
        this.patientObjectProperty.setValue(patient);
        this.physicianObjectProperty.setValue(physician);
        this.admissionConsultantObjectProperty.setValue(admissionConsultant);
        this.leadingConsultantObjectProperty.setValue(leadingConsultant);
        this.cause.setValue(cause);
        this.admissionDateObjectProperty.setValue(admissionDate);
        this.releaseDateObjectProperty.setValue(releaseDate);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public Admission clone() {
        Admission clonedAdmission = new Admission(id, getPatientObjectProperty().clone(), getPhysicianObjectProperty().clone(),
                getAdmissionConsultantObjectProperty().clone(), getLeadingConsultantObjectProperty().clone(), getCause(),
                getAdmissionDateObjectProperty(), getReleaseDateObjectProperty());
        return clonedAdmission;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatientObjectProperty() {
        return patientObjectProperty.get();
    }

    public void setPatientObjectProperty(Patient patientObjectProperty) {
        this.patientObjectProperty.set(patientObjectProperty);
    }

    public ObjectProperty<Patient> patientObjectPropertyProperty() {
        return patientObjectProperty;
    }

    public Physician getPhysicianObjectProperty() {
        return physicianObjectProperty.get();
    }

    public void setPhysicianObjectProperty(Physician physicianObjectProperty) {
        this.physicianObjectProperty.set(physicianObjectProperty);
    }

    public ObjectProperty<Physician> physicianObjectPropertyProperty() {
        return physicianObjectProperty;
    }

    public Doctor getAdmissionConsultantObjectProperty() {
        return admissionConsultantObjectProperty.get();
    }

    public void setAdmissionConsultantObjectProperty(Doctor admissionConsultantObjectProperty) {
        this.admissionConsultantObjectProperty.set(admissionConsultantObjectProperty);
    }

    public ObjectProperty<Doctor> admissionConsultantObjectPropertyProperty() {
        return admissionConsultantObjectProperty;
    }

    public Doctor getLeadingConsultantObjectProperty() {
        return leadingConsultantObjectProperty.get();
    }

    public void setLeadingConsultantObjectProperty(Doctor leadingConsultantObjectProperty) {
        this.leadingConsultantObjectProperty.set(leadingConsultantObjectProperty);
    }

    public ObjectProperty<Doctor> leadingConsultantObjectPropertyProperty() {
        return leadingConsultantObjectProperty;
    }

    public String getCause() {
        return cause.get();
    }

    public void setCause(String cause) {
        this.cause.set(cause);
    }

    public StringProperty causeProperty() {
        return cause;
    }

    public Date getAdmissionDateObjectProperty() {
        return admissionDateObjectProperty.get();
    }

    public void setAdmissionDateObjectProperty(Date admissionDateObjectProperty) {
        this.admissionDateObjectProperty.set(admissionDateObjectProperty);
    }

    public ObjectProperty<Date> admissionDateObjectPropertyProperty() {
        return admissionDateObjectProperty;
    }

    public Date getReleaseDateObjectProperty() {
        return releaseDateObjectProperty.get();
    }

    public void setReleaseDateObjectProperty(Date releaseDateObjectProperty) {
        this.releaseDateObjectProperty.set(releaseDateObjectProperty);
    }

    public ObjectProperty<Date> releaseDateObjectPropertyProperty() {
        return releaseDateObjectProperty;
    }
}

