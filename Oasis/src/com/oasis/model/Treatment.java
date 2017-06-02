package com.oasis.model;

import com.oasis.common.Session;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class Treatment implements Model<Treatment> {
    private int id;
    private StringProperty description = new SimpleStringProperty();
    private StringProperty result = new SimpleStringProperty();
    private ObjectProperty<Date> givenDateObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Charge> chargeObjectProperty = new SimpleObjectProperty<>();

    public Treatment() {
    }

    public Treatment(int id, String description, String result, Date givenDateObjectProperty) {
        this.id = id;
        this.description.setValue(description);
        this.result.setValue(result);
        this.givenDateObjectProperty.setValue(givenDateObjectProperty);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Treatment.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        Treatment t = (Treatment) obj;
        if (t.getId() != getId()) {
            return false;
        }
        if (t.getDescription() != getDescription()) {
            return false;
        }
        if (t.getResult() != getResult()) {
            return false;
        }
        if (t.getGivenDateObjectProperty() != getGivenDateObjectProperty()) {
            return false;
        }

        return true;
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

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public String getResult() {
        return result.get();
    }

    public void setResult(String result) {
        this.result.set(result);
    }

    public StringProperty resultProperty() {
        return result;
    }

    public Date getGivenDateObjectProperty() {
        return givenDateObjectProperty.get();
    }

    public void setGivenDateObjectProperty(Date givenDateObjectProperty) {
        this.givenDateObjectProperty.set(givenDateObjectProperty);
    }

    public ObjectProperty<Date> givenDateObjectPropertyProperty() {
        return givenDateObjectProperty;
    }

    public Charge getChargeObjectProperty() {
        return chargeObjectProperty.get();
    }

    public void setChargeObjectProperty(Charge chargeObjectProperty) {
        this.chargeObjectProperty.set(chargeObjectProperty);
    }

    public ObjectProperty<Charge> chargeObjectPropertyProperty() {
        return chargeObjectProperty;
    }
}
