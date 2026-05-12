package Entity;

import Behaviour.Displayable;
import Behaviour.Editable;
import Utils.Helper;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Appointment implements Displayable, Editable {
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private LocalDate appointmentDate;
    private String appointmentTime;
    private String status;
    private String reason;
    private String notes = "";


    public Appointment(String appointmentId, String patientId, String doctorId, LocalDate appointmentDate,
                       String appointmentTime, String reason, String status) {


        this.appointmentId = Helper.isNotNull(appointmentId) ? appointmentId : Helper.generateId("APT");


        setPatientId(patientId);
        setDoctorId(doctorId);
        setAppointmentDate(appointmentDate);
        setAppointmentTime(appointmentTime);
        setReason(reason);
        setStatus(status);
    }


    public Appointment() {
        this.appointmentId = Helper.generateId("APT");
        this.notes = "";
    }



    public void addNotes(String notes) {
        if (Helper.isNotNull(notes)) {
            this.notes += "\n- " + notes;
        }
    }

    public void addNotes(String notes, String addedBy) {
        if (Helper.isNotNull(notes) && Helper.isNotNull(addedBy)) {
            this.notes += "\n- [" + addedBy + "]: " + notes;
        }
    }

    public void addNotes(String notes, String addedBy, LocalDateTime timestamp) {
        if (Helper.isNotNull(notes) && Helper.isNotNull(addedBy) && Helper.isNotNull(timestamp)) {
            this.notes += "\n- [" + timestamp + "] " + addedBy + ": " + notes;
        }
    }



    @Override
    public void displayInfo() {
        System.out.println("\n--- Appointment Details ---");
        System.out.println("ID: " + appointmentId);
        System.out.println("Patient ID: " + (Helper.isNotNull(patientId) ? patientId : "N/A"));
        System.out.println("Doctor ID: " + (Helper.isNotNull(doctorId) ? doctorId : "N/A"));
        System.out.println("Date & Time: " + appointmentDate + " at " + appointmentTime);
        System.out.println("Status: " + (Helper.isNotNull(status) ? status : "N/A"));
        System.out.println("Reason: " + (Helper.isNotNull(reason) ? reason : "N/A"));
        System.out.println("Clinical Notes: " + (notes.isEmpty() ? "No notes available." : notes));
    }

    @Override
    public void displaySummary() {
        System.out.println("Appt ID: " + appointmentId + " | Date: " + appointmentDate + " | Status: " + status);
    }



    @Override
    public void edit(Object updatedData) {
        if (Helper.isNotNull(updatedData) && updatedData instanceof Appointment) {
            Appointment app = (Appointment) updatedData;
            setAppointmentDate(app.appointmentDate);
            setAppointmentTime(app.appointmentTime);
            setStatus(app.status);
            System.out.println("Appointment record updated successfully.");
        }
    }

    @Override
    public boolean validate() {

        return Helper.isNotNull(appointmentDate) &&
                (Helper.isFutureDate(appointmentDate) || Helper.isToday(appointmentDate)) &&
                Helper.isValidString(patientId) &&
                Helper.isValidString(doctorId);
    }



    public void setAppointmentId(String appointmentId) {
        if (Helper.isValidString(appointmentId)) {
            this.appointmentId = appointmentId;
        }
    }

    public void setPatientId(String patientId) {
        if (Helper.isValidString(patientId)) {
            this.patientId = patientId;
        }
    }

    public void setDoctorId(String doctorId) {
        if (Helper.isValidString(doctorId)) {
            this.doctorId = doctorId;
        }
    }

    public void setAppointmentDate(LocalDate appointmentDate) {

        if (Helper.isNotNull(appointmentDate) && (Helper.isFutureDate(appointmentDate) || Helper.isToday(appointmentDate))) {
            this.appointmentDate = appointmentDate;
        } else {
            System.out.println("Error: Appointment date cannot be in the past.");
        }
    }

    public void setAppointmentTime(String appointmentTime) {
        if (Helper.isValidString(appointmentTime)) {
            this.appointmentTime = appointmentTime;
        }
    }

    public void setStatus(String status) {
        if (Helper.isValidString(status)) {
            this.status = status;
        }
    }

    public void setReason(String reason) {
        if (Helper.isValidString(reason)) {
            this.reason = reason;
        }
    }

    public void setNotes(String notes) {
        if (Helper.isNotNull(notes)) {
            this.notes = notes;
        }
    }

    // --- Getters ---

    public String getAppointmentId() { return appointmentId; }
    public String getPatientId() { return patientId; }
    public String getDoctorId() { return doctorId; }
    public LocalDate getAppointmentDate() { return appointmentDate; }
    public String getAppointmentTime() { return appointmentTime; }
    public String getStatus() { return status; }
    public String getReason() { return reason; }
    public String getNotes() { return notes; }
}