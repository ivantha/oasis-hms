package com.oasis.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class Admission {
    private int id;
    private ObjectProperty<Patient> patientObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Physician> physicianObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Doctor> admissionConsultantObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Doctor> leadingConsultantObjectProperty = new SimpleObjectProperty<>();
    private StringProperty cause = new SimpleStringProperty();
    private ObjectProperty<Date> admissionDateObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Date> releaseDateObjectProperty = new SimpleObjectProperty<>();

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

    public Date getAdmissionDateObjectProperty() {
        return admissionDateObjectProperty.get();
    }

    public ObjectProperty<Date> admissionDateObjectPropertyProperty() {
        return admissionDateObjectProperty;
    }

    public void setAdmissionDateObjectProperty(Date admissionDateObjectProperty) {
        this.admissionDateObjectProperty.set(admissionDateObjectProperty);
    }

    public Date getReleaseDateObjectProperty() {
        return releaseDateObjectProperty.get();
    }

    public ObjectProperty<Date> releaseDateObjectPropertyProperty() {
        return releaseDateObjectProperty;
    }

    public void setReleaseDateObjectProperty(Date releaseDateObjectProperty) {
        this.releaseDateObjectProperty.set(releaseDateObjectProperty);
    }
}
