package Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Task 3.2: Surgeon class inheriting from Doctor.
 * Represents a medical specialist authorized to perform surgical procedures.
 */
public class Surgeon extends Doctor {

    private int surgeriesPerformed;
    private List<String> surgeryTypes;
    private boolean operationTheatreAccess;

    // Full Constructor with super() chaining
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

    // Default Constructor
    public Surgeon() {
        super();
        this.surgeryTypes = new ArrayList<>();
    }

    // --- Task 3.2: Implementation of Displayable ---

    @Override
    public void displayInfo() {
        super.displayInfo(); // Displays Person and Doctor attributes
        System.out.println("Surgeries Performed      : " + surgeriesPerformed);
        System.out.println("Surgery Specializations  : " + surgeryTypes);
        System.out.println("Operation Theatre Access : " + (operationTheatreAccess ? "Granted" : "Denied"));
    }

    @Override
    public void displaySummary() {
        System.out.println("Surgeon: Dr. " + getLastName() + " | Specialty: " + getSpecialization() +
                " | Total Surgeries: " + surgeriesPerformed);
    }

    // --- Task 3.2: Validation logic ---

    @Override
    public boolean validate() {
        // Inherits Person validation and ensures at least one surgery type is listed for a surgeon
        return super.validate() && surgeriesPerformed >= 0 && !surgeryTypes.isEmpty();
    }

    // --- Business Logic Methods ---

    /**
     * Attempts to perform a surgery if the type is supported and access is granted.
     */
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

    /**
     * Updates the total lifetime surgery count.
     */
    public void updateSurgeryCount(int count) {
        if (count >= 0) {
            this.surgeriesPerformed = count;
            System.out.println("Surgery record updated to: " + count);
        } else {
            System.out.println("Error: Surgery count cannot be negative.");
        }
    }

    // --- Getters and Setters ---

    public int getSurgeriesPerformed() { return surgeriesPerformed; }
    public void setSurgeriesPerformed(int surgeriesPerformed) { this.surgeriesPerformed = surgeriesPerformed; }

    public List<String> getSurgeryTypes() { return surgeryTypes; }
    public void setSurgeryTypes(List<String> surgeryTypes) { this.surgeryTypes = surgeryTypes; }

    public boolean isOperationTheatreAccess() { return operationTheatreAccess; }
    public void setOperationTheatreAccess(boolean operationTheatreAccess) { this.operationTheatreAccess = operationTheatreAccess; }
}