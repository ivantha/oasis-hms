package com.oasis.database.connector;

import com.mysql.jdbc.PreparedStatement;
import com.oasis.database.Connect;
import com.oasis.model.Admission;
import com.oasis.model.Charge;
import com.oasis.model.ChargeType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;

public class ChargeConnector extends Connect{

    public HashMap<Integer, Charge> getChargeHashMapByAdmission(Admission admission) {
        HashMap<Integer, Charge> chargeHashMap = new HashMap<>();

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM charge " +
                    "INNER JOIN admission ON charge.id = admission.charge_id " +
                    "WHERE admission.id = ?");
            preparedStatement.setInt(1, admission.getId());
            getChargeDetails(preparedStatement, chargeHashMap, ChargeType.ADMISSION_CHARGE);

            preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM charge " +
                    "INNER JOIN treatment ON charge.id = treatment.charge_id " +
                    "INNER JOIN admission ON treatment.admission_id = admission.id " +
                    "WHERE admission.id = ?");
            preparedStatement.setInt(1, admission.getId());
            getChargeDetails(preparedStatement, chargeHashMap, ChargeType.TREATMENT_CHARGE);

            preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM charge " +
                    "INNER JOIN admission_has_test ON charge.id = admission_has_test.charge_id " +
                    "INNER JOIN admission ON admission_has_test.admission_id = admission.id " +
                    "WHERE admission.id = ?");
            preparedStatement.setInt(1, admission.getId());
            getChargeDetails(preparedStatement, chargeHashMap, ChargeType.TEST_CHARGE);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chargeHashMap;
    }

    public void getChargeDetails(PreparedStatement preparedStatement, HashMap<Integer, Charge> chargeHashMap, ChargeType chargeType){
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("charge.id");
                double amount = resultSet.getDouble("charge.amount");
                String description = resultSet.getString("charge.description");
                Date chargedDate = resultSet.getDate("charge.charged_date");

                Charge charge = new Charge(id, amount, description, chargedDate, chargeType);
                chargeHashMap.put(id, charge);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void newCharge(Charge charge) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("INSERT INTO " +
                    "charge(amount, description, charged_date) " +
                    "VALUES(?, ?, ?)");
            preparedStatement.setDouble(1, charge.getAmount());
            if(null == charge.getDescription()){
                preparedStatement.setNull(2, Types.VARCHAR);
            }else {
                preparedStatement.setString(2, charge.getDescription());
            }
            preparedStatement.setDate(3, new java.sql.Date(charge.getChargedDate().getTime()));

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int newChargeWithReturnId(Charge charge) {
        int chardeId = 0;

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("INSERT INTO " +
                    "charge(amount, description, charged_date) " +
                    "VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDouble(1, charge.getAmount());
            if(null == charge.getDescription()){
                preparedStatement.setNull(2, Types.VARCHAR);
            }else {
                preparedStatement.setString(2, charge.getDescription());
            }
            preparedStatement.setDate(3, new java.sql.Date(charge.getChargedDate().getTime()));

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                chardeId = resultSet.getInt(1);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chardeId;
    }

    public void updateCharge(Charge charge) {

    }

    public void deleteCharge(Charge charge) {

    }
}
