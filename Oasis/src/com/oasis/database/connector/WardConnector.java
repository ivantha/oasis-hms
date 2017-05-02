package com.oasis.database.connector;

import com.mysql.jdbc.PreparedStatement;
import com.oasis.database.Connect;
import com.oasis.model.Gender;
import com.oasis.model.Ward;
import com.oasis.services.GenderServices;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class WardConnector extends Connect {
    public HashMap<Integer, Ward> getWardHashMap() {
        HashMap<Integer, Ward> wardHashMap = new HashMap<>();

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM ward");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ward.id");
                String name = resultSet.getString("ward.name");
                String description = resultSet.getString("ward.description");
                int maxPatientCount = resultSet.getInt("ward.max_patient_count");
                int currentPatientCount = resultSet.getInt("ward.current_patient_count");
                String genderAcceptance = resultSet.getString("ward.gender_acceptance");
                Gender gender = GenderServices.getGenderByTag(genderAcceptance);
                int supervisorId = resultSet.getInt("ward.supervisor_id");

                Ward ward = new Ward(id, name, description, maxPatientCount, currentPatientCount, gender, supervisorId);
                wardHashMap.put(id, ward);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wardHashMap;
    }

    public void newWard(Ward ward) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("INSERT INTO " +
                    "ward(name, description, max_patient_count, gender_acceptance, supervisor_id) " +
                    "VALUES(?, ?, ?, ?, ?)");
            preparedStatement.setString(1, ward.getName());
            preparedStatement.setString(2, ward.getDescription());
            preparedStatement.setInt(3, ward.getMaxPatientCount());
            preparedStatement.setString(4, ward.getGenderAcceptance().getTag());
            preparedStatement.setInt(5, ward.getSupervisorId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
