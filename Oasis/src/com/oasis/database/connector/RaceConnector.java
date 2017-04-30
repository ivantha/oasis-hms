package com.oasis.database.connector;

import com.mysql.jdbc.PreparedStatement;
import com.oasis.database.Connect;
import com.oasis.model.Ethnicity;
import com.oasis.model.Race;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class RaceConnector extends Connect {
    public HashMap<Integer, Race> getRaceHashMap(){
        HashMap<Integer, Race> raceHashMap = new HashMap<>();

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM race");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("race.id");
                String name = resultSet.getString("race.name");

                Race race = new Race(id, name);
                raceHashMap.put(id, race);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return raceHashMap;
    }
}
