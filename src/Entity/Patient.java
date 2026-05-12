package Entity;

import Behaviour.Appointable;
import Behaviour.Billable;
import java.time.LocalDate;
import java.util.List;

public class Patient extends Person implements Appointable, Billable {
    private String patientId;
    private String bloodGroup;
    private LocalDate registrationDate;
    private String insuranceId;


    public Patient(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender,
                   String phoneNumber, String email, String address, String patientId,
                   String bloodGroup, LocalDate registrationDate, String insuranceId) {


        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address);

        this.patientId = patientId;
        this.bloodGroup = bloodGroup;
        this.registrationDate = registrationDate;
        this.insuranceId = insuranceId;
    }

    public Patient() {
        super();
    }

    public void setPatientId(String pId) { this.patientId = pId; }
    public String getPatientId() { return this.patientId; }

    public void setBloodGroup(String blood) { this.bloodGroup = blood; }
    public String getBloodGroup() { return this.bloodGroup; }

    public void setRegistrationDate(LocalDate now) { this.registrationDate = now; }
    public LocalDate getRegistrationDate() { return this.registrationDate; }


    @Override
    public void scheduleAppointment(Appointment a) {
        System.out.println("Appointment scheduled for Patient ID: " + patientId);
    }

    @Override public void cancelAppointment(String id) {
        System.out.println("Appointment " + id + " cancelled.");
    }

    @Override public void rescheduleAppointment(String id, LocalDate d) {
        System.out.println("Appointment " + id + " moved to " + d);
    }

    @Override public double calculateCharges() {
        return 100.0;
    }

    @Override public void generateBill() {
        System.out.println("Generating bill for: " + getFirstName());
    }

    @Override public void processPayment(double a) {
        System.out.println("Payment of " + a + " processed.");
    }
}