package com.oasis.database.connector;

import com.mysql.jdbc.PreparedStatement;
import com.oasis.database.Connect;
import com.oasis.model.Speciality;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class SpecialityConnector extends Connect {
    public HashMap<Integer, Speciality> getSpecialityHashMap(){
        HashMap<Integer, Speciality> specialityHashMap = new HashMap<>();

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM speciality");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("speciality.id");
                String name = resultSet.getString("speciality.name");
                String description = resultSet.getString("speciality.description");

                Speciality speciality = new Speciality(id, name, description);
                specialityHashMap.put(id, speciality);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return specialityHashMap;
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

    public void updateSpeciality(Speciality speciality) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("UPDATE speciality SET " +
                    "name = ?, " +
                    "description = ? " +
                    "WHERE id = ?");
            preparedStatement.setString(1, speciality.getName());
            preparedStatement.setString(2, speciality.getDescription());
            preparedStatement.setInt(3, speciality.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSpeciality(Speciality speciality) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("DELETE FROM speciality " +
                    "WHERE id = ?");
            preparedStatement.setInt(1, speciality.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
