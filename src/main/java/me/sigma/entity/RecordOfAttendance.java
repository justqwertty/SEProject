package me.sigma.entity;

import java.util.Date;

public class RecordOfAttendance {
    private Date date;
    private String status;
    private boolean isFlagged;
    private String studentID;
    private String timeSlot;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    public void markAsFlagged(String reason) {
        this.isFlagged = true;
    }

    public String getAttendanceInfo() {
        return null;
    }

    public void saveAttendance() {
    }
}
