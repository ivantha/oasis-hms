package com.oasis.database.connector;

import com.mysql.jdbc.PreparedStatement;
import com.oasis.database.Connect;
import com.oasis.model.Admission;
import com.oasis.model.Treatment;

import java.sql.ResultSet;
import java.sql.SQLException;
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
}
