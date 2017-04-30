package com.oasis.database.connector;

import com.mysql.jdbc.PreparedStatement;
import com.oasis.database.Connect;
import com.oasis.model.Race;
import com.oasis.model.SpecialistBranch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class SpecialistBranchConnector extends Connect {
    public HashMap<Integer, SpecialistBranch> getSpecialistBranchHashMap(){
        HashMap<Integer, SpecialistBranch> specialistBranchHashMap = new HashMap<>();

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM specialist_branch");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("specialist_branch.id");
                String name = resultSet.getString("specialist_branch.name");
                String description = resultSet.getString("specialist_branch.description");

                SpecialistBranch specialistBranch = new SpecialistBranch(id, name, description);
                specialistBranchHashMap.put(id, specialistBranch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return specialistBranchHashMap;
    }
}
