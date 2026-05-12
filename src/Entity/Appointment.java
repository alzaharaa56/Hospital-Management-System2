package Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Appointment {
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private LocalDate appointmentDate;
    private String appointmentTime;
    private String status; // Scheduled, Cancelled, Completed, Rescheduled
    private String reason;
    private String notes;


    public Appointment(String quickBooking, String reason, String status, String appointmentTime, LocalDate appointmentDate,
                       String doctorId, String patientId, String appointmentId) {
        this.reason = reason;
        this.status = status;
        this.appointmentTime = appointmentTime;
        this.appointmentDate = appointmentDate;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.appointmentId = appointmentId;
        this.notes = "";
    }

    public Appointment() {
    }


    public void addNotes(String notes) {
        this.notes += "\n- " + notes;
        System.out.println("Note added to appointment " + appointmentId);
    }

    public void addNotes(String notes, String addedBy) {
        this.notes += "\n- [" + addedBy + "]: " + notes;
        System.out.println("Note added by " + addedBy);
    }


    public void addNotes(String notes, String addedBy, LocalDateTime timestamp) {
        this.notes += "\n- [" + timestamp + "] " + addedBy + ": " + notes;
        System.out.println("Detailed clinical note recorded.");
    }



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
}