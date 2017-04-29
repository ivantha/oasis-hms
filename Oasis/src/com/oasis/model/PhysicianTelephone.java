package com.oasis.model;

public class PhysicianTelephone {
    private int id;
    private String telephone;

    public PhysicianTelephone(int id, String telephone) {
        this.id = id;
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
