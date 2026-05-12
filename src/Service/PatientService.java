package Service;

import Entity.Patient;
import Behaviour.Manageable;
import Behaviour.Searchable;
import Utils.Helper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Task 3.4 Implementation:
 * PatientService utilizes the Helper class for ID generation and null safety.
 */
public class PatientService implements Manageable<Patient>, Searchable<Patient> {

    private Scanner scanner = new Scanner(System.in);
    private static List<Patient> patients = new ArrayList<>();

    // --- Implementation of Manageable Interface ---

    @Override
    public void add(Patient patient) {

        if (Helper.isNotNull(patient) && patient.validate()) {
            patients.add(patient);
            System.out.println("Success: Patient record synchronized.");
        } else {
            System.out.println("Error: Failed to add patient. Data validation failed.");
        }
    }

    @Override
    public void remove(String patientId) {

        if (Helper.isNull(patientId)) return;

        boolean removed = patients.removeIf(p -> p.getPatientId().equals(patientId));
        if (removed) {
            System.out.println("Success: Patient record removed.");
        } else {
            System.out.println("Error: Patient ID not found.");
        }
    }

    @Override
    public List<Patient> getAll() {
        return new ArrayList<>(patients);
    }

    // --- Implementation of Searchable Interface ---

    @Override
    public Patient searchById(String patientId) {
        if (Helper.isNull(patientId)) return null;

        return patients.stream()
                .filter(p -> p.getPatientId().equals(patientId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Patient> search(String keyword) {
        if (Helper.isNull(keyword)) return new ArrayList<>();

        String key = keyword.toLowerCase();
        return patients.stream()
                .filter(p -> p.getFirstName().toLowerCase().contains(key) ||
                        p.getLastName().toLowerCase().contains(key) ||
                        p.getPatientId().toLowerCase().contains(key))
                .collect(Collectors.toList());
    }

    // --- Refactored Console Logic ---

    public void addPatientFromConsole() {
        try {
            System.out.println("\n--- Register New Patient ---");
            System.out.print("Enter First Name: ");
            String fName = scanner.nextLine();
            System.out.print("Enter Last Name: ");
            String lName = scanner.nextLine();


            String perId = Helper.generateId("PER");
            String patId = Helper.generateId("PAT");

            Patient p = new Patient();
            p.setFirstName(fName);
            p.setLastName(lName);
            p.setId(perId);      // Person ID
            p.setPatientId(patId); // Patient ID
            p.setRegistrationDate(LocalDate.now());

            add(p);
            System.out.println("System: Patient registered with ID: " + patId);

        } catch (Exception e) {
            System.out.println("Input Error: " + e.getMessage());
        }
    }


    public void addPatient(String firstName, String lastName, String phone) {
        Patient p = new Patient();
        p.setFirstName(firstName);
        p.setLastName(lastName);
        p.setPhoneNumber(phone);
        p.setPatientId(Helper.generateId("PAT"));
        p.setRegistrationDate(LocalDate.now());
        add(p);
    }

    // --- Display Methods ---

    public void displayPatients() {
        if (patients.isEmpty()) {
            System.out.println("Registry Status: No patients registered.");
        } else {
            System.out.println("\n--- Registered Patients List ---");
            for (Patient p : patients) {
                p.displaySummary();
            }
        }
    }
}