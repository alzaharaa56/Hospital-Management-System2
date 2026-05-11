package Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Nurse extends Person {

    // Nurse-specific fields
    private String nurseId;
    private String departmentId;
    private String shift; // Morning / Evening / Night
    private String qualification;
    private List<String> assignedPatients;

    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public List<String> getAssignedPatients() {
        return assignedPatients;
    }

    public void setAssignedPatients(List<String> assignedPatients) {
        this.assignedPatients = assignedPatients;
    }

    // Constructor with constructor chaining
    public Nurse(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender, String phoneNumber, String email, String address, String nurseId, String departmentId, String shift, String qualification, List<String> assignedPatients) {
        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address);
        this.nurseId = nurseId;
        this.departmentId = departmentId;
        this.shift = shift;
        this.qualification = qualification;
        this.assignedPatients = new ArrayList<>();
    }

    @Override
    public void displayInfo() {
        super.displayInfo();

        System.out.println("Nurse ID: " + nurseId);
        System.out.println("Department ID: " + departmentId);
        System.out.println("Shift: " + shift);
        System.out.println("Qualification: " + qualification);
        System.out.println("Assigned Patients: " + assignedPatients);
    }

    // Method to assign patient
    public void assignPatient(String patientId ,String nurseId ) {
        assignedPatients.add(patientId);
        System.out.println(patientId + " assigned to Nurse " + nurseId);
    }

    // Method to remove patient
    public void removePatient(String patientId ,String nurseId) {
        if (assignedPatients.remove(patientId)) {
            System.out.println(patientId + " removed from Nurse " + nurseId);
        } else {
            System.out.println("Patient not found.");
        }
    }

}