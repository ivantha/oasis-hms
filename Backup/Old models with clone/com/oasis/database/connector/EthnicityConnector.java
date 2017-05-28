package com.oasis.database.connector;

import com.mysql.jdbc.PreparedStatement;
import com.oasis.database.Connect;
import com.oasis.model.Ethnicity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class EthnicityConnector extends Connect {
    public HashMap<Integer, Ethnicity> getEthnicityHashMap(){
        HashMap<Integer, Ethnicity> ethnicityHashMap = new HashMap<>();

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM ethnicity");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("ethnicity.id");
                String name = resultSet.getString("ethnicity.name");

                Ethnicity ethnicity = new Ethnicity(id, name);
                ethnicityHashMap.put(id, ethnicity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ethnicityHashMap;
    }

    public void newEthnicity(Ethnicity ethnicity) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("INSERT INTO " +
                    "ethnicity(name) " +
                    "VALUES(?)");
            preparedStatement.setString(1, ethnicity.getName());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEthnicity(Ethnicity ethnicity) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("UPDATE ethnicity SET " +
                    "name = ? " +
                    "WHERE id = ?");
            preparedStatement.setString(1, ethnicity.getName());
            preparedStatement.setInt(2, ethnicity.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEthnicity(Ethnicity ethnicity) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("DELETE FROM ethnicity " +
                    "WHERE id = ?");
            preparedStatement.setInt(1, ethnicity.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
