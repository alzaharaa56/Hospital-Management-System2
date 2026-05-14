package Service;

import Behavior.Manageable;
import Behavior.Searchable;
import Entity.InPatient;
import Entity.Patient;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PatientService implements Manageable, Searchable {
    static List<Patient> patients = new ArrayList<>();


    @Override
    public void add(Object entity) {
        if (entity instanceof Patient p) {

            if (searchById(p.getPatientId()) == null) {
                patients.add(p);
                System.out.println("Success: Patient [" + p.getFirstName() + "] added.");
            } else {
                System.out.println("Error: Patient ID already exists.");
            }
        }
    }


    public void addPatient(String firstName, String lastName, String phone) {
        if (HelperUtils.isValidString(firstName) && HelperUtils.isValidString(phone)) {

            String newId = HelperUtils.generateId("PAT");
            Patient p = new Patient();
            p.setPatientId(newId);
            p.setFirstName(firstName);
            p.setLastName(lastName);
            p.setPhoneNumber(phone);
            p.setRegistrationDate(LocalDate.now());
            add(p);
        }
    }


    public void addPatient(String firstName, String lastName, String roomNumber, String bedNumber) {
        InPatient ip = new InPatient();
        ip.setPatientId(HelperUtils.generateId("INP"));
        ip.setFirstName(firstName);
        ip.setLastName(lastName);
        ip.setRoomNumber(roomNumber);
        ip.setBedNumber(bedNumber);
        ip.setAdmissionDate(LocalDate.now());
        add(ip);
    }

    @Override
    public void remove(String id) {
        boolean removed = patients.removeIf(p -> p.getPatientId().equalsIgnoreCase(id));
        if (removed) {
            System.out.println("Success: Patient " + id + " removed.");
        } else {
            System.out.println("Error: Patient " + id + " not found.");
        }
    }

    @Override
    public List<Object> getAll() {
        return new ArrayList<>(patients);
    }



    @Override
    public Object searchById(String id) {
        return patients.stream()
                .filter(p -> p.getPatientId() != null && p.getPatientId().equalsIgnoreCase(id))
                .findFirst().orElse(null);
    }

    public Patient getPatientById(String id) {
        return (Patient) searchById(id);
    }

    @Override
    public void search(String keyword) {
        System.out.println("\n--- Search Results for: '" + keyword + "' ---");
        List<Patient> results = patients.stream()
                .filter(p -> p.getFirstName().toLowerCase().contains(keyword.toLowerCase()) ||
                        p.getPatientId().equalsIgnoreCase(keyword))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            System.out.println("No matching patients found.");
        } else {
            results.forEach(Patient::displaySummary);
        }
    }


    public void displayAll() {
        System.out.println("\n===== ALL REGISTERED PATIENTS =====");
        if (patients.isEmpty()) {
            System.out.println("No records found.");
        } else {

            patients.forEach(Patient::displaySummary);
        }
    }


    public void displayAllPatients() {
        displayAll(); //
    }

    public void displayPatients(boolean onlyInPatients) {
        if (onlyInPatients) {
            System.out.println("\n--- List of In-Patients ---");
            patients.stream()
                    .filter(p -> p instanceof InPatient)
                    .forEach(Patient::displaySummary);
        } else {
            displayAll();
        }
    }



    public void printFullPatientReport() {
        System.out.println("\n" + "=".repeat(45));
        System.out.println("      COMPLETE PATIENT HISTORY REPORT      ");
        System.out.println("=".repeat(45));
        for (Patient p : patients) {
            p.displayInfo();
            System.out.println("-".repeat(45));
        }
        System.out.println("Total Records Processed: " + patients.size());
    }

}