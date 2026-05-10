package Entity;

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
    private List<Patient> assignedPatients;

    public Doctor(String id, String firstName, String lastName, LocalDate dateOfBirth,
                  String gender, String phoneNumber, String email, String address,
                  String doctorId, String specialization, String qualification,
                  int experienceYears, String departmentId, double consultationFee) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address);
        this.doctorId = doctorId;
        this.specialization = specialization;
        this.qualification = qualification;
        this.experienceYears = experienceYears;
        this.departmentId = departmentId;
        this.consultationFee = consultationFee;
        this.availableSlots = new ArrayList<>();
        this.assignedPatients = new ArrayList<>();
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
    }

    public void assignPatient(Patient patient) {
        assignedPatients.add(patient);
    }

    public void removePatient(Patient patient) {
        assignedPatients.remove(patient);
    }

    public void updateAvailability(List<String> slots) {
        this.availableSlots = slots;
    }
}
