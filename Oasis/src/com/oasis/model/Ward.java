package com.oasis.model;

import javafx.beans.property.*;

public class Ward implements Model<Ward>{
    private int id = 0;
    private StringProperty name = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private IntegerProperty maxPatientCount = new SimpleIntegerProperty();
    private IntegerProperty currentPatientCount = new SimpleIntegerProperty();
    private ObjectProperty<Gender> genderAcceptance = new SimpleObjectProperty<>();
    private IntegerProperty supervisorId = new SimpleIntegerProperty();

    public Ward() {
    }

    public Ward(int id, String name, String description, int maxPatientCount, int currentPatientCount, Gender genderAcceptance, int supervisorId) {
        this.id = id;
        this.name.setValue(name);
        this.description.setValue(description);
        this.maxPatientCount.setValue(maxPatientCount);
        this.currentPatientCount.setValue(currentPatientCount);
        this.genderAcceptance.setValue(genderAcceptance);
        this.supervisorId.setValue(supervisorId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Ward.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        Ward w = (Ward) obj;
        if(w.getId() != getId()){
            return false;
        }
        if(w.getName() != getName()){
            return false;
        }
        if(w.getDescription() != getDescription()){
            return false;
        }
        if(w.getMaxPatientCount() != getMaxPatientCount()){
            return false;
        }
        if(w.getCurrentPatientCount() != getCurrentPatientCount()){
            return false;
        }
        if(!w.getGenderAcceptance().equals(getGenderAcceptance())){
            return false;
        }
        if(w.getSupervisorId() != getSupervisorId()){
            return false;
        }

        return true;
    }

    @Override
    public Ward clone(){
        Ward clonedWard = new Ward(id, getName(), getDescription(), getMaxPatientCount(),
                getCurrentPatientCount(), getGenderAcceptance(), getSupervisorId());
        return clonedWard;
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

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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

    public int getMaxPatientCount() {
        return maxPatientCount.get();
    }

    public IntegerProperty maxPatientCountProperty() {
        return maxPatientCount;
    }

    public void setMaxPatientCount(int maxPatientCount) {
        this.maxPatientCount.set(maxPatientCount);
    }

    public int getCurrentPatientCount() {
        return currentPatientCount.get();
    }

    public IntegerProperty currentPatientCountProperty() {
        return currentPatientCount;
    }

    public void setCurrentPatientCount(int currentPatientCount) {
        this.currentPatientCount.set(currentPatientCount);
    }

    public Gender getGenderAcceptance() {
        return genderAcceptance.get();
    }

    public ObjectProperty<Gender> genderAcceptanceProperty() {
        return genderAcceptance;
    }

    public void setGenderAcceptance(Gender genderAcceptance) {
        this.genderAcceptance.set(genderAcceptance);
    }

    public int getSupervisorId() {
        return supervisorId.get();
    }

    public IntegerProperty supervisorIdProperty() {
        return supervisorId;
    }

    public void setSupervisorId(int supervisorId) {
        this.supervisorId.set(supervisorId);
    }
}
