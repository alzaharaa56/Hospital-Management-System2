package Entity;

import Behavior.Displayable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Nurse extends Person implements Displayable {

    private String nurseId;
    private String departmentId;
    private String shift; // Morning / Evening / Night
    private String qualification;
    private List<Patient> assignedPatients;

    // --- Constructors ---


    public Nurse(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender, String phoneNumber, String email, String address, String nurseId, String departmentId, String shift, String qualification) {
        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address);
        this.nurseId = nurseId;
        this.departmentId = departmentId;
        this.shift = shift;
        this.qualification = qualification;
        this.assignedPatients = new ArrayList<>();
    }


    public Nurse(String nurseId, String name, String departmentId, String shift) {
        super();
        this.setFirstName(name);
        this.nurseId = nurseId;
        this.departmentId = departmentId;
        this.shift = shift;
        this.assignedPatients = new ArrayList<>();
    }

    public Nurse() {
        this.assignedPatients = new ArrayList<>();
    }




    @Override
    public void displayInfo() {
        System.out.println("\n--- Nurse Detailed Info ---");
        super.displayInfo();
        System.out.println("Nurse ID: " + nurseId);
        System.out.println("Department: " + departmentId);
        System.out.println("Shift: " + shift);
        System.out.println("Assigned Patients Count: " + assignedPatients.size());
    }

    @Override
    public void displaySummary() {

        System.out.printf("ID: %-10s | Name: %-15s | Shift: %-10s | Dept: %-10s\n",
                nurseId, getFirstName(), shift, departmentId);
    }



    public void assignPatient(Patient patient) {

        if (HelperUtils.isNull(patient)) {
            System.out.println("Error: Invalid patient.");
            return;
        }


        if (assignedPatients.contains(patient)) {
            System.out.println("Notice: Patient already assigned to this nurse.");
            return;
        }

        assignedPatients.add(patient);
        System.out.println("Success: Patient assigned to Nurse " + this.getFirstName());
    }

    public void assignPatient(String patientId) {
        if (HelperUtils.isValidString(patientId)) {
            System.out.println("Assigning Patient ID [" + patientId + "] to Nurse " + nurseId);
        }
    }

    public void removePatient(Patient patient) {
        if (HelperUtils.isNull(patient)) return;

        if (assignedPatients.remove(patient)) {
            System.out.println("Patient removed from Nurse " + nurseId);
        } else {
            System.out.println("Patient not found.");
        }
    }

    public void setNurseId(String nurseId) {

    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }
}