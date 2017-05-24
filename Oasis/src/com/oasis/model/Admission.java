package com.oasis.model;

import com.oasis.common.Session;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Admission implements Model<Admission>{
    private int id = 0;
    private ObjectProperty<Patient> patientObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Physician> physicianObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Doctor> admissionConsultantObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Doctor> leadingConsultantObjectProperty = new SimpleObjectProperty<>();
    private StringProperty cause = new SimpleStringProperty();
    private ObjectProperty<LocalDate> admissionDateObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> releaseDateObjectProperty = new SimpleObjectProperty<>();

    public Admission() {
    }

    public Admission(int id, Patient patient, Physician physician, Doctor admissionConsultant,
                     Doctor leadingConsultant, String cause, LocalDate admissionDate, LocalDate releaseDate) {
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

    public ObjectProperty<Patient> patientObjectPropertyProperty() {
        return patientObjectProperty;
    }

    public void setPatientObjectProperty(Patient patientObjectProperty) {
        this.patientObjectProperty.set(patientObjectProperty);
    }

    public Physician getPhysicianObjectProperty() {
        return physicianObjectProperty.get();
    }

    public ObjectProperty<Physician> physicianObjectPropertyProperty() {
        return physicianObjectProperty;
    }

    public void setPhysicianObjectProperty(Physician physicianObjectProperty) {
        this.physicianObjectProperty.set(physicianObjectProperty);
    }

    public Doctor getAdmissionConsultantObjectProperty() {
        return admissionConsultantObjectProperty.get();
    }

    public ObjectProperty<Doctor> admissionConsultantObjectPropertyProperty() {
        return admissionConsultantObjectProperty;
    }

    public void setAdmissionConsultantObjectProperty(Doctor admissionConsultantObjectProperty) {
        this.admissionConsultantObjectProperty.set(admissionConsultantObjectProperty);
    }

    public Doctor getLeadingConsultantObjectProperty() {
        return leadingConsultantObjectProperty.get();
    }

    public ObjectProperty<Doctor> leadingConsultantObjectPropertyProperty() {
        return leadingConsultantObjectProperty;
    }

    public void setLeadingConsultantObjectProperty(Doctor leadingConsultantObjectProperty) {
        this.leadingConsultantObjectProperty.set(leadingConsultantObjectProperty);
    }

    public String getCause() {
        return cause.get();
    }

    public StringProperty causeProperty() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause.set(cause);
    }

    public LocalDate getAdmissionDateObjectProperty() {
        return admissionDateObjectProperty.get();
    }

    public ObjectProperty<LocalDate> admissionDateObjectPropertyProperty() {
        return admissionDateObjectProperty;
    }

    public void setAdmissionDateObjectProperty(LocalDate admissionDateObjectProperty) {
        this.admissionDateObjectProperty.set(admissionDateObjectProperty);
    }

    public LocalDate getReleaseDateObjectProperty() {
        return releaseDateObjectProperty.get();
    }

    public ObjectProperty<LocalDate> releaseDateObjectPropertyProperty() {
        return releaseDateObjectProperty;
    }

    public void setReleaseDateObjectProperty(LocalDate releaseDateObjectProperty) {
        this.releaseDateObjectProperty.set(releaseDateObjectProperty);
    }
}

