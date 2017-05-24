package com.oasis.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Test implements Model<Test>{
    private int id = 0;
    private StringProperty name = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private DoubleProperty basePrice = new SimpleDoubleProperty();

    public Test() {
    }

    public Test(int id, String name, String description, double basePrice) {
        this.id = id;
        this.name.setValue(name);
        this.description.setValue(description);
        this.basePrice.setValue(basePrice);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Test.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        Test t = (Test) obj;
        if(t.getId() != getId()){
            return false;
        }
        if(t.getName() != getName()){
            return false;
        }
        if(t.getDescription() != getDescription()){
            return false;
        }
        if(t.getBasePrice() != getBasePrice()){
            return false;
        }

        return true;
    }

    @Override
    public Test clone() {
        return new Test(getId(), getName(), getDescription(), getBasePrice());
    }

    public boolean isEmpty(){
        return false;
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

    public double getBasePrice() {
        return basePrice.get();
    }

    public DoubleProperty basePriceProperty() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice.set(basePrice);
    }
}
