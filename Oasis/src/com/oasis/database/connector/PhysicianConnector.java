package com.oasis.database.connector;

import com.mysql.jdbc.PreparedStatement;
import com.oasis.database.Connect;
import com.oasis.model.Physician;
import com.oasis.model.PhysicianDesignation;
import com.oasis.model.PhysicianTelephone;
import com.oasis.services.PhysicianDesignationServices;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class PhysicianConnector extends Connect {
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
                PhysicianDesignation physicianDesignation =
                        PhysicianDesignationServices.getPhysicianDesignationById(
                                resultSet.getInt("physician.physician_designation_id"));
                int physicianTelephoneID = resultSet.getInt("physician_telephone.id");
                String physicianTelephoneTelephone= resultSet.getString("physician_telephone.telephone");

                if (!physicianHashMap.containsKey(id)){
                    Physician physician = new Physician(id, firstName, middleName, lastName, physicianDesignation);
                    physicianHashMap.put(id, physician);
                }

                PhysicianTelephone physicianTelephone = new PhysicianTelephone(physicianTelephoneID, physicianTelephoneTelephone);
                physicianHashMap.get(id).getPhysicianTelephoneArrayList().add(physicianTelephone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return physicianHashMap;
    }
}
