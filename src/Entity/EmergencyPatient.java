package Entity;

import java.time.LocalDate;
import java.util.List;

/**
 * Task 3.2: EmergencyPatient inherits from Patient.
 * Represents patients requiring urgent medical attention with triage prioritization.
 */
public class EmergencyPatient extends Patient {

    private String emergencyType;
    private String arrivalMode; // e.g., Ambulance, Walk-in, Helicopter
    private int triageLevel;    // Priority scale: 1 (Critical) to 5 (Non-urgent)
    private boolean admittedViaER;

    // Full Constructor
    public EmergencyPatient(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender,
                            String phoneNumber, String email, String address, String patientId, String bloodGroup,
                            LocalDate registrationDate, String insuranceId, String emergencyType,
                            String arrivalMode, int triageLevel, boolean admittedViaER) {

        // Calling the parent (Patient) constructor
        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address,
                patientId, bloodGroup, registrationDate, insuranceId);

        this.emergencyType = emergencyType;
        this.arrivalMode = arrivalMode;
        this.triageLevel = triageLevel;
        this.admittedViaER = admittedViaER;
    }

    // Default Constructor
    public EmergencyPatient() {
        super();
    }

    // --- Task 3.2: Implementation of Displayable ---

    @Override
    public void displayInfo() {
        super.displayInfo(); // Displays Person and Patient details
        System.out.println("Emergency Type : " + emergencyType);
        System.out.println("Arrival Mode   : " + arrivalMode);
        System.out.println("Triage Level   : " + triageLevel + " (Priority)");
        System.out.println("Admitted via ER: " + (admittedViaER ? "Yes" : "No"));
    }

    @Override
    public void displaySummary() {
        System.out.println("[ER-PRIORITY " + triageLevel + "] Patient: " + getFirstName() + " " + getLastName() +
                " | Case: " + emergencyType);
    }

    // --- Task 3.2: Validation logic ---

    @Override
    public boolean validate() {
        // Validating parent rules + triage level range
        return super.validate() && (triageLevel >= 1 && triageLevel <= 5);
    }

    // --- Business Logic Methods ---

    /**
     * Updates the triage level with validation.
     */
    public void updateTriageLevel(int level) {
        if (level < 1 || level > 5) {
            System.out.println("Error: Invalid triage level. Must be between 1 and 5.");
            return;
        }
        this.triageLevel = level;
        System.out.println("Triage level updated to " + triageLevel + " for patient " + getLastName());
    }

    // --- Getters and Setters ---

    public String getEmergencyType() { return emergencyType; }
    public void setEmergencyType(String emergencyType) { this.emergencyType = emergencyType; }

    public String getArrivalMode() { return arrivalMode; }
    public void setArrivalMode(String arrivalMode) { this.arrivalMode = arrivalMode; }

    public int getTriageLevel() { return triageLevel; }
    public void setTriageLevel(int triageLevel) { this.triageLevel = triageLevel; }

    public boolean isAdmittedViaER() { return admittedViaER; }
    public void setAdmittedViaER(boolean admittedViaER) { this.admittedViaER = admittedViaER; }
}