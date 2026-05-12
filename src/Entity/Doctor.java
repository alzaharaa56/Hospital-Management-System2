package Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person{

    private String doctorId;
    private String specialization;
    private String qualification;
    private int experienceYears;
    private String departmentId;
    private double consultationFee;
    private List<String> availableSlots;
    private List<String> assignedPatients;

    public Doctor(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender, String phoneNumber, String email, String address, String doctorId, String specialization, String qualification, int experienceYears, String departmentId, double consultationFee, List<String> availableSlots, List<String> assignedPatients) {
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

    public Doctor(String id, int experienceYears) {

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

    public List<String> getAssignedPatients() {
        return assignedPatients;
    }

    public void setAssignedPatients(List<String> assignedPatients) {
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

    // Method to assign a patient
    public void assignPatient(String patientId , String doctorId) {
        assignedPatients.add(patientId);
        System.out.println(patientId + " assigned to Dr. " + doctorId);
    }

    // Method to remove a patient
    public void removePatient(String patientId,String doctorId) {
        if (assignedPatients.remove(patientId)) {
            System.out.println(doctorId + " removed from Dr. " + doctorId);
        } else {
            System.out.println("Patient not found.");
        }
    }

    // Method to update availability
    public void updateAvailability(List<String> newSlots) {
        availableSlots = newSlots;
        System.out.println("Availability updated.");
    }

    public Doctor(){

    }
}