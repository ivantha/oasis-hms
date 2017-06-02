package com.oasis.common;

import com.oasis.configuration.ConfigurationFile;
import com.oasis.database.connector.*;
import com.oasis.model.Employee;
import com.rits.cloning.Cloner;

public class Session {
    public static ConfigurationFile APP_CONFIG;

    public static PhysicianConnector physicianConnector;
    public static WardConnector wardConnector;
    public static TestConnector testConnector;
    public static SpecialityConnector specialityConnector;
    public static EthnicityConnector ethnicityConnector;
    public static EmployeeConnector employeeConnector;
    public static EmployeeRoleConnector employeeRoleConnector;
    public static DegreeConnector degreeConnector;
    public static BloodGroupConnector bloodGroupConnector;
    public static PatientConnector patientConnector;
    public static AdmissionConnector admissionConnector;
    public static ChargeConnector chargeConnector;
    public static PaymentConnector paymentConnector;
    public static TreatmentConnector treatmentConnector;
    public static UserConnector userConnector;

    public static Cloner cloner;

    public static Employee currentUser;
}
