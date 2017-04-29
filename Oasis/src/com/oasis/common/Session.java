package com.oasis.common;

import com.oasis.configuration.ConfigurationFile;
import com.oasis.model.*;
import com.oasis.utils.Cache;

public class Session {
    public static ConfigurationFile APP_CONFIG;

    public static Cache<BloodGroup> bloodGroupCache;
    public static Cache<Degree> degreeCache;
    public static Cache<Employee> employeeCache;
    public static Cache<EmployeeRole> employeeRoleCache;
    public static Cache<Ethnicity> ethnicityCache;
    public static Cache<SpecialistBranch> specialistBranchCache;
    public static Cache<Test> testCache;
    public static Cache<Ward> wardCache;
}
