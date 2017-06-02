package com.oasis.database.connector;

import com.mysql.jdbc.PreparedStatement;
import com.oasis.database.Connector;
import com.oasis.model.BloodGroup;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class BloodGroupConnector extends Connector {
    public HashMap<Integer, BloodGroup> getBloodGroupHashMap() {
        HashMap<Integer, BloodGroup> bloodGroupHashMap = new HashMap<>();

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM blood_group");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("blood_group.id");
                String name = resultSet.getString("blood_group.name");

                BloodGroup bloodGroup = new BloodGroup(id, name);
                bloodGroupHashMap.put(id, bloodGroup);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bloodGroupHashMap;
    }
}
