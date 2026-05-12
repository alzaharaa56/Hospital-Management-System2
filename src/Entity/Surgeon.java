package Entity;

import Utils.Helper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Surgeon extends Doctor {

    private int surgeriesPerformed;
    private List<String> surgeryTypes;
    private boolean operationTheatreAccess;

    public Surgeon(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender,
                   String phoneNumber, String email, String address, String doctorId, String specialization,
                   String qualification, int experienceYears, String departmentId, double consultationFee,
                   List<String> availableSlots, List<String> assignedPatients,
                   int surgeriesPerformed, List<String> surgeryTypes, boolean operationTheatreAccess) {

        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address,
                doctorId, specialization, qualification, experienceYears, departmentId,
                consultationFee, availableSlots, assignedPatients);

        this.surgeriesPerformed = surgeriesPerformed;
        this.surgeryTypes = (surgeryTypes != null) ? surgeryTypes : new ArrayList<>();
        this.operationTheatreAccess = operationTheatreAccess;
    }

    public Surgeon() {
        super();
        this.surgeryTypes = new ArrayList<>();
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Surgeries Performed      : " + surgeriesPerformed);
        System.out.println("Surgery Specializations  : " + surgeryTypes);
        System.out.println("Operation Theatre Access : " + (operationTheatreAccess ? "Granted" : "Denied"));
    }

    @Override
    public void displaySummary() {
        System.out.println("Surgeon: Dr. " + getLastName() + " | Specialty: " + getSpecialization() +
                " | Total Surgeries: " + surgeriesPerformed);
    }

    @Override
    public boolean validate() {
        return super.validate() && surgeriesPerformed >= 0 && !surgeryTypes.isEmpty();
    }

    public boolean performSurgery(String surgeryType) {
        if (operationTheatreAccess && surgeryTypes.contains(surgeryType)) {
            surgeriesPerformed++;
            System.out.println("Success: Surgery '" + surgeryType + "' performed successfully by Dr. " + getLastName());
            return true;
        } else {
            System.out.println("Surgery Failed: Either access to the Operation Theatre is denied or Dr. " +
                    getLastName() + " is not certified for " + surgeryType);
            return false;
        }
    }

    public void updateSurgeryCount(int count) {
        if (count >= 0) {
            this.surgeriesPerformed = count;
            System.out.println("Surgery record updated to: " + count);
        } else {
            System.out.println("Error: Surgery count cannot be negative.");
        }
    }

    public int getSurgeriesPerformed() { return surgeriesPerformed; }
    public void setSurgeriesPerformed(int surgeriesPerformed) { this.surgeriesPerformed = surgeriesPerformed; }

    public List<String> getSurgeryTypes() { return surgeryTypes; }
    public void setSurgeryTypes(List<String> surgeryTypes) { this.surgeryTypes = surgeryTypes; }

    public boolean isOperationTheatreAccess() { return operationTheatreAccess; }
    public void setOperationTheatreAccess(boolean operationTheatreAccess) { this.operationTheatreAccess = operationTheatreAccess; }
}