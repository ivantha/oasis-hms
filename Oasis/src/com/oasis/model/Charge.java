package com.oasis.model;

import com.oasis.common.Session;
import javafx.beans.property.*;

import java.time.LocalDate;

public class Charge implements Model<Charge>{
    private int id;
    private DoubleProperty amount = new SimpleDoubleProperty();
    private StringProperty description = new SimpleStringProperty();
    private ObjectProperty<LocalDate> chargedDate = new SimpleObjectProperty<>();
    private ObjectProperty<ChargeType> chargeType = new SimpleObjectProperty<>();

    public Charge(int id, double amount, String description, LocalDate chargedDate, ChargeType chargeType) {
        this.id = id;
        this.amount.setValue(amount);
        this.description.setValue(description);
        this.chargedDate.setValue(chargedDate);
        this.chargeType.setValue(chargeType);
    }

    @Override
    public Charge clone() {
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

    public double getAmount() {
        return amount.get();
    }

    public DoubleProperty amountProperty() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
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

    public LocalDate getChargedDate() {
        return chargedDate.get();
    }

    public ObjectProperty<LocalDate> chargedDateProperty() {
        return chargedDate;
    }

    public void setChargedDate(LocalDate chargedDate) {
        this.chargedDate.set(chargedDate);
    }

    public ChargeType getChargeType() {
        return chargeType.get();
    }

    public ObjectProperty<ChargeType> chargeTypeProperty() {
        return chargeType;
    }

    public void setChargeType(ChargeType chargeType) {
        this.chargeType.set(chargeType);
    }
}
