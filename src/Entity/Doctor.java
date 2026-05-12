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

    public Doctor(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender,
                  String phoneNumber, String email, String address, String doctorId, String specialization,
                  String qualification, int experienceYears, String departmentId, double consultationFee,
                  List<String> availableSlots, List<String> assignedPatients) {

        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address);

        this.doctorId = doctorId;
        this.specialization = specialization;
        this.qualification = qualification;
        this.experienceYears = experienceYears;
        this.departmentId = departmentId;
        this.consultationFee = consultationFee;
        this.availableSlots = (availableSlots != null) ? availableSlots : new ArrayList<>();
        this.assignedPatients = (assignedPatients != null) ? assignedPatients : new ArrayList<>();
    }

    public Doctor() {
        super();
    }

    public void updateFee(double fee) {
        this.consultationFee = fee;
        System.out.println("Fee updated successfully to: $" + fee);
    }

    public void updateFee(double fee, String reason) {
        this.consultationFee = fee;
        System.out.println("Fee updated to: $" + fee + ". Reason: " + reason);
    }

    public void addAvailability(String slot) {
        if (this.availableSlots == null) this.availableSlots = new ArrayList<>();
        this.availableSlots.add(slot);
        System.out.println("New slot added: " + slot);
    }

    public void addAvailability(List<String> slots) {
        if (this.availableSlots == null) this.availableSlots = new ArrayList<>();
        this.availableSlots.addAll(slots);
        System.out.println("Multiple slots added to availability list.");
    }

    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public double getConsultationFee() { return consultationFee; }
    public void setConsultationFee(double consultationFee) { this.consultationFee = consultationFee; }

    public List<String> getAvailableSlots() { return availableSlots; }
    public List<String> getAssignedPatients() { return assignedPatients; }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Doctor ID: " + doctorId);
        System.out.println("Specialization: " + specialization);
        System.out.println("Experience: " + experienceYears + " years");
        System.out.println("Consultation Fee: $" + consultationFee);
        System.out.println("Available Slots: " + availableSlots);
    }

    public void assignPatient(String patientId) {
        if (Helper.isValidString(patientId)) {
            assignedPatients.add(patientId);
            System.out.println("Patient " + patientId + " assigned to Dr. " + this.getLastName());
        }
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
}