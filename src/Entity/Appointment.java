package Entity;

import Behavior.Displayable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Appointment implements Displayable {

    private String appointmentId;
    private String patientId;
    private String doctorId;
    private LocalDate appointmentDate;
    private String appointmentTime;
    private String status;
    private String reason;
    private String notes;

    public Appointment(String notes, String reason, String status, String appointmentTime, LocalDate appointmentDate, String doctorId, String patientId, String appointmentId) {
        this.notes = notes;
        this.reason = reason;
        this.status = status;
        this.appointmentTime = appointmentTime;
        this.appointmentDate = appointmentDate;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.appointmentId = appointmentId;
    }
    public Appointment(){


    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void displayInfo(){
        System.out.println("Appointment ID: " + appointmentId);
        System.out.println("Patient ID: " + patientId);
        System.out.println("Doctor ID: " + doctorId);
        System.out.println("Appointment Date: " + appointmentDate);
        System.out.println("Appointment Time: " + appointmentTime);
        System.out.println("Status: " + status);
        System.out.println("Reason: " + reason);
        System.out.println("Notes: " + notes);
    }

    @Override
    public void displaySummary() {

    }

    // reschedule() method
    public void reschedule(LocalDate newDate, String newTime) {

        if(!HelperUtils.isValidDate(newDate)){
            System.out.println("Invalid appointment date.");
            return;
        }

        // Check if date is in the past
        if (HelperUtils.isPastDate(newDate)) {
            System.out.println("Appointment date cannot be in the past.");
            return;
        }

        // Check if time is valid

        if(HelperUtils.isNull(newTime)){
            System.out.println("Invalid appointment time.");
            return;
        }

        this.appointmentDate = newDate;
        this.appointmentTime = newTime;
        this.status = "Rescheduled";

        System.out.println("Appointment rescheduled successfully.");
    }

    // cancel() method
    public void cancel() {

        this.status = "Cancelled";

        System.out.println("Appointment cancelled.");
    }

    // Complete Appointment
    public void complete() {
        this.status = "Completed";

        System.out.println("Appointment marked as completed.");
    }

    // overloaded addNotes(String notes)
    public void addNotes(String notes){

        // Check if notes are empty
        if(HelperUtils.isNull(notes)){
            System.out.println("Notes cannot be empty.");
            return;
        }

        // Add notes
        this.notes = notes;

        System.out.println("Notes added successfully.");

    }

    // overloaded addNotes(String notes, String addedBy)

    public void addNotes(String notes, String addedBy){

        // Check if notes are empty
        if(!HelperUtils.isValidString(notes)){
            System.out.println("Notes cannot be empty.");
            return;
        }

        // Check if addedBy is empty
        if (!HelperUtils.isValidString(addedBy)) {
            System.out.println("AddedBy field cannot be empty.");
            return;
        }

        // Add notes
        this.notes = notes + " , Added By:" + addedBy;

        System.out.println("Notes added successfully ");

    }

    // overloaded addNotes(String notes, String addedBy, LocalDateTime timestamp)

    public void addNotes(String notes, String addedBy, LocalDateTime timestamp){

        // Check if notes are empty
        if(!HelperUtils.isValidString(notes)){
            System.out.println("Notes cannot be empty.");
            return;
        }


        // Check if addedBy is empty
        if (!HelperUtils.isValidString(addedBy)) {
            System.out.println("AddedBy field cannot be empty.");
            return;
        }

        // Validate timestamp
        if (!HelperUtils.isValidDate(timestamp.toLocalDate())) {
            System.out.println("Timestamp cannot be null.");
            return;
        }

        // Store formatted note
        this.notes =
                "Added By: " + addedBy +
                        "\nTimestamp: " + timestamp +
                        "\nNote: " + notes;

        System.out.println("Notes added successfully ");

    }

}