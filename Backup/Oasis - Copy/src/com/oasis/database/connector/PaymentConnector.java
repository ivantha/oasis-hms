package com.oasis.database.connector;

import com.mysql.jdbc.PreparedStatement;
import com.oasis.database.Connect;
import com.oasis.model.Admission;
import com.oasis.model.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.chrono.JapaneseEra;
import java.util.Date;
import java.util.HashMap;

public class PaymentConnector extends Connect {
    public HashMap<Integer, Payment> getPaymentArrayListByAdmission(Admission admission) {
        HashMap<Integer, Payment> paymentHashMap = new HashMap<>();

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM payment " +
                    "WHERE payment.admission_id = ?");
            preparedStatement.setInt(1, admission.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("payment.id");
                double amount = resultSet.getDouble("payment.amount");
                Date paymentDate = resultSet.getDate("payment.payment_date");

                Payment payment = new Payment(id, admission.getId(), amount, paymentDate);
                paymentHashMap.put(id, payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paymentHashMap;
    }

    public void newPayment(Payment payment) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("INSERT INTO " +
                    "payment(admission_id, amount, payment_date) " +
                    "VALUES(?, ?, ?)");
            preparedStatement.setInt(1, payment.getAdmissionId());
            preparedStatement.setDouble(2, payment.getAmount());
            preparedStatement.setObject(3, payment.getPaymentDate());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePayment(Payment payment) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("DELETE FROM payment " +
                    "WHERE id = ?");
            preparedStatement.setInt(1, payment.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
