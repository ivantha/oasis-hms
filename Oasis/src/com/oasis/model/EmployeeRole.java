package com.oasis.model;

public class EmployeeRole implements Model<EmployeeRole>{
    private int id;
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
        if(er.getId() != getId()){
            return false;
        }
        if(er.getRole() != getRole()){
            return false;
        }

        return true;
    }

    @Override
    public EmployeeRole clone() {
        return new EmployeeRole(getId(), getRole());
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
}
