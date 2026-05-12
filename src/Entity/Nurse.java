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
        this.nurseId = nurseId;
        this.departmentId = departmentId;
        this.shift = shift;
        this.qualification = qualification;
        this.assignedPatients = new ArrayList<>();
    }

    public Nurse() {
        super();
        this.assignedPatients = new ArrayList<>();
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
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

    @Override
    public boolean validate() {
        return super.validate() && Helper.isValidString(shift);
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
        if (assignedPatients.remove(patientId)) {
            System.out.println("Success: Patient " + patientId + " removed from Nurse " + getLastName() + "'s list.");
        } else {
            System.out.println("Error: Patient ID " + patientId + " was not found.");
        }
    }

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