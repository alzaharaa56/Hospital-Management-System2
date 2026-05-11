package Entity;

import java.time.LocalDate;
import java.util.List;

public class EmergencyPatient extends Patient{

    private String emergencyType;
    private String arrivalMode; // Ambulance / Walk-in
    private int triageLevel;    // 1 to 5
    private boolean admittedViaER;

    public EmergencyPatient(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender, String phoneNumber, String email, String address, String patientId, String bloodGroup, List<String> allergies, String emergencyContact, LocalDate registrationDate, List<MedicalRecord> medicalRecords, String insuranceId, List<Appointment> appointments, String emergencyType, String arrivalMode, int triageLevel, boolean admittedViaER) {
        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address, patientId, bloodGroup, allergies, emergencyContact, registrationDate, medicalRecords, insuranceId, appointments);
        this.emergencyType = emergencyType;
        this.arrivalMode = arrivalMode;
        this.triageLevel = triageLevel;
        this.admittedViaER = admittedViaER;
    }

    public String getEmergencyType() {
        return emergencyType;
    }

    public void setEmergencyType(String emergencyType) {
        this.emergencyType = emergencyType;
    }

    public String getArrivalMode() {
        return arrivalMode;
    }

    public void setArrivalMode(String arrivalMode) {
        this.arrivalMode = arrivalMode;
    }

    public int getTriageLevel() {
        return triageLevel;
    }

    public void setTriageLevel(int triageLevel) {

        if (triageLevel < 1 || triageLevel > 5) {
            System.out.println("Invalid triage level.");
            return;
        }

        this.triageLevel = triageLevel;
    }

    public boolean isAdmittedViaER() {
        return admittedViaER;
    }

    public void setAdmittedViaER(boolean admittedViaER) {
        this.admittedViaER = admittedViaER;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Emergency Type : " + emergencyType);
        System.out.println("Arrival Mode   : " + arrivalMode);
        System.out.println("Triage Level   : " + triageLevel);
        System.out.println("Admitted Via ER: " + admittedViaER);

    }
}