
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

    public static List<Patient> getPatients() {
        return patients;
    }

    public static void setPatients(List<Patient> patients) {
        PatientService.patients = patients;
    }


    public Patient addPatient(){

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

        Patient patient = new Patient(id,patientFName,DOB,patientLName,gender,phone,email,address,patientID,bloodGroup,allergies,emergencyContact,DOR,medicalRecords,insuranceId,appointments);

        return patient;
    }

    public List<Patient> addPatients(){

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


    public void editPatient(String patientId){

        for(Patient patient: patients ){

            if(patient.getPatientId().equals(patientId)){

                System.out.println("Enter updated patient id :");
                patient.setId(scanner.nextLine());

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

                System.out.println("Enter updated patient id :");
                patient.setPatientId(scanner.nextLine());

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

            }
        }

    }


    public void removePatient(String patientId){

        patients.removeIf(b -> b.getPatientId() == patientId);
        System.out.println("patient removed successfully");

        System.out.println("patient not found");

    }


    public Patient getPatientById(String patientId){

        for(Patient patient: patients){
            if(patient.getPatientId().equals(patientId)){
                return patient;
            }

        }
        System.out.println("patient not found");
        return null;
    }


    public void displayAllPatients(){

        for(Patient patient: patients){
            patient.displayInfo();
        }

    }


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

    private static List<Patient> patient = new ArrayList<>();

    // ---------------- Overloaded addPatient methods ----------------
    public void addPatient(String firstName, String lastName, String phone) {
        Patient patient = new Patient(
                "GEN-" + (patients.size() + 1), firstName, lastName, null,
                "Unknown", phone, null, "Unknown",
                "P-" + (patients.size() + 1), "Unknown", new ArrayList<>(),
                "N/A", null, "N/A", String.valueOf(new ArrayList<>()), new ArrayList<>()
        );
        patients.add(patient);
    }

    public void addPatient(String firstName, String lastName, String phone, String bloodGroup, String email) {
        Patient patient = new Patient(
                "GEN-" + (patients.size() + 1), firstName, lastName, null,
                "Unknown", phone, email, "Unknown",
                "P-" + (patients.size() + 1), bloodGroup, new ArrayList<>(),
                "N/A", null, "N/A", new String(), new ArrayList<>()
        );
        patients.add(patient);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    // ---------------- Overloaded searchPatients methods ----------------
    public List<Patient> searchPatients(String keyword) {
        List<Patient> results = new ArrayList<>();
        for (Patient p : patients) {
            if (p.toString().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(p);
            }
        }
        return results;
    }

    public List<Patient> searchPatients(String firstName, String lastName) {
        List<Patient> results = new ArrayList<>();
        for (Patient p : patients) {
            if (p.getFirstName().equalsIgnoreCase(firstName) && p.getLastName().equalsIgnoreCase(lastName)) {
                results.add(p);
            }
        }
        return results;
    }

    // ---------------- Overloaded displayPatients methods ----------------
    public void displayPatients() {
        for (Patient p : patients) {
            p.displayInfo();
            System.out.println("-------------------");
        }
    }

    public void displayPatients(String filter) {
        for (Patient p : patients) {
            if (p.toString().toLowerCase().contains(filter.toLowerCase())) {
                p.displayInfo();
                System.out.println("-------------------");
            }
        }
    }

    public void displayPatients(int limit) {
        for (int i = 0; i < Math.min(limit, patients.size()); i++) {
            patients.get(i).displayInfo();
            System.out.println("-------------------");
        }
    }
}

