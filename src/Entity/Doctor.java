package Entity;

import Utils.Helper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person {

    private String doctorId;
    private String specialization;
    private String qualification;
    private int experienceYears;
    private String departmentId;
    private double consultationFee;
    private List<String> availableSlots;
    private List<String> assignedPatients;

    // Full Constructor
    public Doctor(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender,
                  String phoneNumber, String email, String address, String doctorId, String specialization,
                  String qualification, int experienceYears, String departmentId, double consultationFee,
                  List<String> availableSlots, List<String> assignedPatients) {

        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address);


        this.doctorId = Helper.isNotNull(doctorId) ? doctorId : Helper.generateId("DOC");


        setSpecialization(specialization);
        setQualification(qualification);
        setExperienceYears(experienceYears);
        setDepartmentId(departmentId);
        setConsultationFee(consultationFee);


        this.availableSlots = Helper.isNotNull(availableSlots) ? availableSlots : new ArrayList<>();
        this.assignedPatients = Helper.isNotNull(assignedPatients) ? assignedPatients : new ArrayList<>();
    }

    public Doctor() {
        super();
        this.doctorId = Helper.generateId("DOC");
        this.availableSlots = new ArrayList<>();
        this.assignedPatients = new ArrayList<>();
    }



    public void updateFee(double fee) {
        if (Helper.isPositive(fee)) {
            this.consultationFee = fee;
            System.out.println("Fee updated successfully to: $" + fee);
        } else {
            System.out.println("Error: Fee must be a positive value.");
        }
    }

    public void updateFee(double fee, String reason) {
        if (Helper.isPositive(fee) && Helper.isValidString(reason)) {
            this.consultationFee = fee;
            System.out.println("Fee updated to: $" + fee + ". Reason: " + reason);
        }
    }

    public void addAvailability(String slot) {
        if (Helper.isValidString(slot)) {
            this.availableSlots.add(slot);
            System.out.println("New slot added: " + slot);
        }
    }

    public void addAvailability(List<String> slots) {
        if (Helper.isNotNull(slots)) {
            this.availableSlots.addAll(slots);
            System.out.println("Multiple slots added to availability list.");
        }
    }

    public void assignPatient(String patientId) {
        if (Helper.isValidString(patientId)) {
            if (!assignedPatients.contains(patientId)) {
                assignedPatients.add(patientId);
                System.out.println("Patient " + patientId + " assigned to Dr. " + this.getLastName());
            }
        }
    }



    public void setDoctorId(String doctorId) {
        if (Helper.isValidString(doctorId)) {
            this.doctorId = doctorId;
        }
    }

    public void setSpecialization(String specialization) {
        if (Helper.isValidString(specialization)) {
            this.specialization = specialization;
        } else {
            this.specialization = "General Medicine";
        }
    }

    public void setQualification(String qualification) {
        if (Helper.isValidString(qualification)) {
            this.qualification = qualification;
        }
    }

    public void setExperienceYears(int experienceYears) {
        if (experienceYears >= 0) {
            this.experienceYears = experienceYears;
        }
    }

    public void setDepartmentId(String departmentId) {
        if (Helper.isValidString(departmentId)) {
            this.departmentId = departmentId;
        }
    }

    public void setConsultationFee(double consultationFee) {
        if (Helper.isPositive(consultationFee)) {
            this.consultationFee = consultationFee;
        }
    }

    // --- Display Methods ---

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Doctor ID       : " + doctorId);
        System.out.println("Specialization  : " + (Helper.isNotNull(specialization) ? specialization : "N/A"));
        System.out.println("Qualification   : " + (Helper.isNotNull(qualification) ? qualification : "N/A"));
        System.out.println("Experience      : " + experienceYears + " years");
        System.out.println("Department ID   : " + (Helper.isNotNull(departmentId) ? departmentId : "N/A"));
        System.out.println("Consultation Fee: $" + consultationFee);
        System.out.println("Available Slots : " + (availableSlots.isEmpty() ? "None" : availableSlots));
    }

    // --- Getters ---

    public String getDoctorId() { return doctorId; }
    public String getSpecialization() { return specialization; }
    public String getQualification() { return qualification; }
    public int getExperienceYears() { return experienceYears; }
    public String getDepartmentId() { return departmentId; }
    public double getConsultationFee() { return consultationFee; }
    public List<String> getAvailableSlots() { return availableSlots; }
    public List<String> getAssignedPatients() { return assignedPatients; }
}