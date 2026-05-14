package Entity;

import Behavior.Displayable;
import Behavior.Billable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class EmergencyPatient extends InPatient implements Displayable, Billable {

    private String emergencyType;
    private String arrivalMode;
    private int triageLevel;
    private boolean admittedViaER;

    public EmergencyPatient(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender, String phoneNumber, String email, String address, String patientId, String bloodGroup, List<String> allergies, String emergencyContact, LocalDate registrationDate, List<MedicalRecord> medicalRecords, String insuranceId, List<Appointment> appointments, LocalDate admissionDate, LocalDate dischargeDate, String roomNumber, String bedNumber, String admittingDoctorId, double dailyCharges, String emergencyType, String arrivalMode, int triageLevel, boolean admittedViaER) {
        super();
        this.emergencyType = emergencyType;
        this.arrivalMode = arrivalMode;
        this.triageLevel = triageLevel;
        this.admittedViaER = admittedViaER;
    }

    public EmergencyPatient() {
        super();
    }


    @Override
    public double calculateCharges() {

        LocalDate end = (getDischargeDate() != null) ? getDischargeDate() : LocalDate.now();
        long days = ChronoUnit.DAYS.between(getAdmissionDate(), end);
        if (days <= 0) days = 1;


        double emergencyFee = (triageLevel == 1) ? 50.0 : 20.0;
        return (days * getDailyCharges()) + emergencyFee;
    }

    @Override
    public void generateBill() {
        System.out.println("********** EMERGENCY BILL **********");
        System.out.println("Patient: " + getFirstName() + " " + getLastName());
        System.out.println("Emergency Type: " + emergencyType);
        System.out.println("Total Charges: " + calculateCharges() + " OMR");
        System.out.println("************************************");
    }

    @Override
    public boolean processPayment(double amount) {
        if (amount >= calculateCharges()) {
            System.out.println("Payment of " + amount + " OMR processed successfully.");
            return true;
        }
        System.out.println("Insufficient payment amount.");
        return false;
    }



    public String getEmergencyType() { return emergencyType; }
    public void setEmergencyType(String emergencyType) { this.emergencyType = emergencyType; }

    public String getArrivalMode() { return arrivalMode; }
    public void setArrivalMode(String arrivalMode) { this.arrivalMode = arrivalMode; }

    public int getTriageLevel() { return triageLevel; }
    public void setTriageLevel(int triageLevel) {
        if (triageLevel < 1 || triageLevel > 5) {
            System.out.println("Invalid triage level. Must be 1-5.");
            return;
        }
        this.triageLevel = triageLevel;
    }

    public boolean isAdmittedViaER() { return admittedViaER; }
    public void setAdmittedViaER(boolean admittedViaER) { this.admittedViaER = admittedViaER; }



    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Emergency Type : " + emergencyType);
        System.out.println("Arrival Mode   : " + arrivalMode);
        System.out.println("Triage Level   : " + triageLevel);
        System.out.println("Admitted Via ER: " + (admittedViaER ? "Yes" : "No"));
        System.out.println("Current Bill   : " + calculateCharges() + " OMR");
    }

    @Override
    public void displaySummary() {
        System.out.println("Patient ID: " + getPatientId() +
                " | Name: " + getFirstName() + " " + getLastName() +
                " | Status: EMERGENCY (" + emergencyType + ")");
    }
}