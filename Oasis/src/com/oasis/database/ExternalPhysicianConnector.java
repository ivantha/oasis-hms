package com.oasis.database;

import com.mysql.jdbc.PreparedStatement;
import com.oasis.model.Physician;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ExternalPhysicianConnector extends Connect {
    public HashMap<Integer, Physician> getPhysicianHashMap() {
        HashMap<Integer, Physician> physicianHashMap = new HashMap<>();

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM physician " +
                    "LEFT JOIN physician_telephone ON physician.id = physician_telephone.physician_id");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("physician.id");
                String firstName = resultSet.getString("physician.first_name");
                String middleName = resultSet.getString("physician.middle_name");
                String lastName = resultSet.getString("physician.last_name");
                int physicianDesignationID = resultSet.getInt("physician.physician_designation_id");
                String physicianTelephoneID = resultSet.getString("physician_telephone.telephone");

                System.out.println(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return physicianHashMap;
    }
}
