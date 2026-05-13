package Entity;

import Behavior.Displayable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Level 2 Inheritance
public class Nurse extends Person implements Displayable {

    // Nurse-specific fields
    private String nurseId;
    private String departmentId;
    private String shift; // Morning / Evening / Night
    private String qualification;
    private List<Patient> assignedPatients;

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

    public List<Patient> getAssignedPatients() {
        return assignedPatients;
    }

    public void setAssignedPatients(List<Patient> assignedPatients) {
        this.assignedPatients = assignedPatients;
    }

    // Constructor chaining to Person
    public Nurse(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender, String phoneNumber, String email, String address, String nurseId, String departmentId, String shift, String qualification, List<String> assignedPatients) {

        // Calls Person constructor
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

    @Override
    public void displaySummary() {

    }

    // Method to assign patient
    public void assignPatient(Patient patient ,String nurseId ) {

        // Validate patient object
        if (HelperUtils.isNull(patient)) {
            System.out.println("Invalid patient.");
            return;
        }

        // Validate nurse ID
        if (HelperUtils.isValidString(nurseId)) {
            System.out.println("Invalid nurse ID.");
            return;
        }

        // Check if patient already assigned
        if (assignedPatients.contains(patient)) {
            System.out.println("Patient " + patient.getPatientId() + " is already assigned.");
            return;
        }

        assignedPatients.add(patient);
        System.out.println("Patient " + patient.getPatientId()
                + " assigned to Nurse " + nurseId);
    }

    // Method to remove patient
    public void removePatient(Patient patient ,String nurseId) {

        // Validate patient
        if (HelperUtils.isNull(patient)) {
            System.out.println("Invalid patient.");
            return;
        }

        // Validate nurse ID
        if (!HelperUtils.isValidString(nurseId)) {
            System.out.println("Invalid nurse ID.");
            return;
        }

        // Remove patient from assigned list
        if (assignedPatients.remove(patient)) {
            System.out.println("Patient " + patient.getPatientId()
                    + " removed from Nurse " + nurseId);
        } else {
            System.out.println("Patient not found for Nurse " + nurseId);
        }

    }

}