package com.oasis.model;

import com.oasis.common.Session;

public class EmployeeRole implements Model<EmployeeRole> {
    private int id = 0;
    private String role;

    public EmployeeRole(int id, String role) {
        this.id = id;
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!EmployeeRole.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        EmployeeRole er = (EmployeeRole) obj;
        if (er.getId() != getId()) {
            return false;
        }
        if (er.getRole() != getRole()) {
            return false;
        }

        return true;
    }

    @Override
    public EmployeeRole clone() {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isDoctor() {
        return getRole().equals("Doctor");
    }

    public boolean isNurse() {
        return getRole().equals("Nurse");
    }

    public boolean isTech() {
        return getRole().equals("Tech");
    }

    public boolean isTheraphist() {
        return getRole().equals("Theraphist");
    }

    public boolean isPharmacist() {
        return getRole().equals("Pharmacist");
    }

    public boolean isMedicalAssistant() {
        return getRole().equals("Medical Assistant");
    }

    public boolean isMedicalLabTechnologist() {
        return getRole().equals("Medical Lab Technologist");
    }

    public boolean isDiatician() {
        return getRole().equals("Diatician");
    }

    public boolean isJanitor() {
        return getRole().equals("Janitor");
    }
}
