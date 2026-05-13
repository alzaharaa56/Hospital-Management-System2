package Entity;

import Behavior.Displayable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Level 2 Inheritance
public class Patient extends Person implements Displayable {

    private String patientId;
    private String bloodGroup;
    private List<String> allergies;
    private String emergencyContact;
    private LocalDate registrationDate;
    private String insuranceId;
    private List<MedicalRecord> medicalRecords;
    private List <Appointment>appointments;

    // Constructor chaining to Person
    public Patient(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender, String phoneNumber, String email, String address, String patientId, String bloodGroup, List<String> allergies, String emergencyContact, LocalDate registrationDate, List<MedicalRecord> medicalRecords, String insuranceId, List<Appointment> appointments) {

        // Calls Person constructor
        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address);

        this.patientId = patientId;
        this.bloodGroup = bloodGroup;
        this.allergies = new ArrayList<>();
        this.emergencyContact = emergencyContact;
        this.registrationDate = registrationDate;
        this.medicalRecords = new ArrayList<>();
        this.insuranceId = insuranceId;
        this.appointments = new ArrayList<>();
    }

    public Patient(){

    }

    public Patient(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender, String phoneNumber, String email, String address, String patientId) {



    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("patient Id : "+ patientId);
        System.out.println("patient blood Group : "+ bloodGroup);
        System.out.println("patient allergies : "+ allergies);
        System.out.println("patient emergency Contact : "+ emergencyContact);
        System.out.println("patient registration Date : "+ registrationDate);
        System.out.println("patient insuranceId : "+ insuranceId);
        System.out.println("patient medical Records : "+ medicalRecords);
        System.out.println("patient appointments : "+ appointments);

    }

    @Override
    public void displaySummary() {

    }

    // Add medical record
    public void addMedicalRecord(MedicalRecord record){

        // Validate record
        if (HelperUtils.isNull(record)) {
            System.out.println("Invalid medical record.");
            return;
        }

        // Add record
        medicalRecords.add(record);
        System.out.println("Medical record added successfully.");
    }

    // Add appointment
    public void addAppointment(Appointment appointment) {

        // Validate appointment
        if (HelperUtils.isNull(appointment)) {
            System.out.println("Invalid appointment.");
            return;
        }

        //  prevent duplicate appointments
        if (appointments.contains(appointment)) {
            System.out.println("Appointment already exists.");
            return;
        }

        // Add appointment
        appointments.add(appointment);

        System.out.println("Appointment added successfully.");
    }

    // Update insurance
    public void updateInsurance(String newInsuranceId) {

        // Validate newInsuranceId
        if (!HelperUtils.isValidString(newInsuranceId)) {
            System.out.println("Insurance ID cannot be empty.");
            return;
        }

        this.insuranceId = newInsuranceId;

        System.out.println("Insurance updated successfully.");
    }


    //overloaded updateContact(String phone)
    public void updateContact(String phone){

        // Validate phone number
        if (!HelperUtils.isValidString(phone)) {
            System.out.println("Phone number cannot be empty.");
            return;
        }

        // Validate numeric format using HelperUtils regex method
        if (!HelperUtils.isValidString(phone, "\\d+")) {
            System.out.println("Invalid phone number format. Only digits are allowed.");
            return;
        }

        // Update phone
        this.setPhoneNumber(phone);

        System.out.println("Contact phone number is updated successfully");

    }

    //overloaded updateContact(String phone, String email)
    public void updateContact(String phone, String email){

        // Validate phone number
        if (!HelperUtils.isValidString(phone)) {
            System.out.println("Phone number cannot be empty.");
            return;
        }

        // Validate numeric format using HelperUtils regex method
        if (!HelperUtils.isValidString(phone, "\\d+")) {
            System.out.println("Invalid phone number format. Only digits are allowed.");
            return;
        }


        // validate email
        if (!HelperUtils.isValidString(email)) {
            System.out.println("Email cannot be empty.");
            return;
        }

        // Validate email using HelperUtils regex
        if (!HelperUtils.isValidString(email, "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            System.out.println("Invalid email format.");
            return;
        }

        this.setPhoneNumber(phone);
        this.setEmail(email);

        System.out.println("Contact phone number and email  updated successfully");

    }

    //overloaded updateContact(String phone, String email)
    public void updateContact(String phone, String email, String address){

        // Validate phone number
        if (!HelperUtils.isValidString(phone)) {
            System.out.println("Phone number cannot be empty.");
            return;
        }

        // Validate numeric format using HelperUtils regex method
        if (!HelperUtils.isValidString(phone, "\\d+")) {
            System.out.println("Invalid phone number format. Only digits are allowed.");
            return;
        }


        // validate email
        if (!HelperUtils.isValidString(email)) {
            System.out.println("Email cannot be empty.");
            return;
        }

        // Validate email using HelperUtils regex
        if (!HelperUtils.isValidString(email, "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            System.out.println("Invalid email format.");
            return;
        }

        // Validate address
        if (!HelperUtils.isValidString(address)) {
            System.out.println("Address cannot be empty.");
            return;
        }

        this.setPhoneNumber(phone);
        this.setEmail(email);
        this.setAddress(address);

        System.out.println("Contact details (phone, email, address) updated successfully.");

    }

}