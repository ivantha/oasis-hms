package com.oasis.database.connector;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.oasis.database.Connect;
import com.oasis.model.*;
import com.oasis.services.BloodGroupServices;
import com.oasis.services.EthnicityServices;
import com.oasis.services.GenderServices;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

public class PatientConnector extends Connect{
    public HashMap<Integer, Patient> getPatientHashMap() {
        HashMap<Integer, Patient> patientHashMap = new HashMap<>();

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM patient");
            getPatientGeneralDetails(preparedStatement, patientHashMap);

            getPatientTelephoneDetails(patientHashMap);
            getPatientAddressDetails(patientHashMap);
            getPatientEmailDetails(patientHashMap);
            getPatientEmailDetails(patientHashMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patientHashMap;
    }

    private void getPatientGeneralDetails(PreparedStatement preparedStatement, HashMap<Integer, Patient> patientHashMap){
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("patient.id");
                String nic = resultSet.getString("patient.nic");
                String firstName = resultSet.getString("patient.first_name");
                String middleName = resultSet.getString("patient.middle_name");
                String lastName = resultSet.getString("patient.last_name");
                Gender gender = GenderServices.getGenderByTag(resultSet.getString("patient.gender"));
                LocalDate dob = resultSet.getDate("patient.dob").toLocalDate();
                BloodGroup bloodGroup = BloodGroupServices.getBloodGroupById(resultSet.getInt("patient.blood_group_id"));
                Ethnicity ethnicity = EthnicityServices.getEthnicityById(resultSet.getInt("patient.ethnicity_id"));
                Date addedDate = resultSet.getTimestamp("patient.added_date");
                String description = resultSet.getString("patient.description");

                Patient patient = new Patient(id, nic, firstName, middleName, lastName, gender, dob, bloodGroup, ethnicity, addedDate, description);
                patientHashMap.put(id, patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getPatientTelephoneDetails(HashMap<Integer, Patient> patientHashMap){
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM patient_telephone");
            ResultSet resultSet1 = preparedStatement.executeQuery();

            while (resultSet1.next()) {
                int patientId = resultSet1.getInt("patient_telephone.patient_id");
                int patientTelephoneId = resultSet1.getInt("patient_telephone.id");
                String patientTelephoneTelephone = resultSet1.getString("patient_telephone.telephone");
                Telephone telephone = new Telephone(patientTelephoneId, patientTelephoneTelephone);

                patientHashMap.get(patientId).getTelephoneArrayList().add(telephone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getPatientAddressDetails(HashMap<Integer, Patient> patientHashMap){
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM patient_address");
            ResultSet resultSet2 = preparedStatement.executeQuery();

            while (resultSet2.next()) {
                int patientId = resultSet2.getInt("patient_address.patient_id");
                int patientAddressId = resultSet2.getInt("patient_address.id");
                String patientAddressStreet = resultSet2.getString("patient_address.street");
                String patientAddressTown = resultSet2.getString("patient_address.town");
                String patientAddressProvince = resultSet2.getString("patient_address.province");
                String patientAddressPostalCode = resultSet2.getString("patient_address.postal_code");
                Address address = new Address(patientAddressId, patientAddressStreet, patientAddressTown, patientAddressProvince, patientAddressPostalCode);

                patientHashMap.get(patientId).getAddressArrayList().add(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getPatientEmailDetails(HashMap<Integer, Patient> patientHashMap){
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM patient_email");
            ResultSet resultSet3 = preparedStatement.executeQuery();

            while (resultSet3.next()) {
                int patientId = resultSet3.getInt("patient_email.patient_id");
                int patientEmailId = resultSet3.getInt("patient_email.id");
                String patientEmailEmail = resultSet3.getString("patient_email.email");
                Email email = new Email(patientEmailId, patientEmailEmail);

                patientHashMap.get(patientId).getEmailArrayList().add(email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getPatientEmergencyContactDetails(HashMap<Integer, Patient> patientHashMap){
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM emergency_contact");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int patientId = resultSet.getInt("patient_email.patient_id");
                int emergencyContactId = resultSet.getInt("patient_email.id");
                String emergencyContactName = resultSet.getString("patient_email.email");
                String emergencyContactRelationship = resultSet.getString("patient_email.email");
                String emergencyContactTelephone = resultSet.getString("patient_email.email");
                String emergencyContactAddress = resultSet.getString("patient_email.email");

                EmergencyContact emergencyContact = new EmergencyContact(emergencyContactId, emergencyContactName,
                        emergencyContactRelationship, emergencyContactTelephone, emergencyContactAddress);
                patientHashMap.get(patientId).getEmergencyContactArrayList().add(emergencyContact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void newPatient(Patient patient) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("INSERT INTO " +
                    "patient(nic, first_name, middle_name, last_name, gender, dob, " +
                    "blood_group_id, ethnicity_id, description) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, patient.getNic());
            preparedStatement.setString(2, patient.getFirstName());
            preparedStatement.setString(3, patient.getMiddleName());
            preparedStatement.setString(4, patient.getLastName());
            preparedStatement.setString(5, patient.getGender().getTag());
            preparedStatement.setDate(6, java.sql.Date.valueOf(patient.getDob()));
            preparedStatement.setInt(7, patient.getBloodGroupObjectProperty().getId());
            preparedStatement.setInt(8, patient.getEthnicityObjectProperty().getId());
            preparedStatement.setString(9, patient.getDescription());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                patient.setId(resultSet.getInt(1));
            }

            for (Telephone telephone : patient.getTelephoneArrayList()) {
                newPatientTelephone(patient.getId(), telephone);
            }

            for (Address address : patient.getAddressArrayList()) {
                newPatientAddress(patient.getId(), address);
            }

            for (Email email : patient.getEmailArrayList()) {
                newPatientEmail(patient.getId(), email);
            }

            for (EmergencyContact emergencyContact : patient.getEmergencyContactArrayList()) {
                newPatientEmergencyContact(patient.getId(), emergencyContact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePatient(Patient patient) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("UPDATE patient SET " +
                    "nic = ?, " +
                    "first_name = ?, " +
                    "middle_name = ?, " +
                    "last_name = ?, " +
                    "gender = ?, " +
                    "dob = ?, " +
                    "blood_group_id = ?, " +
                    "ethnicity_id = ?, " +
                    "description = ? " +
                    "WHERE id = ?");
            preparedStatement.setString(1, patient.getNic());
            preparedStatement.setString(2, patient.getFirstName());
            preparedStatement.setString(3, patient.getMiddleName());
            preparedStatement.setString(4, patient.getFirstName());
            preparedStatement.setString(5, patient.getGender().getTag());
            preparedStatement.setDate(6, java.sql.Date.valueOf(patient.getDob()));
            preparedStatement.setInt(7, patient.getBloodGroupObjectProperty().getId());
            preparedStatement.setInt(8, patient.getEthnicityObjectProperty().getId());
            preparedStatement.setString(9, patient.getDescription());
            preparedStatement.setInt(10, patient.getId());

            preparedStatement.execute();

            for (Telephone telephone : patient.getTelephoneArrayList()) {
                if (telephone.getId() == 0) {
                    newPatientTelephone(patient.getId(), telephone);
                } else {
                    updatePatientTelephone(telephone);
                }
            }

            for (Address address : patient.getAddressArrayList()) {
                if (address.getId() == 0) {
                    newPatientAddress(patient.getId(), address);
                } else {
                    updatePatientAddress(address);
                }
            }

            for (Email email : patient.getEmailArrayList()) {
                if (email.getId() == 0) {
                    newPatientEmail(patient.getId(), email);
                } else {
                    updatePatientEmail(email);
                }
            }

            for (EmergencyContact emergencyContact : patient.getEmergencyContactArrayList()) {
                if (emergencyContact.getId() == 0) {
                    newPatientEmergencyContact(patient.getId(), emergencyContact);
                } else {
                    updatePatientEmergencyContact(emergencyContact);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePatient(Patient patient) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("DELETE FROM patient " +
                    "WHERE id = ?");
            preparedStatement.setInt(1, patient.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void newPatientTelephone(int patientId, Telephone telephone) throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("INSERT INTO " +
                "patient_telephone(patient_id, telephone) " +
                "VALUES(?, ?)");
        preparedStatement.setInt(1, patientId);
        preparedStatement.setString(2, telephone.getTelephone());

        preparedStatement.execute();
    }

    private void updatePatientTelephone(Telephone telephone) throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("UPDATE patient_telephone SET " +
                "telephone = ? " +
                "WHERE id = ?");

        preparedStatement.setString(1, telephone.getTelephone());
        preparedStatement.setInt(2, telephone.getId());

        preparedStatement.execute();
    }

    //Delete patient telephone

    private void newPatientAddress(int patientId, Address address) throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("INSERT INTO " +
                "patient_address(patient_id, street, town, province, postal_code) " +
                "VALUES(?, ?, ?, ?, ?)");
        preparedStatement.setInt(1, patientId);
        preparedStatement.setString(2, address.getStreet());
        preparedStatement.setString(3, address.getTown());
        preparedStatement.setString(4, address.getProvince());
        preparedStatement.setString(5, address.getPostalCode());

        preparedStatement.execute();
    }

    private void updatePatientAddress(Address address) throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("UPDATE patient_address SET " +
                "street = ?, " +
                "town = ?, " +
                "province = ?, " +
                "postal_code = ? " +
                "WHERE id = ?");

        preparedStatement.setString(1, address.getStreet());
        preparedStatement.setString(2, address.getTown());
        preparedStatement.setString(3, address.getProvince());
        preparedStatement.setString(4, address.getPostalCode());
        preparedStatement.setInt(5, address.getId());

        preparedStatement.execute();
    }

    //Delete patient address

    private void newPatientEmail(int patientId, Email email) throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("INSERT INTO " +
                "patient_email(patient_id, email) " +
                "VALUES(?, ?)");
        preparedStatement.setInt(1, patientId);
        preparedStatement.setString(2, email.getEmail());

        preparedStatement.execute();
    }

    private void updatePatientEmail(Email email) throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("UPDATE patient_email SET " +
                "email = ? " +
                "WHERE id = ?");

        preparedStatement.setString(1, email.getEmail());
        preparedStatement.setInt(2, email.getId());

        preparedStatement.execute();
    }

    //Delete patient email

    private void newPatientEmergencyContact(int patientId, EmergencyContact emergencyContact) throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("INSERT INTO " +
                "emergency_contact(patient_id, name, relationship, telephone, address) " +
                "VALUES(?, ?, ?, ?)");
        preparedStatement.setInt(1, patientId);
        preparedStatement.setString(2, emergencyContact.getName());
        preparedStatement.setString(3, emergencyContact.getRelationship());
        preparedStatement.setString(4, emergencyContact.getTelephone());
        preparedStatement.setString(5, emergencyContact.getAddress());

        preparedStatement.execute();
    }

    private void updatePatientEmergencyContact(EmergencyContact emergencyContact) throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("UPDATE emergency_contact SET " +
                "name = ?, " +
                "relationship = ?, " +
                "telephone = ?, " +
                "address = ? " +
                "WHERE id = ?");

        preparedStatement.setString(1, emergencyContact.getName());
        preparedStatement.setString(2, emergencyContact.getRelationship());
        preparedStatement.setString(3, emergencyContact.getTelephone());
        preparedStatement.setString(4, emergencyContact.getAddress());
        preparedStatement.setInt(5, emergencyContact.getId());

        preparedStatement.execute();
    }

    //Delete patient emergency contact

    public HashMap<Integer, Patient> getPatientLike(String param){
        HashMap<Integer, Patient> patientHashMap = new HashMap<>();
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM patient WHERE " +
                    "CONCAT_WS(' ', first_name, middle_name, last_name) LIKE '%" + param + "%'");
            getPatientGeneralDetails(preparedStatement, patientHashMap);

//            getPatientTelephoneDetails(patientHashMap);
//            getPatientAddressDetails(patientHashMap);
//            getPatientEmailDetails(patientHashMap);
//            getPatientEmergencyContactDetails(patientHashMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patientHashMap;
    }
}
