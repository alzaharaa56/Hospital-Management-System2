package Entity;

import Behaviour.Displayable;
import Behaviour.Editable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Task 3.2: Appointment implements Displayable and Editable.
 * This class represents a medical appointment linking a Patient and a Doctor.
 */
public class Appointment implements Displayable, Editable {
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private LocalDate appointmentDate;
    private String appointmentTime;
    private String status; // Possible values: Scheduled, Cancelled, Completed, Rescheduled
    private String reason;
    private String notes = "";

    // Full Constructor
    public Appointment(String standardBooking, String appointmentId, String patientId, String doctorId, LocalDate appointmentDate,
                       String appointmentTime, String reason, String status) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.reason = reason;
        this.status = status;
        this.notes = "";
    }

    // Default Constructor
    public Appointment(String appId, String patientId, String doctorId, LocalDate date, String time, String consultation, String scheduled) {}

    // --- Task 3.1: Method Overloading (Add Clinical Notes) ---

    // Add a basic note
    public void addNotes(String notes) {
        this.notes += "\n- " + notes;
    }

    // Add a note with the author's name
    public void addNotes(String notes, String addedBy) {
        this.notes += "\n- [" + addedBy + "]: " + notes;
    }

    // Add a detailed note with author and precise timestamp
    public void addNotes(String notes, String addedBy, LocalDateTime timestamp) {
        this.notes += "\n- [" + timestamp + "] " + addedBy + ": " + notes;
    }

    // --- Task 3.2: Implementation of Displayable ---

    @Override
    public void displayInfo() {
        System.out.println("\n--- Appointment Details ---");
        System.out.println("ID: " + appointmentId);
        System.out.println("Patient ID: " + patientId);
        System.out.println("Doctor ID: " + doctorId);
        System.out.println("Date & Time: " + appointmentDate + " at " + appointmentTime);
        System.out.println("Status: " + status);
        System.out.println("Reason: " + reason);
        System.out.println("Clinical Notes: " + (notes.isEmpty() ? "No notes available." : notes));
    }

    @Override
    public void displaySummary() {
        System.out.println("Appt ID: " + appointmentId + " | Date: " + appointmentDate + " | Status: " + status);
    }

    // --- Task 3.2: Implementation of Editable ---

    @Override
    public void edit(Object updatedData) {
        if (updatedData instanceof Appointment) {
            Appointment app = (Appointment) updatedData;
            this.appointmentDate = app.appointmentDate;
            this.appointmentTime = app.appointmentTime;
            this.status = app.status;
            System.out.println("Appointment record updated successfully.");
        }
    }

    @Override
    public boolean validate() {
        // Ensure the appointment date is not null and not in the past
        return appointmentDate != null && !appointmentDate.isBefore(LocalDate.now());
    }

    // --- Getters & Setters ---

    public String getAppointmentId() { return appointmentId; }
    public void setAppointmentId(String appointmentId) { this.appointmentId = appointmentId; }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }

    public LocalDate getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDate appointmentDate) { this.appointmentDate = appointmentDate; }

    public String getAppointmentTime() { return appointmentTime; }
    public void setAppointmentTime(String appointmentTime) { this.appointmentTime = appointmentTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}