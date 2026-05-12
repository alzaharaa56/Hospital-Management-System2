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


        setAdmissionDate(admissionDate);
        setDischargeDate(dischargeDate);
        setRoomNumber(roomNumber);
        setBedNumber(bedNumber);
        setAdmittingDoctorId(admittingDoctorId);
        setDailyCharges(dailyCharges);
    }

    public InPatient() {
        super();
    }



    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Admission Date      : " + admissionDate);
        System.out.println("Discharge Date      : " + (Helper.isNotNull(dischargeDate) ? dischargeDate : "Still Admitted"));
        System.out.println("Room / Bed          : " + (Helper.isNotNull(roomNumber) ? roomNumber : "N/A") + " / " + (Helper.isNotNull(bedNumber) ? bedNumber : "N/A"));
        System.out.println("Admitting Doctor ID : " + (Helper.isNotNull(admittingDoctorId) ? admittingDoctorId : "Not Assigned"));
        System.out.println("Daily Charges       : $" + dailyCharges);
        System.out.println("Stay Duration       : " + calculateStayDuration() + " days");
        System.out.println("Total Amount Due    : $" + calculateCharges());
    }

    @Override
    public void displaySummary() {
        System.out.println("In-Patient: " + getFirstName() + " " + getLastName() +
                " | Room: " + (Helper.isNotNull(roomNumber) ? roomNumber : "N/A") + " | Status: Admitted");
    }



    @Override
    public double calculateCharges() {
        return calculateStayDuration() * dailyCharges;
    }

    @Override
    public void generateBill() {
        System.out.println("\n--- HOSPITAL BILL (IN-PATIENT) ---");
        System.out.println("Patient Name: " + getFirstName() + " " + getLastName());
        System.out.println("Room Number : " + (Helper.isNotNull(roomNumber) ? roomNumber : "N/A"));
        System.out.println("Total Days  : " + calculateStayDuration());
        System.out.println("Total Cost  : $" + calculateCharges());
        System.out.println("----------------------------------");
    }

    @Override
    public void processPayment(double amount) {

        if (Helper.isPositive(amount)) {
            System.out.println("Payment of $" + amount + " received for patient: " + getLastName());
        } else {
            System.out.println("Payment Error: Invalid amount.");
        }
    }

    // --- Business Logic ---

    public long calculateStayDuration() {
        if (Helper.isNull(admissionDate)) return 0;

        LocalDate end = (Helper.isNull(dischargeDate)) ? LocalDate.now() : dischargeDate;
        long days = ChronoUnit.DAYS.between(admissionDate, end);

        return (days <= 0) ? 1 : days;
    }



    public void setAdmissionDate(LocalDate admissionDate) {

        if (Helper.isNotNull(admissionDate) && !Helper.isFutureDate(admissionDate)) {
            this.admissionDate = admissionDate;
        } else {
            this.admissionDate = LocalDate.now();
        }
    }

    public void setDischargeDate(LocalDate dischargeDate) {

        if (Helper.isNotNull(dischargeDate) && Helper.isNotNull(admissionDate) && !dischargeDate.isBefore(admissionDate)) {
            this.dischargeDate = dischargeDate;
        } else {
            this.dischargeDate = null;
        }
    }

    public void setRoomNumber(String roomNumber) {
        if (Helper.isValidString(roomNumber)) {
            this.roomNumber = roomNumber;
        }
    }

    public void setBedNumber(String bedNumber) {
        if (Helper.isValidString(bedNumber)) {
            this.bedNumber = bedNumber;
        }
    }

    public void setAdmittingDoctorId(String admittingDoctorId) {
        if (Helper.isValidString(admittingDoctorId)) {
            this.admittingDoctorId = admittingDoctorId;
        }
    }

    public void setDailyCharges(double dailyCharges) {
        if (Helper.isPositive(dailyCharges)) {
            this.dailyCharges = dailyCharges;
        }
    }



    public LocalDate getAdmissionDate() { return admissionDate; }
    public LocalDate getDischargeDate() { return dischargeDate; }
    public String getRoomNumber() { return roomNumber; }
    public String getBedNumber() { return bedNumber; }
    public String getAdmittingDoctorId() { return admittingDoctorId; }
    public double getDailyCharges() { return dailyCharges; }
}