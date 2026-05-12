package Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {

    private String patientId;
    private String bloodGroup;
    private List<String> allergies;
    private String emergencyContact;
    private LocalDate registrationDate;
    private String insuranceId;
    private List<MedicalRecord> medicalRecords;
    private List<Appointment> appointments;


    public Patient(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender,
                   String phoneNumber, String email, String address, String patientId, String bloodGroup,
                   List<String> allergies, String emergencyContact, LocalDate registrationDate,
                   List<MedicalRecord> medicalRecords, String insuranceId, List<Appointment> appointments) {


        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address);

        this.patientId = patientId;
        this.bloodGroup = bloodGroup;

        this.allergies = (allergies != null) ? allergies : new ArrayList<>();
        this.emergencyContact = emergencyContact;
        this.registrationDate = registrationDate;
        this.medicalRecords = (medicalRecords != null) ? medicalRecords : new ArrayList<>();
        this.insuranceId = insuranceId;
        this.appointments = (appointments != null) ? appointments : new ArrayList<>();
    }


    public Patient() {
        super();
    }


    public void updateContact(String phone) {
        this.setPhoneNumber(phone);
        System.out.println("Contact updated: Phone changed to " + phone);
    }


    public void updateContact(String phone, String email) {
        this.setPhoneNumber(phone);
        this.setEmail(email);
        System.out.println("Contact updated: Phone and Email updated.");
    }


    public void updateContact(String phone, String email, String address) {
        this.setPhoneNumber(phone);
        this.setEmail(email);
        this.setAddress(address);
        System.out.println("Contact updated: All details synchronized.");
    }



    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public List<String> getAllergies() { return allergies; }
    public void setAllergies(List<String> allergies) { this.allergies = allergies; }

    public String getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(String emergencyContact) { this.emergencyContact = emergencyContact; }

    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }

    public String getInsuranceId() { return insuranceId; }
    public void setInsuranceId(String insuranceId) { this.insuranceId = insuranceId; }

    public List<MedicalRecord> getMedicalRecords() { return medicalRecords; }
    public void setMedicalRecords(List<MedicalRecord> medicalRecords) { this.medicalRecords = medicalRecords; }

    public List<Appointment> getAppointments() { return appointments; }
    public void setAppointments(List<Appointment> appointments) { this.appointments = appointments; }



    @Override
    public void displayInfo() {

        super.displayInfo();
        System.out.println("Patient ID          : " + patientId);
        System.out.println("Blood Group         : " + bloodGroup);
        System.out.println("Allergies           : " + allergies);
        System.out.println("Emergency Contact   : " + emergencyContact);
        System.out.println("Registration Date   : " + registrationDate);
        System.out.println("Insurance ID        : " + insuranceId);
        System.out.println("Medical Records Count: " + medicalRecords.size());
        System.out.println("Appointments Count  : " + appointments.size());
    }

    public void addMedicalRecord(MedicalRecord record) {
        if (medicalRecords == null) medicalRecords = new ArrayList<>();
        medicalRecords.add(record);
        System.out.println("Medical record added for patient " + patientId);
    }

    public void addAppointment(Appointment appointment) {
        if (appointments == null) appointments = new ArrayList<>();
        appointments.add(appointment);
        System.out.println("New appointment booked.");
    }

    public void updateInsurance(String newInsuranceId) {
        this.insuranceId = newInsuranceId;
        System.out.println("Insurance information updated successfully.");
    }
}