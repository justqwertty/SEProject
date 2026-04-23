package me.seproject.mavenproject1.entity;

public class Staff extends User {
    private String staffID;
    private String position;

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void maintainProfile(User profile) {
    }

    public void recordAttendance(Student student, java.util.Date date) {
    }
}
