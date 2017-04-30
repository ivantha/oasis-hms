package com.oasis.database.connector;

import com.mysql.jdbc.PreparedStatement;
import com.oasis.database.Connect;
import com.oasis.model.BloodGroup;
import com.oasis.model.PhysicianDesignation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class PhysicianDesignationConnector extends Connect {
    public HashMap<Integer, PhysicianDesignation> getPhysicianDesignationhashMap(){
        HashMap<Integer, PhysicianDesignation> physicianDesignationHashMap = new HashMap<>();

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM physician_designation");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("physician_designation.id");
                String name = resultSet.getString("physician_designation.name");

                PhysicianDesignation physicianDesignation = new PhysicianDesignation(id, name);
                physicianDesignationHashMap.put(id, physicianDesignation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return physicianDesignationHashMap;
    }
}
