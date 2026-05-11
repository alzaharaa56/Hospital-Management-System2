package Service;

import Entity.Appointment;
import Entity.MedicalRecord;
import Entity.Patient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatientService {
    Scanner scanner = new Scanner(System.in);

    static List<Patient> patients = new ArrayList<>();
    List<MedicalRecord> medicalRecords = new ArrayList<>();
    List<Appointment> appointments = new ArrayList<>();


    //add new patient

    public Patient addPatient() {

        System.out.println("Enter patient id :");
        String id = scanner.nextLine();

        System.out.println("Enter patient first name :");
        String patientFName = scanner.nextLine();

        System.out.println("Enter patient last name :");
        String patientLName = scanner.nextLine();

        System.out.println("Enter patient DOB: ");
        String dateOfBirth = scanner.nextLine();
        LocalDate DOB = LocalDate.parse(dateOfBirth);

        System.out.println("Enter patient gender :");
        String gender = scanner.nextLine();

        System.out.println("Enter patient phone number :");
        String phone = scanner.nextLine();

        System.out.println("Enter patient email :");
        String email = scanner.nextLine();

        System.out.println("Enter patient address :");
        String address = scanner.nextLine();

        System.out.println("Enter patient id :");
        String patientID = scanner.nextLine();

        System.out.println("Enter patient blood Group :");
        String bloodGroup = scanner.nextLine();

        System.out.println("Enter patient emergency Contact :");
        String emergencyContact = scanner.nextLine();

        System.out.println("Enter registration Date :");
        String dateOfRegistration = scanner.nextLine();
        LocalDate DOR = LocalDate.parse(dateOfBirth);


        System.out.println("Enter patient insurance Id :");
        String insuranceId = scanner.nextLine();

        System.out.println("Enter patient allergies :");

        Boolean continueFlag = true;

        List<String> allergies = new ArrayList<>();

        while (continueFlag) {

            allergies.add(scanner.nextLine());
            System.out.println("Enter c to add more allergies , and q to exit");
            if (scanner.nextLine().equalsIgnoreCase("q")) {
                continueFlag = false;
            }
        }

        Patient patient = new Patient(id, patientFName, DOB, patientLName, gender, phone, email, address, patientID, bloodGroup, allergies, emergencyContact, DOR, medicalRecords, insuranceId, appointments);

        return patient;
    }

    public List<Patient> addPatients() {

        Boolean continueFlag = true;
        while (continueFlag) {

            patients.add(addPatient());
            System.out.println("Patient add successfully");

            System.out.println("Enter c to add more , and q to exit");
            if (scanner.nextLine().equalsIgnoreCase("q")) {
                continueFlag = false;
            }
        }
        return patients;

    }

    // Overload addPatient(String firstName, String lastName, String phone) - minimal info

    public void addPatient(String firstName, String lastName, String phone) {

        Patient patient = new Patient();

        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setPhoneNumber(phone);

        patients.add(patient);
        System.out.println("Patient add successfully");

    }

    // Overload addPatient(String firstName, String lastName, String phone, String bloodGroup, String email)

    public void addPatient(String firstName, String lastName, String phone, String bloodGroup, String email) {
        Patient patient = new Patient();

        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setPhoneNumber(phone);
        patient.setBloodGroup(bloodGroup);
        patient.setEmail(email);

        patients.add(patient);
        System.out.println("Patient add successfully");

    }

    // search Patients by any field

    public List<Patient> searchPatients(String keyword) {

        List<Patient> matchedPatients = new ArrayList<>();


        for (Patient patient : patients) {
            if (patient.getPatientId().toLowerCase().contains(keyword.toLowerCase())
                    || patient.getFirstName().toLowerCase().contains(keyword.toLowerCase())
                    || patient.getLastName().toLowerCase().contains(keyword.toLowerCase())
                    || patient.getPhoneNumber().contains(keyword)
                    || patient.getBloodGroup().toLowerCase().contains(keyword.toLowerCase())
                    || patient.getEmail().toLowerCase().contains(keyword.toLowerCase())) {

                matchedPatients.add(patient);

            }

        }
        return matchedPatients;
    }

    // search by name
    public void searchPatients(String firstName, String lastName) {

        List<Patient> matchedPatients = new ArrayList<>();
        for (Patient patient : patients) {


        }


    }






    //update existing patient
    public void editPatient(String patientId){

        for(Patient patient: patients ){

            if(patient.getPatientId().equals(patientId)){

                System.out.println("Enter updated patient first name :");
                patient.setFirstName(scanner.nextLine());

                System.out.println("Enter updated patient last name :");
                patient.setLastName(scanner.nextLine());

                System.out.println("Enter updated patient DOB: ");
                String dateOfBirth = scanner.nextLine();
                LocalDate DOB = LocalDate.parse(dateOfBirth);
                patient.setDateOfBirth(DOB);

                System.out.println("Enter updated patient gender :");
                patient.setGender(scanner.nextLine());

                System.out.println("Enter updated patient phone number :");
                patient.setPhoneNumber(scanner.nextLine());

                System.out.println("Enter updated patient email :");
                patient.setEmail(scanner.nextLine());

                System.out.println("Enter updated patient address :");
                patient.setAddress(scanner.nextLine());

                System.out.println("Enter updated patient blood Group :");
                patient.setBloodGroup(scanner.nextLine());

                System.out.println("Enter updated patient emergency Contact :");
                patient.setEmergencyContact(scanner.nextLine());

                System.out.println("Enter updated registration Date :");
                String dateOfRegistration = scanner.nextLine();
                LocalDate DOR = LocalDate.parse(dateOfBirth);
                patient.setRegistrationDate(DOR);


                System.out.println("Enter updated patient insurance Id :");
                patient.setInsuranceId(scanner.nextLine());

                System.out.println("Enter updated patient allergies :");

                Boolean continueFlag = true;

                List<String> allergies = new ArrayList<>();

                while (continueFlag) {

                    allergies.add(scanner.nextLine());
                    System.out.println("Enter c to add more allergies , and q to exit");
                    if (scanner.nextLine().equalsIgnoreCase("q")) {
                        continueFlag = false;
                    }
                }
                System.out.println("Patient updated successfully");


            }
        }

    }

    // remove patient by ID
    public void removePatient(String patientId){

        patients.removeIf(b -> b.getPatientId() == patientId);
        System.out.println("patient removed successfully");

        System.out.println("patient not found");

    }

    //retrieve patient
    public Patient getPatientById(String patientId){

        for(Patient patient: patients){
            if(patient.getPatientId().equals(patientId)){
                return patient;
            }

        }
        System.out.println("patient not found");
        return null;
    }

    //display all patients with formatted output
    public void displayAllPatients(){

        for(Patient patient: patients){
            patient.displayInfo();
        }

    }

    //search functionality
    public void searchPatientsByName(String name) {

        boolean found = false;


        for(Patient patient : patients){

            String fullName = patient.getFirstName() + " " + patient.getLastName();

            if(fullName.toLowerCase().contains(name.toLowerCase())){
                patient.displayInfo();
                found = true;
            }
        }

        if(!found){
            System.out.println("No patients found with this name");
        }

    }

}