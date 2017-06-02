package com.oasis.model;

import com.oasis.common.Session;

public class BloodGroup implements Model<BloodGroup> {
    private int id = 0;
    private String name;

    public BloodGroup(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!BloodGroup.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        BloodGroup bg = (BloodGroup) obj;
        if (bg.getId() != getId()) {
            return false;
        }
        if (bg.getName() != getName()) {
            return false;
        }

        return true;
    }

    @Override
    public BloodGroup clone() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
