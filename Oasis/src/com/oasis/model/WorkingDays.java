package com.oasis.model;

import com.oasis.common.Session;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class WorkingDays implements Model<WorkingDays> {
    private BooleanProperty monday = new SimpleBooleanProperty(false);
    private BooleanProperty tuesdays = new SimpleBooleanProperty(false);
    private BooleanProperty wednesday = new SimpleBooleanProperty(false);
    private BooleanProperty thursday = new SimpleBooleanProperty(false);
    private BooleanProperty friday = new SimpleBooleanProperty(false);
    private BooleanProperty saturday = new SimpleBooleanProperty(false);
    private BooleanProperty sunday = new SimpleBooleanProperty(false);

    public WorkingDays() {
    }

    public WorkingDays(boolean monday, boolean tuesdays, boolean wednesday,
                       boolean thursday, boolean friday, boolean saturday, boolean sunday) {
        this.monday.setValue(monday);
        this.tuesdays.setValue(tuesdays);
        this.wednesday.setValue(wednesday);
        this.thursday.setValue(thursday);
        this.friday.setValue(friday);
        this.saturday.setValue(saturday);
        this.sunday.setValue(sunday);
    }

    public static WorkingDays valueOf(String days) {
        WorkingDays workingDays = new WorkingDays();

        if (days.length() == 7) {
            if (days.charAt(0) == '1') {
                workingDays.setMonday(true);
            }
            if (days.charAt(1) == '1') {
                workingDays.setTuesdays(true);
            }
            if (days.charAt(2) == '1') {
                workingDays.setWednesday(true);
            }
            if (days.charAt(3) == '1') {
                workingDays.setThursday(true);
            }
            if (days.charAt(4) == '1') {
                workingDays.setFriday(true);
            }
            if (days.charAt(5) == '1') {
                workingDays.setSaturday(true);
            }
            if (days.charAt(6) == '1') {
                workingDays.setSunday(true);
            }
        }

        return workingDays;
    }

    @Override
    public String toString() {
        String workingDaysChain = "";
        if (isMonday()) {
            workingDaysChain += "1";
        } else {
            workingDaysChain += "0";
        }
        if (isTuesdays()) {
            workingDaysChain += "1";
        } else {
            workingDaysChain += "0";
        }
        if (isWednesday()) {
            workingDaysChain += "1";
        } else {
            workingDaysChain += "0";
        }
        if (isThursday()) {
            workingDaysChain += "1";
        } else {
            workingDaysChain += "0";
        }
        if (isFriday()) {
            workingDaysChain += "1";
        } else {
            workingDaysChain += "0";
        }
        if (isSaturday()) {
            workingDaysChain += "1";
        } else {
            workingDaysChain += "0";
        }
        if (isSunday()) {
            workingDaysChain += "1";
        } else {
            workingDaysChain += "0";
        }

        return workingDaysChain;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!WorkingDays.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        WorkingDays wd = (WorkingDays) obj;
        if (wd.isMonday() != isMonday()) {
            return false;
        }
        if (wd.isTuesdays() != isTuesdays()) {
            return false;
        }
        if (wd.isWednesday() != isWednesday()) {
            return false;
        }
        if (wd.isThursday() != isThursday()) {
            return false;
        }
        if (wd.isFriday() != isFriday()) {
            return false;
        }
        if (wd.isSaturday() != isSaturday()) {
            return false;
        }
        if (wd.isSunday() != isSunday()) {
            return false;
        }

        return true;
    }

    @Override
    public WorkingDays clone() {
        return Session.cloner.deepClone(this);
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean isMonday() {
        return monday.get();
    }

    public void setMonday(boolean monday) {
        this.monday.set(monday);
    }

    public BooleanProperty mondayProperty() {
        return monday;
    }

    public boolean isTuesdays() {
        return tuesdays.get();
    }

    public void setTuesdays(boolean tuesdays) {
        this.tuesdays.set(tuesdays);
    }

    public BooleanProperty tuesdaysProperty() {
        return tuesdays;
    }

    public boolean isWednesday() {
        return wednesday.get();
    }

    public void setWednesday(boolean wednesday) {
        this.wednesday.set(wednesday);
    }

    public BooleanProperty wednesdayProperty() {
        return wednesday;
    }

    public boolean isThursday() {
        return thursday.get();
    }

    public void setThursday(boolean thursday) {
        this.thursday.set(thursday);
    }

    public BooleanProperty thursdayProperty() {
        return thursday;
    }

    public boolean isFriday() {
        return friday.get();
    }

    public void setFriday(boolean friday) {
        this.friday.set(friday);
    }

    public BooleanProperty fridayProperty() {
        return friday;
    }

    public boolean isSaturday() {
        return saturday.get();
    }

    public void setSaturday(boolean saturday) {
        this.saturday.set(saturday);
    }

    public BooleanProperty saturdayProperty() {
        return saturday;
    }

    public boolean isSunday() {
        return sunday.get();
    }

    public void setSunday(boolean sunday) {
        this.sunday.set(sunday);
    }

    public BooleanProperty sundayProperty() {
        return sunday;
    }
}
