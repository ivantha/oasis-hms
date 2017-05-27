package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.Admission;
import com.oasis.model.Payment;

import java.util.ArrayList;

public class PaymentServices {
    public static ArrayList<Payment> getPaymentArrayListByAdmission(Admission admission){
        ArrayList<Payment> paymentArrayList = new ArrayList<>();
        paymentArrayList.addAll(Session.paymentConnector.getPaymentArrayListByAdmission(admission).values());
        return paymentArrayList;
    }

    public static void newPayment(ArrayList<Payment> paymentArrayList){
        for(Payment payment: paymentArrayList){
            Session.paymentConnector.newPayment(payment);
        }
    }

    public static void deletePayment(ArrayList<Payment> paymentArrayList){
        for(Payment payment: paymentArrayList){
            Session.paymentConnector.deletePayment(payment);
        }
    }
}
