package com.oasis.database.connector;

import com.mysql.jdbc.PreparedStatement;
import com.oasis.database.Connector;
import com.oasis.model.Degree;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DegreeConnector extends Connector {
    public HashMap<Integer, Degree> getDegreeHashMap() {
        HashMap<Integer, Degree> degreeHashMap = new HashMap<>();

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM degree");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("degree.id");
                String name = resultSet.getString("degree.name");
                String acronym = resultSet.getString("degree.acronym");

                Degree degree = new Degree(id, name, acronym);
                degreeHashMap.put(id, degree);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return degreeHashMap;
    }
}
