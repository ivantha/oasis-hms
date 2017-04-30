package com.oasis.database.connector;

import com.mysql.jdbc.PreparedStatement;
import com.oasis.database.Connect;
import com.oasis.model.BloodGroup;
import com.oasis.model.PhysicianTelephone;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class PhysicianTelephoneConnector extends Connect {
    public HashMap<Integer, PhysicianTelephone> getPhysicianTelephoneHashMap(){
        HashMap<Integer, PhysicianTelephone> physicianTelephoneHashMap = new HashMap<>();

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM physician_telephone");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("physician_telephone.id");
                String telephone = resultSet.getString("physician_telephone.telephone");

                PhysicianTelephone physicianTelephone = new PhysicianTelephone(id, telephone);
                physicianTelephoneHashMap.put(id, physicianTelephone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return physicianTelephoneHashMap;
    }
}
