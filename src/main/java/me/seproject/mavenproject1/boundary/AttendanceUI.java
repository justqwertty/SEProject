package me.seproject.mavenproject1.boundary;

import me.seproject.mavenproject1.entity.RecordOfAttendance;
import java.util.Map;
import java.util.List;

public class AttendanceUI {
    private String selectedModuleID;
    private Map<String, String> attendanceData;
    private String statusMessage;

    public String getSelectedModuleID() {
        return selectedModuleID;
    }

    public void setSelectedModuleID(String selectedModuleID) {
        this.selectedModuleID = selectedModuleID;
    }

    public Map<String, String> getAttendanceData() {
        return attendanceData;
    }

    public void setAttendanceData(Map<String, String> attendanceData) {
        this.attendanceData = attendanceData;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public void accessAttendanceInterface() {
    }

    public void selectModule(String moduleID) {
    }

    public void submitAttendanceDetails(RecordOfAttendance details) {
    }

    public void alertUser(String errorMessage) {
    }

    public void displaySuccessConfirmation() {
    }
}
