package Entity;

import Behavior.Displayable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Patient extends Person implements Displayable {

    private String patientId;
    private String bloodGroup;
    private List<String> allergies;
    private String emergencyContact;
    private LocalDate registrationDate;
    private String insuranceId;
    private List<MedicalRecord> medicalRecords;
    private List<Appointment> appointments;


    public Patient(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender, String phoneNumber, String email, String address, String patientId, String bloodGroup, List<String> allergies, String emergencyContact, LocalDate registrationDate, List<MedicalRecord> medicalRecords, String insuranceId, List<Appointment> appointments) {

        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address);

        this.patientId = patientId;
        this.bloodGroup = bloodGroup;
        this.allergies = (allergies != null) ? new ArrayList<>(allergies) : new ArrayList<>();
        this.emergencyContact = emergencyContact;
        this.registrationDate = registrationDate;
        this.medicalRecords = (medicalRecords != null) ? new ArrayList<>(medicalRecords) : new ArrayList<>();
        this.insuranceId = insuranceId;
        this.appointments = (appointments != null) ? new ArrayList<>(appointments) : new ArrayList<>();
    }


    public Patient() {
        super();
        this.allergies = new ArrayList<>();
        this.medicalRecords = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }


    public Patient(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender, String phoneNumber, String email, String address, String patientId) {
        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address);
        this.patientId = patientId;
        this.registrationDate = LocalDate.now();
        this.allergies = new ArrayList<>();
        this.medicalRecords = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }



    @Override
    public void displayInfo() {
        System.out.println("\n--- Detailed Patient Information ---");
        super.displayInfo();
        System.out.println("Patient ID: " + patientId);
        System.out.println("Blood Group: " + bloodGroup);
        System.out.println("Allergies: " + (allergies.isEmpty() ? "None" : allergies));
        System.out.println("Emergency Contact: " + emergencyContact);
        System.out.println("Registration Date: " + registrationDate);
        System.out.println("Insurance ID: " + insuranceId);
        System.out.println("Medical Records Count: " + medicalRecords.size());
        System.out.println("Appointments Count: " + appointments.size());
    }

    @Override
    public void displaySummary() {
        System.out.printf("ID: %-10s | Name: %-20s | Phone: %-15s\n",
                patientId, getFirstName() + " " + getLastName(), getPhoneNumber());
    }



    public void addMedicalRecord(MedicalRecord record) {
        if (HelperUtils.isNull(record)) {
            System.out.println("Invalid medical record.");
            return;
        }
        medicalRecords.add(record);
        System.out.println("Medical record added successfully.");
    }

    public void addAppointment(Appointment appointment) {
        if (HelperUtils.isNull(appointment)) {
            System.out.println("Invalid appointment.");
            return;
        }
        if (appointments.contains(appointment)) {
            System.out.println("Appointment already exists.");
            return;
        }
        appointments.add(appointment);
        System.out.println("Appointment added successfully.");
    }



    public void updateContact(String phone) {
        if (HelperUtils.isValidString(phone, "\\d+")) {
            this.setPhoneNumber(phone);
            System.out.println("Phone updated successfully.");
        } else {
            System.out.println("Invalid phone format.");
        }
    }

    public void updateContact(String phone, String email) {
        updateContact(phone);
        if (HelperUtils.isValidString(email, "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            this.setEmail(email);
            System.out.println("Email updated successfully.");
        }
    }

    public void updateContact(String phone, String email, String address) {
        updateContact(phone, email);
        if (HelperUtils.isValidString(address)) {
            this.setAddress(address);
            System.out.println("Address updated successfully.");
        }
    }

    public String getPatientId() {
        return this.patientId;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
}