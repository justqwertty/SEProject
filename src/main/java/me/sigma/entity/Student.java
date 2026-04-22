package me.sigma.entity;

public class Student extends User {
    private int studentID;
    private boolean financialAidStatus;
    private int absenceThreshold;

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public boolean isFinancialAidStatus() {
        return financialAidStatus;
    }

    public void setFinancialAidStatus(boolean financialAidStatus) {
        this.financialAidStatus = financialAidStatus;
    }

    public int getAbsenceThreshold() {
        return absenceThreshold;
    }

    public void setAbsenceThreshold(int absenceThreshold) {
        this.absenceThreshold = absenceThreshold;
    }

    public void maintainPersonalDetails(String name, String email, String phone) {
    }
}
