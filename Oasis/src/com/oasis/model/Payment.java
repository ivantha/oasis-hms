package com.oasis.model;

import com.oasis.common.Session;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Date;

public class Payment implements Model<Payment>{
    private int id;
    private DoubleProperty amount = new SimpleDoubleProperty();
    private ObjectProperty<Date> paymentDate = new SimpleObjectProperty<>();

    public Payment(int id, double amount, Date paymentDate) {
        this.id = id;
        this.amount.setValue(amount);
        this.paymentDate.setValue(paymentDate);
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
    public Payment clone() {
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

    public Date getPaymentDate() {
        return paymentDate.get();
    }

    public ObjectProperty<Date> paymentDateProperty() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate.set(paymentDate);
    }
}
