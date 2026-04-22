package me.sigma.controller;

import me.sigma.entity.Module;
import java.util.List;

public class EnrollmentController {
    private String currentSessionID;
    private boolean validationStatus;

    public String getCurrentSessionID() {
        return currentSessionID;
    }

    public void setCurrentSessionID(String currentSessionID) {
        this.currentSessionID = currentSessionID;
    }

    public boolean isValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(boolean validationStatus) {
        this.validationStatus = validationStatus;
    }

    public void selectUpcomingSemester() {
    }

    public void selectCourse() {
    }

    public Module getSelectedCourse() {
        return null;
    }

    public void submitCourseRegistration() {
    }
}
