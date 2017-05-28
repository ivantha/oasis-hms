package com.oasis.database.connector;

import com.mysql.jdbc.PreparedStatement;
import com.oasis.database.Connect;
import com.oasis.model.Admission;
import com.oasis.model.Treatment;
import com.oasis.services.ChargeServices;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;

public class TreatmentConnector extends Connect{

    public HashMap<Integer, Treatment> getTreatmentArrayListByAdmission(Admission admission) {
        HashMap<Integer, Treatment> treatmentHashMap = new HashMap<>();

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM treatment " +
                    "WHERE treatment.admission_id = ?");
            preparedStatement.setInt(1, admission.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("treatment.id");
                String description = resultSet.getString("treatment.description");
                String result = resultSet.getString("treatment.result");
                Date givenDate = resultSet.getDate("treatment.given_date");

                Treatment treatment = new Treatment(id, description, result, givenDate);
                treatmentHashMap.put(id, treatment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return treatmentHashMap;
    }

    public void newTreatment(Treatment treatment, int admissionId) {
        if(null != treatment.getChargeObjectProperty()){
            int chargeId = ChargeServices.newChargeWithReturnId(treatment.getChargeObjectProperty());
            treatment.getChargeObjectProperty().setId(chargeId);
        }

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("INSERT INTO " +
                    "treatment(admission_id, description, result, given_date, charge_id) " +
                    "VALUES(?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, admissionId);
            preparedStatement.setString(2, treatment.getDescription());
            preparedStatement.setString(3, treatment.getResult());
            preparedStatement.setDate(4, new java.sql.Date(treatment.getGivenDateObjectProperty().getTime()));
            if(null == treatment.getChargeObjectProperty()){
                preparedStatement.setNull(5, Types.INTEGER);
            }else {
                preparedStatement.setInt(5, treatment.getChargeObjectProperty().getId());
            }

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTreatment(Treatment treatment) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("UPDATE treatment SET " +
                    "description = ?, " +
                    "result = ?, " +
                    "given_date = ?, " +
                    "charge_id = ? " +
                    "WHERE id = ?");
            preparedStatement.setString(1, treatment.getDescription());
            preparedStatement.setString(2, treatment.getResult());
            preparedStatement.setDate(3, new java.sql.Date(treatment.getGivenDateObjectProperty().getTime()));
            if(null == treatment.getChargeObjectProperty()){
                preparedStatement.setNull(4, Types.INTEGER);
            }else {
                preparedStatement.setInt(4, treatment.getChargeObjectProperty().getId());
            }
            preparedStatement.setInt(5, treatment.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTreatment(Treatment treatment) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("DELETE FROM treatment " +
                    "WHERE id = ?");
            preparedStatement.setInt(1, treatment.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
