package com.oasis.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Address implements Model<Address>{
    private int id = 0;
    private StringProperty street = new SimpleStringProperty();
    private StringProperty town = new SimpleStringProperty();
    private StringProperty province = new SimpleStringProperty();
    private StringProperty postalCode = new SimpleStringProperty();

    public Address() {
    }

    public Address(int id, String street, String town, String province, String postalCode) {
        this.id = id;
        this.street.setValue(street);
        this.town.setValue(town);
        this.province.setValue(province);
        this.postalCode.setValue(postalCode);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Address.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        Address ea = (Address) obj;
        if (ea.getId() != getId()) {
            return false;
        }
        if (ea.getStreet() != getStreet()) {
            return false;
        }
        if (ea.getTown() != getTown()) {
            return false;
        }
        if (ea.getProvince() != getProvince()) {
            return false;
        }
        if (ea.getPostalCode() != getPostalCode()) {
            return false;
        }

        return true;
    }

    @Override
    public Address clone() {
        return new Address(id, getStreet(), getTown(), getProvince(), getPostalCode());
    }

    public boolean isEmpty(){
        if (!"".equals(getStreet())){
            return false;
        }
        if (!"".equals(getTown())){
            return false;
        }
        if (!"".equals(getProvince())){
            return false;
        }
        if (!"".equals(getPostalCode())){
            return false;
        }

        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street.get();
    }

    public StringProperty streetProperty() {
        return street;
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public String getTown() {
        return town.get();
    }

    public StringProperty townProperty() {
        return town;
    }

    public void setTown(String town) {
        this.town.set(town);
    }

    public String getProvince() {
        return province.get();
    }

    public StringProperty provinceProperty() {
        return province;
    }

    public void setProvince(String province) {
        this.province.set(province);
    }

    public String getPostalCode() {
        return postalCode.get();
    }

    public StringProperty postalCodeProperty() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode.set(postalCode);
    }
}
