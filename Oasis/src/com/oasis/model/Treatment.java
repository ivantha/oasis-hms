package com.oasis.model;

import com.oasis.common.Session;
import javafx.beans.property.*;

import java.util.Date;

public class Treatment implements Model<Treatment>{
    private int id;
    private StringProperty description = new SimpleStringProperty();
    private StringProperty result = new SimpleStringProperty();
    private ObjectProperty<Date> giveDate = new SimpleObjectProperty<>();

    public Treatment(int id, String description, String result, Date giveDate) {
        this.id = id;
        this.description.setValue(description);
        this.result.setValue(result);
        this.giveDate.setValue(giveDate);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public Treatment clone() {
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
