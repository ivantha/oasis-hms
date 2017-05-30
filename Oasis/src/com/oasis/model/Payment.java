package com.oasis.model;

import com.oasis.common.Session;
import javafx.beans.property.*;

import java.util.Date;

public class Payment implements Model<Payment>{
    private int id;
    private IntegerProperty admissioid = new SimpleIntegerProperty();
    private DoubleProperty amount = new SimpleDoubleProperty();
    private ObjectProperty<Date> paymentDate = new SimpleObjectProperty<>();

    public Payment(int admissioid, double amount, Date paymentDate) {
        this.admissioid.setValue(admissioid);
        this.amount.setValue(amount);
        this.paymentDate.setValue(paymentDate);
    }

    public Payment(int id, int admissioid, double amount, Date paymentDate) {
        this.id = id;
        this.admissioid.setValue(admissioid);
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

    public int getAdmissioid() {
        return admissioid.get();
    }

    public IntegerProperty admissioidProperty() {
        return admissioid;
    }

    public void setAdmissioid(int admissioid) {
        this.admissioid.set(admissioid);
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
