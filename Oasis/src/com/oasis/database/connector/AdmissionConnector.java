package com.oasis.database.connector;

import com.mysql.jdbc.PreparedStatement;
import com.oasis.database.Connector;
import com.oasis.model.Admission;
import com.oasis.model.Doctor;
import com.oasis.model.Patient;
import com.oasis.model.Physician;
import com.oasis.services.EmployeeServices;
import com.oasis.services.PhysicianServices;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

public class AdmissionConnector extends Connector {
    public HashMap<Integer, Admission> getAdmissionHashMapByPatient(Patient patient) {
        HashMap<Integer, Admission> admissionHashMap = new HashMap<>();

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM admission WHERE " +
                    "patient_id = ?");
            preparedStatement.setInt(1, patient.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("admission.id");
                Physician physician = PhysicianServices.getPhysicianById(resultSet.getInt("admission.physician_id"));
                Doctor admissionConsultant = new Doctor(EmployeeServices.getEmployeeById(
                        resultSet.getInt("admission.admission_consultant_id")));
                Doctor leadingConsultant = new Doctor(EmployeeServices.getEmployeeById(
                        resultSet.getInt("admission.leading_consultant_id")));
                String cause = resultSet.getString("admission.cause");
                Date admissionDate = resultSet.getDate("admission.admission_date");

                Date releaseDate = resultSet.getDate("admission.release_date");

                Admission admission = new Admission(id, patient, physician, admissionConsultant, leadingConsultant, cause, admissionDate, releaseDate);
                admissionHashMap.put(id, admission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admissionHashMap;
    }

    public void newAdmission(Admission admission) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("INSERT INTO " +
                    "admission(patient_id, physician_id, admission_consultant_id, leading_consultant_id, cause, admission_date, release_date) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, admission.getPatientObjectProperty().getId());
            preparedStatement.setInt(2, admission.getPhysicianObjectProperty().getId());
            preparedStatement.setInt(3, admission.getAdmissionConsultantObjectProperty().getId());
            preparedStatement.setInt(4, admission.getLeadingConsultantObjectProperty().getId());
            preparedStatement.setString(5, admission.getCause());
            preparedStatement.setDate(6, new Date(admission.getAdmissionDateObjectProperty().getTime()));
            if (admission.getReleaseDateObjectProperty() == null) {
                preparedStatement.setNull(7, Types.DATE);
            } else {
                preparedStatement.setDate(7, new Date(admission.getReleaseDateObjectProperty().getTime()));
            }

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAdmission(Admission admission) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("UPDATE admission SET " +
                    "patient_id = ?, " +
                    "physician_id = ?, " +
                    "admission_consultant_id = ?, " +
                    "leading_consultant_id = ?, " +
                    "cause = ?, " +
                    "admission_date = ?, " +
                    "release_date = ? " +
                    "WHERE id = ?");
            preparedStatement.setInt(1, admission.getPatientObjectProperty().getId());
            preparedStatement.setInt(2, admission.getPhysicianObjectProperty().getId());
            preparedStatement.setInt(3, admission.getAdmissionConsultantObjectProperty().getId());
            preparedStatement.setInt(4, admission.getLeadingConsultantObjectProperty().getId());
            preparedStatement.setString(5, admission.getCause());
            preparedStatement.setDate(6, new Date(admission.getAdmissionDateObjectProperty().getTime()));
            if (null == admission.getReleaseDateObjectProperty()) {
                preparedStatement.setNull(7, Types.DATE);
            } else {
                preparedStatement.setDate(7, new Date(admission.getReleaseDateObjectProperty().getTime()));
            }
            preparedStatement.setInt(8, admission.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAdmission(Admission admission) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("DELETE FROM admission " +
                    "WHERE id = ?");
            preparedStatement.setInt(1, admission.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
