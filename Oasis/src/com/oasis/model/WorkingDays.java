package com.oasis.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class WorkingDays implements Model<WorkingDays>{
    private BooleanProperty monday = new SimpleBooleanProperty(false);
    private BooleanProperty tuesdays = new SimpleBooleanProperty(false);
    private BooleanProperty wednesday = new SimpleBooleanProperty(false);
    private BooleanProperty thursday = new SimpleBooleanProperty(false);
    private BooleanProperty friday = new SimpleBooleanProperty(false);
    private BooleanProperty saturday = new SimpleBooleanProperty(false);
    private BooleanProperty sunday = new SimpleBooleanProperty(false);

    public WorkingDays() {
    }

    public WorkingDays(String days) {
        if (days.length() == 7){
            if(days.charAt(0) == '1'){
                setMonday(true);
            }
            if(days.charAt(1) == '1'){
                setTuesdays(true);
            }
            if(days.charAt(2) == '1'){
                setWednesday(true);
            }
            if(days.charAt(3) == '1'){
                setThursday(true);
            }
            if(days.charAt(4) == '1'){
                setFriday(true);
            }
            if(days.charAt(5) == '1'){
                setSaturday(true);
            }
            if(days.charAt(6) == '1'){
                setSunday(true);
            }
        }
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!WorkingDays.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        WorkingDays wd = (WorkingDays) obj;
        if(wd.isMonday() != isMonday()){
            return false;
        }
        if(wd.isTuesdays() != isTuesdays()){
            return false;
        }
        if(wd.isWednesday() != isWednesday()){
            return false;
        }
        if(wd.isThursday() != isThursday()){
            return false;
        }
        if(wd.isFriday() != isFriday()){
            return false;
        }
        if(wd.isSaturday() != isSaturday()){
            return false;
        }
        if(wd.isSunday() != isSunday()){
            return false;
        }

        return true;
    }

    @Override
    public WorkingDays clone() {
        return new WorkingDays(isMonday(), isTuesdays(), isWednesday(), isThursday(), isFriday(), isSaturday(), isSunday());
    }

    public boolean isMonday() {
        return monday.get();
    }

    public BooleanProperty mondayProperty() {
        return monday;
    }

    public void setMonday(boolean monday) {
        this.monday.set(monday);
    }

    public boolean isTuesdays() {
        return tuesdays.get();
    }

    public BooleanProperty tuesdaysProperty() {
        return tuesdays;
    }

    public void setTuesdays(boolean tuesdays) {
        this.tuesdays.set(tuesdays);
    }

    public boolean isWednesday() {
        return wednesday.get();
    }

    public BooleanProperty wednesdayProperty() {
        return wednesday;
    }

    public void setWednesday(boolean wednesday) {
        this.wednesday.set(wednesday);
    }

    public boolean isThursday() {
        return thursday.get();
    }

    public BooleanProperty thursdayProperty() {
        return thursday;
    }

    public void setThursday(boolean thursday) {
        this.thursday.set(thursday);
    }

    public boolean isFriday() {
        return friday.get();
    }

    public BooleanProperty fridayProperty() {
        return friday;
    }

    public void setFriday(boolean friday) {
        this.friday.set(friday);
    }

    public boolean isSaturday() {
        return saturday.get();
    }

    public BooleanProperty saturdayProperty() {
        return saturday;
    }

    public void setSaturday(boolean saturday) {
        this.saturday.set(saturday);
    }

    public boolean isSunday() {
        return sunday.get();
    }

    public BooleanProperty sundayProperty() {
        return sunday;
    }

    public void setSunday(boolean sunday) {
        this.sunday.set(sunday);
    }
}
