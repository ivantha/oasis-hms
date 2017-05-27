package com.oasis.model;

import com.oasis.common.Session;
import javafx.beans.property.*;

import java.util.Date;

public class Payment implements Model<Payment>{
    private int id;
    private IntegerProperty admissionId = new SimpleIntegerProperty();
    private DoubleProperty amount = new SimpleDoubleProperty();
    private ObjectProperty<Date> paymentDate = new SimpleObjectProperty<>();

    public Payment(int admissionId, double amount, Date paymentDate) {
        this.admissionId.setValue(admissionId);
        this.amount.setValue(amount);
        this.paymentDate.setValue(paymentDate);
    }

    public Payment(int id, int admissionId, double amount, Date paymentDate) {
        this.id = id;
        this.admissionId.setValue(admissionId);
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

    public int getAdmissionId() {
        return admissionId.get();
    }

    public IntegerProperty admissionIdProperty() {
        return admissionId;
    }

    public void setAdmissionId(int admissionId) {
        this.admissionId.set(admissionId);
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
