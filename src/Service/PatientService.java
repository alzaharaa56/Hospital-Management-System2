package Service;

import Entity.Patient;
import Behaviour.Manageable; // Task 3.2
import Behaviour.Searchable; // Task 3.2
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Task 3.2: PatientService implements Manageable and Searchable interfaces.
 * This class handles the business logic for managing patient records.
 */
public class PatientService implements Manageable<Patient>, Searchable<Patient> {

    private Scanner scanner = new Scanner(System.in);
    private static List<Patient> patients = new ArrayList<>();

    // --- Implementation of Manageable Interface ---

    @Override
    public void add(Patient patient) {
        if (patient != null && patient.validate()) {
            patients.add(patient);
            System.out.println("Success: Patient record added to the database.");
        } else {
            System.out.println("Error: Failed to add patient. Data is invalid.");
        }
    }

    @Override
    public void remove(String patientId) {
        boolean removed = patients.removeIf(p -> p.getPatientId() != null && p.getPatientId().equals(patientId));
        if (removed) {
            System.out.println("Success: Patient with ID " + patientId + " has been removed.");
        } else {
            System.out.println("Error: Patient ID " + patientId + " not found.");
        }
    }

    @Override
    public List<Patient> getAll() {
        return new ArrayList<>(patients);
    }

    // --- Implementation of Searchable Interface ---

    @Override
    public Patient searchById(String patientId) {
        for (Patient p : patients) {
            if (p.getPatientId() != null && p.getPatientId().equals(patientId)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Patient> search(String keyword) {
        String key = keyword.toLowerCase();
        return patients.stream()
                .filter(p -> p.getFirstName().toLowerCase().contains(key) ||
                        p.getLastName().toLowerCase().contains(key) ||
                        (p.getPatientId() != null && p.getPatientId().contains(keyword)))
                .collect(Collectors.toList());
    }

    // --- Overloaded Methods (From your original code) ---

    public void addPatientFromConsole() {
        try {
            System.out.println("\n--- Register New Patient ---");
            System.out.print("Enter First Name: ");
            String fName = scanner.nextLine();
            System.out.print("Enter Last Name: ");
            String lName = scanner.nextLine();
            System.out.print("Enter Patient ID: ");
            String pId = scanner.nextLine();

            Patient p = new Patient();
            p.setFirstName(fName);
            p.setLastName(lName);
            p.setPatientId(pId);
            p.setRegistrationDate(LocalDate.now());

            add(p); // Calls the interface 'add' method

        } catch (Exception e) {
            System.out.println("Error during console registration: " + e.getMessage());
        }
    }

    // Overloaded add methods to maintain your original functionality
    public void addPatient(String firstName, String lastName, String phone) {
        Patient p = new Patient();
        p.setFirstName(firstName);
        p.setLastName(lastName);
        p.setPhoneNumber(phone);
        p.setRegistrationDate(LocalDate.now());
        add(p);
    }

    // --- Display Methods ---

    public void displayPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients currently registered.");
        } else {
            System.out.println("\n--- Registered Patients List ---");
            for (Patient p : patients) {
                p.displaySummary(); // Using the interface method we added to Patient
            }
        }
    }

    public void displayDetailedPatients() {
        for (Patient p : patients) {
            p.displayInfo();
            System.out.println("--------------------------------");
        }
    }
}