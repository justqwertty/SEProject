package me.seproject.mavenproject1.entity;

import java.util.List;

public class Module {
    private String moduleID;
    private String moduleName;
    private List<String> prerequisites;
    private int credits;
    private int capacity;

    public String getModuleID() {
        return moduleID;
    }

    public void setModuleID(String moduleID) {
        this.moduleID = moduleID;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public List<String> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setPrerequisites(String moduleID, int credits) {
    }

    public boolean checkPrerequisites(String moduleID) {
        return false;
    }

    public void editModuleDetails(String name, int credits) {
    }

    public boolean isCapacityFull() {
        return false;
    }

    public double getAttendanceStatus() {
        return 0.0;
    }

    public java.util.List<Module> getModuleList() {
        return null;
    }

    public void viewModules() {
    }

    public void registerForModule(Module module) {
    }
}
