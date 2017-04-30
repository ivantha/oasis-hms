package com.oasis.database.connector;

import com.mysql.jdbc.PreparedStatement;
import com.oasis.database.Connect;
import com.oasis.model.BloodGroup;
import com.oasis.model.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class TestConnector extends Connect {
    public HashMap<Integer, Test> getTestHashMap(){
        HashMap<Integer, Test> testHashMap = new HashMap<>();

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM test");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("test.id");
                String name = resultSet.getString("test.name");
                String description = resultSet.getString("test.description");
                double basePrice = resultSet.getDouble("test.base_price");

                Test test = new Test(id, name, description, basePrice);
                testHashMap.put(id, test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return testHashMap;
    }
}
