package Entity;

import Behaviour.Appointable;
import Behaviour.Billable;
import Utils.Helper;
import java.time.LocalDate;

public class Patient extends Person implements Appointable, Billable {
    private String patientId;
    private String bloodGroup;
    private LocalDate registrationDate;
    private String insuranceId;

    // Full Constructor
    public Patient(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender,
                   String phoneNumber, String email, String address, String pId,
                   String bloodGroup, LocalDate registrationDate, String insuranceId) {

        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address);


        this.patientId = Helper.isNotNull(pId) ? pId : Helper.generateId("PAT");


        setBloodGroup(bloodGroup);
        setInsuranceId(insuranceId);


        this.registrationDate = Helper.isNotNull(registrationDate) ? registrationDate : LocalDate.now();
    }

    // Default Constructor
    public Patient() {
        super();
        this.patientId = Helper.generateId("PAT");
        this.registrationDate = LocalDate.now();
    }

    // --- Business Logic & Validation ---

    @Override
    public boolean validate() {

        return super.validate() &&
                Helper.isValidString(patientId) &&
                (Helper.isPastDate(getDateOfBirth()) || Helper.isToday(getDateOfBirth()));
    }



    public void setPatientId(String pId) {

        if (Helper.isValidString(pId)) {
            this.patientId = pId;
        } else {
            System.out.println("Error: Invalid Patient ID format.");
        }
    }

    public void setBloodGroup(String blood) {

        if (Helper.isValidString(blood, "^(A|B|AB|O)[+-]$")) {
            this.bloodGroup = blood;
        } else {
            System.out.println("Validation Error: Invalid blood group format (e.g., A+, O-).");
        }
    }

    public void setInsuranceId(String insuranceId) {

        if (Helper.isNotNull(insuranceId) && !insuranceId.trim().isEmpty()) {
            this.insuranceId = insuranceId;
        } else {
            this.insuranceId = "Uninsured";
        }
    }

    public void setRegistrationDate(LocalDate date) {

        if (Helper.isNotNull(date) && !Helper.isFutureDate(date)) {
            this.registrationDate = date;
        } else {
            this.registrationDate = LocalDate.now();
        }
    }

    // --- Display Methods ---

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Patient ID       : " + patientId);
        System.out.println("Blood Group      : " + (Helper.isNotNull(bloodGroup) ? bloodGroup : "Unknown"));
        System.out.println("Registration Date: " + registrationDate);
        System.out.println("Insurance Status : " + (Helper.isNotNull(insuranceId) ? insuranceId : "Private/Uninsured"));
    }

    @Override
    public void displaySummary() {
        System.out.println("Patient Record: " + getFirstName() + " " + getLastName() + " [ID: " + patientId + "]");
    }

    // --- Appointable Implementation ---

    @Override
    public void scheduleAppointment(Appointment a) {
        if (Helper.isNotNull(a)) {
            System.out.println("Scheduling Success: Appointment linked to Patient " + patientId);
        }
    }

    @Override
    public void cancelAppointment(String id) {
        if (Helper.isValidString(id)) {
            System.out.println("Cancellation Success: Appointment " + id + " has been removed.");
        }
    }

    @Override
    public void rescheduleAppointment(String id, LocalDate newDate) {

        if (Helper.isFutureDate(newDate) || Helper.isToday(newDate)) {
            System.out.println("Update Success: Appointment " + id + " moved to " + newDate);
        } else {
            System.out.println("Scheduling Error: Cannot reschedule to a date in the past.");
        }
    }

    // --- Billable Implementation ---

    @Override
    public double calculateCharges() {
        return 150.0;
    }

    @Override
    public void generateBill() {
        System.out.println("\n--- INVOICE ---");
        System.out.println("Patient Name: " + getFirstName() + " " + getLastName());
        System.out.println("Patient ID  : " + patientId);
        System.out.println("Total Due   : $" + calculateCharges());
        System.out.println("----------------");
    }

    @Override
    public void processPayment(double amount) {

        if (Helper.isPositive(amount)) {
            System.out.println("Payment Receipt: Transaction of $" + amount + " successful.");
        } else {
            System.out.println("Payment Error: Invalid transaction amount.");
        }
    }



    public String getPatientId() { return patientId; }
    public String getBloodGroup() { return bloodGroup; }
    public LocalDate getRegistrationDate() { return registrationDate; }
    public String getInsuranceId() { return insuranceId; }
}