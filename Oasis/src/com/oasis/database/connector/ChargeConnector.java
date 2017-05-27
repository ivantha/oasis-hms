package com.oasis.database.connector;

import com.mysql.jdbc.PreparedStatement;
import com.oasis.database.Connect;
import com.oasis.model.Admission;
import com.oasis.model.Charge;
import com.oasis.model.ChargeType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
}
