package com.oasis.model;

import org.jetbrains.annotations.Contract;

public enum ChargeType {
    ADMISSION_CHARGE("Admission charge"),
    TREATMENT_CHARGE("Treatment charge"),
    TEST_CHARGE("Test charge");

    private final String name;

    ChargeType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Contract(pure = true)
    public String getName() {
        return name;
    }
}
