package Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Task 3.2: Nurse class inheriting from Person.
 * Manages nursing staff information, shifts, and patient assignments.
 */
public class Nurse extends Person {

    // Nurse-specific fields
    private String nurseId;
    private String departmentId;
    private String shift; // e.g., Morning, Evening, Night
    private String qualification;
    private List<String> assignedPatients;

    // Full Constructor with constructor chaining to Person
    public Nurse(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender,
                 String phoneNumber, String email, String address, String nurseId, String departmentId,
                 String shift, String qualification) {

        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address);
        this.nurseId = nurseId;
        this.departmentId = departmentId;
        this.shift = shift;
        this.qualification = qualification;
        this.assignedPatients = new ArrayList<>();
    }

    // Default Constructor
    public Nurse() {
        super();
        this.assignedPatients = new ArrayList<>();
    }

    // --- Task 3.2: Implementation of Displayable ---

    @Override
    public void displayInfo() {
        super.displayInfo(); // Displays Person details (Name, ID, Contact)
        System.out.println("Nurse ID          : " + nurseId);
        System.out.println("Department ID     : " + departmentId);
        System.out.println("Current Shift     : " + shift);
        System.out.println("Qualification     : " + qualification);
        System.out.println("Assigned Patients : " + (assignedPatients.isEmpty() ? "None" : assignedPatients));
    }

    @Override
    public void displaySummary() {
        System.out.println("Nurse: " + getFirstName() + " " + getLastName() + " [" + nurseId + "] | Shift: " + shift);
    }

    // --- Task 3.2: Validation ---

    @Override
    public boolean validate() {
        // Inherits Person validation and ensures shift is assigned
        return super.validate() && shift != null && !shift.isEmpty();
    }

    // --- Business Logic Methods ---

    /**
     * Assigns a patient to this nurse's care list.
     */
    public void assignPatient(String patientId) {
        if (!assignedPatients.contains(patientId)) {
            assignedPatients.add(patientId);
            System.out.println("Success: Patient " + patientId + " assigned to Nurse " + getLastName());
        } else {
            System.out.println("Note: Patient " + patientId + " is already assigned to this nurse.");
        }
    }

    /**
     * Removes a patient from this nurse's care list.
     */
    public void removePatient(String patientId) {
        if (assignedPatients.remove(patientId)) {
            System.out.println("Success: Patient " + patientId + " removed from Nurse " + getLastName() + "'s list.");
        } else {
            System.out.println("Error: Patient ID " + patientId + " was not found in the assignment list.");
        }
    }

    // --- Getters and Setters ---

    public String getNurseId() { return nurseId; }
    public void setNurseId(String nurseId) { this.nurseId = nurseId; }

    public String getDepartmentId() { return departmentId; }
    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }

    public String getShift() { return shift; }
    public void setShift(String shift) { this.shift = shift; }

    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }

    public List<String> getAssignedPatients() { return assignedPatients; }
    public void setAssignedPatients(List<String> assignedPatients) { this.assignedPatients = assignedPatients; }
}