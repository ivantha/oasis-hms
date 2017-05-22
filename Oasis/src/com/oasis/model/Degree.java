package com.oasis.model;

import com.oasis.common.Session;

public class Degree implements Model<Degree>{
    private int id = 0;
    private String name;
    private String acronym;

    public Degree(int id, String name, String acronym) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
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
        if (!Degree.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        Degree d = (Degree) obj;
        if (d.getId() != getId()) {
            return false;
        }
        if (!d.getName().equals(getName())) {
            return false;
        }
        if (!d.getAcronym().equals(getAcronym())) {
            return false;
        }

        return true;
    }

    @Override
    public Degree clone() {
        return Session.cloner.deepClone(this);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }
}
