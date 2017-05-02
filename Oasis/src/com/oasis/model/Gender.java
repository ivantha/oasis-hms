package com.oasis.model;

public class Gender {
    private int id;
    private String tag;
    private String name;

    public Gender(int id, String tag){
        this.id = id;
        this.tag = tag;
        if(tag.equals("f")){
            this.name = "Female";
        }else {
            this.name = "Male";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Gender.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        Gender g = (Gender) obj;
        if(g.getId() != getId()){
            return false;
        }
        if(g.getTag() != getTag()){
            return false;
        }
        if(g.getName() != getName()){
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
