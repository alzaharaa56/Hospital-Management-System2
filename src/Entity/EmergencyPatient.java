package Entity;

import Utils.Helper;
import java.time.LocalDate;

public class EmergencyPatient extends Patient {

    private String emergencyType;
    private String arrivalMode;
    private int triageLevel;
    private boolean admittedViaER;


    public EmergencyPatient(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender,
                            String phoneNumber, String email, String address, String patientId, String bloodGroup,
                            LocalDate registrationDate, String insuranceId, String emergencyType,
                            String arrivalMode, int triageLevel, boolean admittedViaER) {

        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address,
                patientId, bloodGroup, registrationDate, insuranceId);


        setEmergencyType(emergencyType);
        setArrivalMode(arrivalMode);
        setTriageLevel(triageLevel);
        this.admittedViaER = admittedViaER;
    }

    public EmergencyPatient() {
        super();
    }

    @Override
    public boolean validate() {

        return super.validate() &&
                (triageLevel >= 1 && triageLevel <= 5) &&
                Helper.isValidString(emergencyType);
    }

    public void updateTriageLevel(int level) {

        setTriageLevel(level);
        if (this.triageLevel == level) {
            System.out.println("Triage level updated to " + triageLevel + " for patient " + getLastName());
        }
    }



    public void setEmergencyType(String emergencyType) {
        if (Helper.isValidString(emergencyType)) {
            this.emergencyType = emergencyType;
        } else {
            this.emergencyType = "Unknown Emergency";
        }
    }

    public void setArrivalMode(String arrivalMode) {
        if (Helper.isValidString(arrivalMode)) {
            this.arrivalMode = arrivalMode;
        } else {
            this.arrivalMode = "Self-Arrival";
        }
    }

    public void setTriageLevel(int triageLevel) {

        if (triageLevel >= 1 && triageLevel <= 5) {
            this.triageLevel = triageLevel;
        } else {
            System.out.println("Error: Invalid triage level (Must be 1-5). Setting to 5 (Non-Urgent).");
            this.triageLevel = 5;
        }
    }

    public void setAdmittedViaER(boolean admittedViaER) {
        this.admittedViaER = admittedViaER;
    }

    // --- Display Methods ---

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Emergency Type : " + (Helper.isNotNull(emergencyType) ? emergencyType : "N/A"));
        System.out.println("Arrival Mode   : " + (Helper.isNotNull(arrivalMode) ? arrivalMode : "Unknown"));
        System.out.println("Triage Level   : " + triageLevel + " (Priority)");
        System.out.println("Admitted via ER: " + (admittedViaER ? "Yes" : "No"));
    }

    @Override
    public void displaySummary() {
        System.out.println("[ER-PRIORITY " + triageLevel + "] Patient: " + getFirstName() + " " + getLastName() +
                " | Case: " + (Helper.isNotNull(emergencyType) ? emergencyType : "Emergency"));
    }

    // --- Getters ---

    public String getEmergencyType() { return emergencyType; }
    public String getArrivalMode() { return arrivalMode; }
    public int getTriageLevel() { return triageLevel; }
    public boolean isAdmittedViaER() { return admittedViaER; }
}