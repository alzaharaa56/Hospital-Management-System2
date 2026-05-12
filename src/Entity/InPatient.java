package Entity;

import Behaviour.Billable;
import Utils.Helper;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class InPatient extends Patient implements Billable {

    private LocalDate admissionDate;
    private LocalDate dischargeDate;
    private String roomNumber;
    private String bedNumber;
    private String admittingDoctorId;
    private double dailyCharges;

    public InPatient(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender,
                     String phoneNumber, String email, String address, String patientId, String bloodGroup,
                     LocalDate registrationDate, String insuranceId, LocalDate admissionDate,
                     LocalDate dischargeDate, String roomNumber, String bedNumber,
                     String admittingDoctorId, double dailyCharges) {

        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address,
                patientId, bloodGroup, registrationDate, insuranceId);

        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.roomNumber = roomNumber;
        this.bedNumber = bedNumber;
        this.admittingDoctorId = admittingDoctorId;
        this.dailyCharges = dailyCharges;
    }

    public InPatient() {
        super();
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Admission Date      : " + admissionDate);
        System.out.println("Discharge Date      : " + (dischargeDate == null ? "Still Admitted" : dischargeDate));
        System.out.println("Room / Bed          : " + roomNumber + " / " + bedNumber);
        System.out.println("Admitting Doctor ID : " + admittingDoctorId);
        System.out.println("Daily Charges       : $" + dailyCharges);
        System.out.println("Stay Duration       : " + calculateStayDuration() + " days");
        System.out.println("Total Amount Due    : $" + calculateCharges());
    }

    @Override
    public void displaySummary() {
        System.out.println("In-Patient: " + getFirstName() + " " + getLastName() +
                " | Room: " + roomNumber + " | Status: Admitted");
    }

    @Override
    public double calculateCharges() {
        return calculateStayDuration() * dailyCharges;
    }

    @Override
    public void generateBill() {
        System.out.println("\n--- HOSPITAL BILL (IN-PATIENT) ---");
        System.out.println("Patient Name: " + getFirstName() + " " + getLastName());
        System.out.println("Room Number : " + roomNumber);
        System.out.println("Total Days  : " + calculateStayDuration());
        System.out.println("Total Cost  : $" + calculateCharges());
        System.out.println("----------------------------------");
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Payment of $" + amount + " received for patient: " + getLastName());
    }

    public long calculateStayDuration() {
        if (admissionDate == null) return 0;

        LocalDate end = (dischargeDate == null) ? LocalDate.now() : dischargeDate;
        long days = ChronoUnit.DAYS.between(admissionDate, end);

        return (days == 0) ? 1 : days;
    }

    public LocalDate getAdmissionDate() { return admissionDate; }
    public void setAdmissionDate(LocalDate admissionDate) { this.admissionDate = admissionDate; }

    public LocalDate getDischargeDate() { return dischargeDate; }
    public void setDischargeDate(LocalDate dischargeDate) { this.dischargeDate = dischargeDate; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public String getBedNumber() { return bedNumber; }
    public void setBedNumber(String bedNumber) { this.bedNumber = bedNumber; }

    public String getAdmittingDoctorId() { return admittingDoctorId; }
    public void setAdmittingDoctorId(String admittingDoctorId) { this.admittingDoctorId = admittingDoctorId; }

    public double getDailyCharges() { return dailyCharges; }
    public void setDailyCharges(double dailyCharges) { this.dailyCharges = dailyCharges; }
}