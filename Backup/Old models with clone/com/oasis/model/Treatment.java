package com.oasis.model;

import javafx.beans.property.*;

import java.util.Date;

public class Treatment {
    private int id;
    private StringProperty description = new SimpleStringProperty();
    private StringProperty result = new SimpleStringProperty();
    private DoubleProperty price = new SimpleDoubleProperty();
    private ObjectProperty<Date> giveDate = new SimpleObjectProperty<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getResult() {
        return result.get();
    }

    public StringProperty resultProperty() {
        return result;
    }

    public void setResult(String result) {
        this.result.set(result);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public Date getGiveDate() {
        return giveDate.get();
    }

    public ObjectProperty<Date> giveDateProperty() {
        return giveDate;
    }

    public void setGiveDate(Date giveDate) {
        this.giveDate.set(giveDate);
    }
}
