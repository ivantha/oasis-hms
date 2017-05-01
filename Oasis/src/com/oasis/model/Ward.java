package com.oasis.model;

public class Ward{
    private int id;
    private String name;
    private String description;
    private int maxPatientCount;
    private int currentPatientCount;
    private String genderAcceptance;
    private int supervisorId;

    public Ward(int id, String name, String description, int maxPatientCount, int currentPatientCount, String genderAcceptance, int supervisorId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.maxPatientCount = maxPatientCount;
        this.currentPatientCount = currentPatientCount;
        this.genderAcceptance = genderAcceptance;
        this.supervisorId = supervisorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxPatientCount() {
        return maxPatientCount;
    }

    public void setMaxPatientCount(int maxPatientCount) {
        this.maxPatientCount = maxPatientCount;
    }

    public int getCurrentPatientCount() {
        return currentPatientCount;
    }

    public void setCurrentPatientCount(int currentPatientCount) {
        this.currentPatientCount = currentPatientCount;
    }

    public String getGenderAcceptance() {
        return genderAcceptance;
    }

    public void setGenderAcceptance(String genderAcceptance) {
        this.genderAcceptance = genderAcceptance;
    }

    public int getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }
}
