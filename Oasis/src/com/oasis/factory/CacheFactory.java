package com.oasis.factory;

import com.oasis.cache.Cache;
import com.oasis.cache.CacheType;
import com.oasis.common.Session;
import com.oasis.model.*;

import java.util.HashMap;

import static com.oasis.cache.CacheType.*;

public class CacheFactory {
    private static final HashMap<CacheType, Cache> CACHE_HASH_MAP = new HashMap<>();

    public static void initializeAllCaches() {
        getBloodGroupCache();
        getDegreeCache();
        getEmployeeCache();
        getEmployeeRoleCache();
        getEthnicityCache();
        getGenderCache();
        getPhysicianDesignationCache();
        getSpecialityCache();
        getTestCache();
        getWardCache();
    }

    public static Cache<BloodGroup> getBloodGroupCache() {
        if (!CacheFactory.CACHE_HASH_MAP.containsKey(BLOOD_GROUP_CACHE)) {
            Cache<BloodGroup> bloodGroupCache = new Cache<BloodGroup>() {
                @Override
                public void itemAdder(HashMap<Integer, BloodGroup> itemHashMap) {
                    itemHashMap.putAll(Session.bloodGroupConnector.getBloodGroupHashMap());
                }
            };

            CACHE_HASH_MAP.put(BLOOD_GROUP_CACHE, bloodGroupCache);
        }

        return CACHE_HASH_MAP.get(BLOOD_GROUP_CACHE);
    }

    public static Cache<Degree> getDegreeCache() {
        if (!CacheFactory.CACHE_HASH_MAP.containsKey(DEGREE_CACHE)) {
            Cache<Degree> degreeCache = new Cache<Degree>() {
                @Override
                public void itemAdder(HashMap<Integer, Degree> itemHashMap) {
                    itemHashMap.putAll(Session.degreeConnector.getDegreeHashMap());
                }
            };

            CACHE_HASH_MAP.put(DEGREE_CACHE, degreeCache);
        }

        return CACHE_HASH_MAP.get(DEGREE_CACHE);
    }

    public static Cache<Employee> getEmployeeCache() {
        if (!CacheFactory.CACHE_HASH_MAP.containsKey(EMPLOYEE_CACHE)) {
            Cache<Employee> employeeCache = new Cache<Employee>() {
                @Override
                public void itemAdder(HashMap<Integer, Employee> itemHashMap) {
                    itemHashMap.putAll(Session.employeeConnector.getEmployeeHashMap());
                }
            };

            CACHE_HASH_MAP.put(EMPLOYEE_CACHE, employeeCache);
        }

        return CACHE_HASH_MAP.get(EMPLOYEE_CACHE);
    }

    public static Cache<EmployeeRole> getEmployeeRoleCache() {
        if (!CacheFactory.CACHE_HASH_MAP.containsKey(EMPLOYEE_ROLE_CACHE)) {
            Cache<EmployeeRole> employeeRoleCache = new Cache<EmployeeRole>() {
                @Override
                public void itemAdder(HashMap<Integer, EmployeeRole> itemHashMap) {
                    itemHashMap.putAll(Session.employeeRoleConnector.getEmployeeRoleHashMap());
                }
            };

            CACHE_HASH_MAP.put(EMPLOYEE_ROLE_CACHE, employeeRoleCache);
        }

        return CACHE_HASH_MAP.get(EMPLOYEE_ROLE_CACHE);
    }

    public static Cache<Ethnicity> getEthnicityCache() {
        if (!CacheFactory.CACHE_HASH_MAP.containsKey(ETHNICITY_CACHE)) {
            Cache<Ethnicity> ethnicityCache = new Cache<Ethnicity>() {
                @Override
                public void itemAdder(HashMap<Integer, Ethnicity> itemHashMap) {
                    itemHashMap.putAll(Session.ethnicityConnector.getEthnicityHashMap());
                }
            };

            CACHE_HASH_MAP.put(ETHNICITY_CACHE, ethnicityCache);
        }

        return CACHE_HASH_MAP.get(ETHNICITY_CACHE);
    }

    public static Cache<Gender> getGenderCache() {
        if (!CacheFactory.CACHE_HASH_MAP.containsKey(GENDER_CACHE)) {
            Cache<Gender> genderCache = new Cache<Gender>() {
                @Override
                public void itemAdder(HashMap<Integer, Gender> itemHashMap) {
                }
            };
            genderCache.getItemHashMap().put(1, new Gender(1, "m"));
            genderCache.getItemHashMap().put(2, new Gender(2, "f"));

            CACHE_HASH_MAP.put(GENDER_CACHE, genderCache);
        }

        return CACHE_HASH_MAP.get(GENDER_CACHE);
    }

    public static Cache<PhysicianDesignation> getPhysicianDesignationCache() {
        if (!CacheFactory.CACHE_HASH_MAP.containsKey(PHYSICIAN_DESIGNATION_CACHE)) {
            Cache<PhysicianDesignation> physicianDesignationCache = new Cache<PhysicianDesignation>() {
                @Override
                public void itemAdder(HashMap<Integer, PhysicianDesignation> itemHashMap) {
                    itemHashMap.putAll(Session.physicianConnector.getPhysicianDesignationhashMap());
                }
            };

            CACHE_HASH_MAP.put(PHYSICIAN_DESIGNATION_CACHE, physicianDesignationCache);
        }

        return CACHE_HASH_MAP.get(PHYSICIAN_DESIGNATION_CACHE);
    }

    public static Cache<Speciality> getSpecialityCache() {
        if (!CacheFactory.CACHE_HASH_MAP.containsKey(SPECIALITY_CACHE)) {
            Cache<Speciality> specialityCache = new Cache<Speciality>() {
                @Override
                public void itemAdder(HashMap<Integer, Speciality> itemHashMap) {
                    itemHashMap.putAll(Session.specialityConnector.getSpecialityHashMap());
                }
            };

            CACHE_HASH_MAP.put(SPECIALITY_CACHE, specialityCache);
        }

        return CACHE_HASH_MAP.get(SPECIALITY_CACHE);
    }

    public static Cache<Test> getTestCache() {
        if (!CacheFactory.CACHE_HASH_MAP.containsKey(TEST_CACHE)) {
            Cache<Test> testCache = new Cache<Test>() {
                @Override
                public void itemAdder(HashMap<Integer, Test> itemHashMap) {
                    itemHashMap.putAll(Session.testConnector.getTestHashMap());
                }
            };

            CACHE_HASH_MAP.put(TEST_CACHE, testCache);
        }

        return CACHE_HASH_MAP.get(TEST_CACHE);
    }

    public static Cache<Ward> getWardCache() {
        if (!CacheFactory.CACHE_HASH_MAP.containsKey(WARD_CACHE)) {
            Cache<Ward> wardCache = new Cache<Ward>() {
                @Override
                public void itemAdder(HashMap<Integer, Ward> itemHashMap) {
                    itemHashMap.putAll(Session.wardConnector.getWardHashMap());
                }
            };

            CACHE_HASH_MAP.put(WARD_CACHE, wardCache);
        }

        return CACHE_HASH_MAP.get(WARD_CACHE);
    }
}
