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

        this.emergencyType = emergencyType;
        this.arrivalMode = arrivalMode;
        this.triageLevel = triageLevel;
        this.admittedViaER = admittedViaER;
    }

    public EmergencyPatient() {
        super();
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
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

    @Override
    public boolean validate() {
        return super.validate() && (triageLevel >= 1 && triageLevel <= 5);
    }

    public void updateTriageLevel(int level) {
        if (level < 1 || level > 5) {
            System.out.println("Error: Invalid triage level. Must be between 1 and 5.");
            return;
        }
        this.triageLevel = level;
        System.out.println("Triage level updated to " + triageLevel + " for patient " + getLastName());
    }

    public String getEmergencyType() { return emergencyType; }
    public void setEmergencyType(String emergencyType) { this.emergencyType = emergencyType; }

    public String getArrivalMode() { return arrivalMode; }
    public void setArrivalMode(String arrivalMode) { this.arrivalMode = arrivalMode; }

    public int getTriageLevel() { return triageLevel; }
    public void setTriageLevel(int triageLevel) { this.triageLevel = triageLevel; }

    public boolean isAdmittedViaER() { return admittedViaER; }
    public void setAdmittedViaER(boolean admittedViaER) { this.admittedViaER = admittedViaER; }
}