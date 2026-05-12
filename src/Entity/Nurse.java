package Entity;

import Utils.Helper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Nurse extends Person {

    private String nurseId;
    private String departmentId;
    private String shift;
    private String qualification;
    private List<String> assignedPatients;

    public Nurse(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender,
                 String phoneNumber, String email, String address, String nurseId, String departmentId,
                 String shift, String qualification) {

        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address);

        this.nurseId = Helper.isNotNull(nurseId) ? nurseId : Helper.generateId("NUR");


        setDepartmentId(departmentId);
        setShift(shift);
        setQualification(qualification);
        this.assignedPatients = new ArrayList<>();
    }

    public Nurse() {
        super();
        this.nurseId = Helper.generateId("NUR");
        this.assignedPatients = new ArrayList<>();
    }

    // --- Business Logic & Validation ---

    @Override
    public boolean validate() {

        return super.validate() && Helper.isValidString(nurseId) && Helper.isValidString(shift);
    }

    public void assignPatient(String patientId) {

        if (Helper.isValidString(patientId)) {
            if (!assignedPatients.contains(patientId)) {
                assignedPatients.add(patientId);
                System.out.println("Success: Patient " + patientId + " assigned to Nurse " + getLastName());
            } else {
                System.out.println("Note: Patient " + patientId + " is already assigned to this nurse.");
            }
        }
    }

    public void removePatient(String patientId) {
        if (Helper.isValidString(patientId) && assignedPatients.remove(patientId)) {
            System.out.println("Success: Patient " + patientId + " removed from Nurse " + getLastName() + "'s list.");
        } else {
            System.out.println("Error: Patient ID " + patientId + " was not found.");
        }
    }



    public void setNurseId(String nurseId) {
        if (Helper.isValidString(nurseId)) {
            this.nurseId = nurseId;
        } else {
            System.out.println("Error: Invalid Nurse ID format.");
        }
    }

    public void setDepartmentId(String departmentId) {
        if (Helper.isValidString(departmentId)) {
            this.departmentId = departmentId;
        }
    }

    public void setShift(String shift) {

        if (Helper.isNotNull(shift) && !shift.trim().isEmpty()) {
            this.shift = shift;
        } else {
            System.out.println("Validation Error: Shift cannot be empty.");
        }
    }

    public void setQualification(String qualification) {
        if (Helper.isNotNull(qualification)) {
            this.qualification = qualification;
        }
    }

    public void setAssignedPatients(List<String> assignedPatients) {
        if (Helper.isNotNull(assignedPatients)) {
            this.assignedPatients = assignedPatients;
        }
    }

    // --- Display Methods ---

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Nurse ID          : " + nurseId);
        System.out.println("Department ID     : " + (Helper.isNotNull(departmentId) ? departmentId : "N/A"));
        System.out.println("Current Shift     : " + (Helper.isNotNull(shift) ? shift : "Not Assigned"));
        System.out.println("Qualification     : " + (Helper.isNotNull(qualification) ? qualification : "N/A"));
        System.out.println("Assigned Patients : " + (assignedPatients.isEmpty() ? "None" : assignedPatients));
    }

    @Override
    public void displaySummary() {
        System.out.println("Nurse: " + getFirstName() + " " + getLastName() + " [" + nurseId + "] | Shift: " + (Helper.isNotNull(shift) ? shift : "N/A"));
    }

    // --- Getters ---

    public String getNurseId() { return nurseId; }
    public String getDepartmentId() { return departmentId; }
    public String getShift() { return shift; }
    public String getQualification() { return qualification; }
    public List<String> getAssignedPatients() { return assignedPatients; }
}