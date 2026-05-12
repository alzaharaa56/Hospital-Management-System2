package Entity;

import Behaviour.Appointable;
import Behaviour.Billable;
import java.time.LocalDate;
import java.util.List;

public class Patient extends Person implements Appointable, Billable {
    private String patientId;
    private String bloodGroup;
    private LocalDate registrationDate;

    public Patient(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender, String phoneNumber, String email, String address, String patientId, String bloodGroup, List<String> allergies, String emergencyContact, LocalDate registrationDate, List<MedicalRecord> medicalRecords, String insuranceId, List<Appointment> appointments) {


    }

    public Patient() {

    }


    public void setPatientId(String pId) {
        this.patientId = pId;
    }

    public void setBloodGroup(String blood) {
        this.bloodGroup = blood;
    }

    public void setRegistrationDate(LocalDate now) {
        this.registrationDate = now;
    }


    public String getPatientId() {
        return this.patientId;
    }

    public String getBloodGroup() {
        return this.bloodGroup;
    }


    @Override public void scheduleAppointment(Appointment a) { }
    @Override public void cancelAppointment(String id) { }
    @Override public void rescheduleAppointment(String id, LocalDate d) { }
    @Override public double calculateCharges() { return 0.0; }
    @Override public void generateBill() { }
    @Override public void processPayment(double a) { }
}