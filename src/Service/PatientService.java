package Service;

import Entity.Appointment;
import Entity.MedicalRecord;
import Entity.Patient;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatientService {
    private Scanner scanner = new Scanner(System.in);

    private static List<Patient> patients = new ArrayList<>();


    public void addPatientFromConsole() {
        try {
            System.out.println("\n--- Register New Patient ---");
            System.out.print("Enter Person ID (National ID): ");
            String id = scanner.nextLine();
            System.out.print("Enter First Name: ");
            String fName = scanner.nextLine();
            System.out.print("Enter Last Name: ");
            String lName = scanner.nextLine();
            System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
            LocalDate dob = LocalDate.parse(scanner.nextLine());
            System.out.print("Enter Gender: ");
            String gender = scanner.nextLine();
            System.out.print("Enter Phone Number: ");
            String phone = scanner.nextLine();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            System.out.print("Enter Address: ");
            String address = scanner.nextLine();

            System.out.print("Enter Hospital Patient ID: ");
            String patientID = scanner.nextLine();
            System.out.print("Enter Blood Group: ");
            String bloodGroup = scanner.nextLine();
            System.out.print("Enter Emergency Contact: ");
            String emergencyContact = scanner.nextLine();
            System.out.print("Enter Insurance ID: ");
            String insuranceId = scanner.nextLine();


            List<String> allergies = new ArrayList<>();
            System.out.println("Enter allergies (type 'done' to finish):");
            while (true) {
                String allergy = scanner.nextLine();
                if (allergy.equalsIgnoreCase("done")) break;
                allergies.add(allergy);
            }


            Patient patient = new Patient(id, fName, dob, lName, gender, phone, email, address,
                    patientID, bloodGroup, allergies, emergencyContact, LocalDate.now(),
                    new ArrayList<>(), insuranceId, new ArrayList<>());

            patients.add(patient);
            System.out.println("Patient registered successfully with ID: " + patientID);

        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format. Please use YYYY-MM-DD.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }


    public void addPatient(String firstName, String lastName, String phone) {
        Patient patient = new Patient();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setPhoneNumber(phone);
        patient.setRegistrationDate(LocalDate.now());
        patients.add(patient);
        System.out.println("Basic patient profile created for: " + firstName);
    }


    public void addPatient(String firstName, String lastName, String phone, String insuranceId) {
        Patient patient = new Patient();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setPhoneNumber(phone);
        patient.setInsuranceId(insuranceId);
        patient.setRegistrationDate(LocalDate.now());
        patients.add(patient);
        System.out.println("Insurance-linked patient profile created.");
    }


    public Patient getPatientById(String patientId) {
        for (Patient p : patients) {
            if (p.getPatientId() != null && p.getPatientId().equals(patientId)) {
                return p;
            }
        }
        return null;
    }


    public List<Patient> searchPatients(String keyword) {
        List<Patient> found = new ArrayList<>();
        for (Patient p : patients) {
            if (p.getFirstName().toLowerCase().contains(keyword.toLowerCase()) ||
                    p.getLastName().toLowerCase().contains(keyword.toLowerCase()) ||
                    (p.getPatientId() != null && p.getPatientId().contains(keyword)) ||
                    p.getPhoneNumber().contains(keyword)) {
                found.add(p);
            }
        }
        return found;
    }


    public void editPatient(String patientId) {
        Patient p = getPatientById(patientId);
        if (p != null) {
            System.out.print("Enter new Phone (Current: " + p.getPhoneNumber() + "): ");
            p.setPhoneNumber(scanner.nextLine());
            System.out.print("Enter new Email (Current: " + p.getEmail() + "): ");
            p.setEmail(scanner.nextLine());
            System.out.print("Enter new Address: ");
            p.setAddress(scanner.nextLine());
            System.out.println("Patient info updated successfully.");
        } else {
            System.out.println("Patient not found.");
        }
    }


    public void removePatient(String patientId) {
        boolean removed = patients.removeIf(p -> p.getPatientId() != null && p.getPatientId().equals(patientId));
        if (removed) {
            System.out.println("Patient removed successfully.");
        } else {
            System.out.println("Error: Patient ID not found.");
        }
    }


    public void displayAllPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients in the system.");
        } else {
            System.out.println("\n--- All Registered Patients ---");
            for (Patient p : patients) {
                p.displayInfo();
                System.out.println("--------------------------------");
            }
        }
    }
}