package Entity;

import Behavior.Displayable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Level 2 Inheritance
public class Doctor extends Person implements Displayable {

    private String doctorId;
    private String specialization;
    private String qualification;
    private int experienceYears;
    private String departmentId;
    private double consultationFee;
    private List<String> availableSlots;
    private List<Patient> assignedPatients;

    // Constructor chaining to Person
    public Doctor(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender, String phoneNumber, String email, String address, String doctorId, String specialization, String qualification, int experienceYears, String departmentId, double consultationFee, List<String> availableSlots, List<Patient> assignedPatients) {

        // Calls Person constructor
        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address);

        this.doctorId = doctorId;
        this.specialization = specialization;
        this.qualification = qualification;
        this.experienceYears = experienceYears;
        this.departmentId = departmentId;
        this.consultationFee = consultationFee;
        this.availableSlots = new ArrayList<>();
        this.assignedPatients =new ArrayList<>();
    }

    public Doctor(){

    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }

    public List<String> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(List<String> availableSlots) {
        this.availableSlots = availableSlots;
    }

    public List<Patient> getAssignedPatients() {
        return assignedPatients;
    }

    public void setAssignedPatients(List<Patient> assignedPatients) {
        this.assignedPatients = assignedPatients;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();

        System.out.println("Doctor ID: " + doctorId);
        System.out.println("Specialization: " + specialization);
        System.out.println("Qualification: " + qualification);
        System.out.println("Experience: " + experienceYears + " years");
        System.out.println("Department ID: " + departmentId);
        System.out.println("Consultation Fee: $" + consultationFee);

        System.out.println("Available Slots: " + availableSlots);
        System.out.println("Assigned Patients: " + assignedPatients);
    }

    @Override
    public void displaySummary() {

    }

    // Method to assign a patient

    public void assignPatient(Patient patient) {

        // Validate patient using HelperUtils
        if (HelperUtils.isNull(patient)) {
            System.out.println("Invalid patient.");
            return;
        }

        // Check duplicate assignment
        if (assignedPatients.contains(patient)) {
            System.out.println("Patient already assigned.");
            return;
        }

        // Assign patient
        assignedPatients.add(patient);

        System.out.println("Patient assigned successfully: "
                + patient.getPatientId());

    }

    // Method to remove a patient
    public void removePatient(Patient patient) {

        // Check if patient not null
        if(HelperUtils.isNull(patient)){
            System.out.println("Invalid patient.");
            return;
        }

        if (assignedPatients.remove(patient)) {
            System.out.println(" patient removed successfully");
        } else {
            System.out.println("Patient not found.");
        }
    }

    // Method to update availability
    public void updateAvailability(List<String> newSlots) {

        //Check if new list isnull
        if(HelperUtils.isNull(newSlots)){
            System.out.println("Invalid list");
            return;
        }

        availableSlots = new ArrayList<>(newSlots);
        System.out.println("Availability updated successfully");
    }

    // overloaded updateFee(double fee)

    public void updateFee(double fee){

        // Validate fee amount
        if (HelperUtils.isNegative(fee)) {
            System.out.println("Fee cannot be negative.");
            return;
        }

        this.consultationFee = fee;

        System.out.println("Fee updated successfully");

    }

    // overloaded updateFee(double fee, String reason)
    public void updateFee(double fee, String reason){

        // Validate fee
        if (HelperUtils.isNegative(fee)) {
            System.out.println("Fee cannot be negative.");
            return;
        }

        // Validate reason
        if (!HelperUtils.isValidString(reason)) {
            System.out.println("Reason cannot be empty.");
            return;
        }

        this.consultationFee = fee;

        System.out.println("Fee updated successfully because of this reason : " + reason );

    }

    // overloaded addAvailability(String slot)
    public void  addAvailability(String slot){

        // Validate slot
        if (!HelperUtils.isValidString(slot)) {
            System.out.println("Availability slot cannot be empty.");
            return;
        }

        availableSlots.add(slot);

        System.out.println("Availability add successfully.");

    }

    // overloaded addAvailability(List<String> slots)
    public void addAvailability(List<String> slots){

        // Check if the list is empty
        if (HelperUtils.isNull(slots)) {
            System.out.println("No availability slots provided.");
            return;
        }

        // Add all slot to doctor's availability schedule
        availableSlots.addAll(slots);

    }

}