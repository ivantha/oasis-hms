package com.oasis.common;

import com.oasis.configuration.ConfigurationFile;
import com.oasis.database.connector.*;
import com.oasis.model.*;
import com.oasis.utils.Cache;

public class Session {
    public static ConfigurationFile APP_CONFIG;

    public static PhysicianConnector physicianConnector;
    public static WardConnector wardConnector;
    public static TestConnector testConnector;
    public static SpecialistBranchConnector specialistBranchConnector;
    public static RaceConnector raceConnector;
    public static PhysicianTelephoneConnector physicianTelephoneConnector;
    public static PhysicianDesignationConnector physicianDesignationConnector;
    public static EthnicityConnector ethnicityConnector;
    public static EmployeeConnector employeeConnector;
    public static EmployeeRoleConnector employeeRoleConnector;
    public static DegreeConnector degreeConnector;
    public static BloodGroupConnector bloodGroupConnector;

    public static Cache<BloodGroup> bloodGroupCache;
    public static Cache<Degree> degreeCache;
    public static Cache<Employee> employeeCache;
    public static Cache<EmployeeRole> employeeRoleCache;
    public static Cache<Ethnicity> ethnicityCache;
    public static Cache<PhysicianDesignation> physicianDesignationCache;
    public static Cache<Race> raceCache;
    public static Cache<SpecialistBranch> specialistBranchCache;
    public static Cache<Test> testCache;
    public static Cache<Ward> wardCache;
}
