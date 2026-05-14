package Entity;

import Behavior.Displayable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person implements Displayable {

    private String doctorId;
    private String specialization;
    private String qualification;
    private int experienceYears;
    private String departmentId;
    private double consultationFee;
    private List<String> availableSlots;
    private List<Patient> assignedPatients;

    public Doctor(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender, String phoneNumber, String email, String address, String doctorId, String specialization, String qualification, int experienceYears, String departmentId, double consultationFee, List<String> availableSlots, List<Patient> assignedPatients) {
        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address);

        this.doctorId = doctorId;
        this.specialization = specialization;
        this.qualification = qualification;
        this.experienceYears = experienceYears;
        this.departmentId = departmentId;
        this.consultationFee = consultationFee;
        this.availableSlots = new ArrayList<>();
        this.assignedPatients = new ArrayList<>();
    }

    public Doctor() {
        this.availableSlots = new ArrayList<>();
        this.assignedPatients = new ArrayList<>();
    }


    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public double getConsultationFee() { return consultationFee; }
    public void setConsultationFee(double consultationFee) { this.consultationFee = consultationFee; }
    public List<Patient> getAssignedPatients() { return assignedPatients; }


    @Override
    public void displayInfo() {
        System.out.println("\n--- Detailed Doctor Information ---");
        System.out.println("Name: Dr. " + getFirstName() + " " + getLastName());
        System.out.println("Doctor ID: " + doctorId);
        System.out.println("Specialization: " + specialization);
        System.out.println("Qualification: " + qualification);
        System.out.println("Experience: " + experienceYears + " years");
        System.out.println("Consultation Fee: " + consultationFee + " OMR");
        System.out.println("Assigned Patients Count: " + assignedPatients.size());
    }

    @Override
    public void displaySummary() {
        System.out.printf("ID: %-10s | Name: Dr. %-15s | Specialization: %-15s\n",
                doctorId, getFirstName() + " " + getLastName(), specialization);
    }



    public void assignPatient(Patient patient) {
        if (HelperUtils.isNull(patient)) {
            System.out.println("Error: Cannot assign null patient.");
            return;
        }
        if (!assignedPatients.contains(patient)) {
            assignedPatients.add(patient);
            System.out.println("Success: Patient " + patient.getFirstName() + " assigned to Dr. " + getLastName());
        }
    }


    public void assignPatient(String patientId) {
        System.out.println("Assigning Patient ID: " + patientId + " to Dr. " + getLastName());

    }



    public void updateFee(double fee) {
        if (!HelperUtils.isNegative(fee)) {
            this.consultationFee = fee;
            System.out.println("Fee updated successfully.");
        }
    }

    public void addAvailability(String slot) {
        if (HelperUtils.isValidString(slot)) {
            availableSlots.add(slot);
            System.out.println("Slot added.");
        }
    }
}