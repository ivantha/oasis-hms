package com.oasis.database.connector;

import com.mysql.jdbc.PreparedStatement;
import com.oasis.database.Connect;
import com.oasis.model.Speciality;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class SpecialityConnector extends Connect {
    public HashMap<Integer, Speciality> getSpecialistBranchHashMap(){
        HashMap<Integer, Speciality> specialistBranchHashMap = new HashMap<>();

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM speciality");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("specialist_branch.id");
                String name = resultSet.getString("specialist_branch.name");
                String description = resultSet.getString("specialist_branch.description");

                Speciality speciality = new Speciality(id, name, description);
                specialistBranchHashMap.put(id, speciality);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return specialistBranchHashMap;
    }

    public void newSpeciality(Speciality speciality) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("INSERT INTO " +
                    "speciality(name, description) " +
                    "VALUES(?, ?)");
            preparedStatement.setString(1, speciality.getName());
            preparedStatement.setString(2, speciality.getDescription());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
