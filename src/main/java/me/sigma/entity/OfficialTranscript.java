package me.sigma.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class OfficialTranscript {
    private String transcriptID;
    private String studentID;
    private LocalDateTime generationDate;
    private List<Grade> academicHistory;
    private float cumulativeGPA;
    private String honors;

    public String getTranscriptID() {
        return transcriptID;
    }

    public void setTranscriptID(String transcriptID) {
        this.transcriptID = transcriptID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public LocalDateTime getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(LocalDateTime generationDate) {
        this.generationDate = generationDate;
    }

    public List<Grade> getAcademicHistory() {
        return academicHistory;
    }

    public void setAcademicHistory(List<Grade> academicHistory) {
        this.academicHistory = academicHistory;
    }

    public float getCumulativeGPA() {
        return cumulativeGPA;
    }

    public void setCumulativeGPA(float cumulativeGPA) {
        this.cumulativeGPA = cumulativeGPA;
    }

    public String getHonors() {
        return honors;
    }

    public void setHonors(String honors) {
        this.honors = honors;
    }

    public void generate() {
    }
}
