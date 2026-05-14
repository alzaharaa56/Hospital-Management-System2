package Entity;

import Behavior.Displayable;
import Utils.HelperUtils;

import java.time.LocalDate;

public class Appointment implements Displayable {

    private String appointmentId;
    private String patientId;
    private String doctorId;
    private LocalDate appointmentDate;
    private String appointmentTime;
    private String status;
    private String reason;
    private String notes;



    public Appointment(String appointmentId, String patientId, String doctorId,
                       LocalDate appointmentDate, String appointmentTime,
                       String status, String reason, String notes) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.reason = reason;
        this.notes = notes;
    }

    public Appointment() {
        this.status = "Scheduled";
    }



    public String getAppointmentId() { return appointmentId; }
    public String getPatientId() { return patientId; }
    public String getDoctorId() { return doctorId; }
    public LocalDate getAppointmentDate() { return appointmentDate; }
    public String getAppointmentTime() { return appointmentTime; }
    public String getStatus() { return status; }



    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public void setAppointmentDate(LocalDate date) {
        this.appointmentDate = date;
    }

    public void setAppointmentTime(String time) {
        this.appointmentTime = time;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    @Override
    public void displayInfo() {
        System.out.println("\n--- Appointment Details ---");
        System.out.println("Appointment ID: " + appointmentId);
        System.out.println("Patient ID    : " + patientId);
        System.out.println("Doctor ID     : " + doctorId);
        System.out.println("Date & Time   : " + appointmentDate + " at " + appointmentTime);
        System.out.println("Status        : " + status);
        System.out.println("Reason        : " + (reason != null ? reason : "General Checkup"));
        System.out.println("Notes         : " + (notes != null ? notes : "No notes"));
    }

    @Override
    public void displaySummary() {

        System.out.printf("Appt: %-10s | Date: %-12s | Time: %-8s | Status: %-12s\n",
                appointmentId, appointmentDate, appointmentTime, status);
    }



    public void reschedule(LocalDate newDate, String newTime) {
        if (!HelperUtils.isValidDate(newDate) || HelperUtils.isPastDate(newDate)) {
            System.out.println("Error: Invalid or past date provided.");
            return;
        }
        this.appointmentDate = newDate;
        this.appointmentTime = newTime;
        this.status = "Rescheduled";
        System.out.println("Success: Appointment rescheduled.");
    }

    public void cancel() {
        this.status = "Cancelled";
        System.out.println("Appointment cancelled.");
    }



    public void addNotes(String notes) {
        if (HelperUtils.isValidString(notes)) {
            this.notes = notes;
        }
    }

    public void addNotes(String notes, String addedBy) {
        if (HelperUtils.isValidString(notes) && HelperUtils.isValidString(addedBy)) {
            this.notes = notes + " (Added By: " + addedBy + ")";
        }
    }
}