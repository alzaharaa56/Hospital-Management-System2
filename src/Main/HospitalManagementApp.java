package Main;

import Entity.*;
import Utils.Helper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HospitalManagementApp {

    private static List<Patient> patients = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            displayMainMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    patientManagementMenu();
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting System... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n===== HOSPITAL MANAGEMENT SYSTEM =====");
        System.out.println("1. Patient Management");
        System.out.println("0. Exit");
        System.out.print("Select an option: ");
    }

    private static void patientManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Patient Management Menu ---");
            System.out.println("1.1 Register New Patient (General)");
            System.out.println("1.2 Register InPatient");
            System.out.println("1.3 Register OutPatient");
            System.out.println("1.4 Register Emergency Patient");
            System.out.println("1.5 View All Patients");
            System.out.println("1.6 Search Patient");
            System.out.println("1.7 Update Patient Information");
            System.out.println("1.8 Remove Patient");
            System.out.println("1.9 View Patient Medical History");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1.1": registerPatient("General"); break;
                case "1.2": registerPatient("InPatient"); break;
                case "1.3": registerPatient("OutPatient"); break;
                case "1.4": registerPatient("Emergency"); break;
                case "1.5": viewAllPatients(); break;
                case "1.6": searchPatient(); break;
                case "1.7": updatePatient(); break;
                case "1.8": removePatient(); break;
                case "1.9": viewMedicalHistory(); break;
                case "0": back = true; break;
                default: System.out.println("Invalid Input.");
            }
        }
    }



    private static void registerPatient(String type) {
        System.out.print("Enter First Name: ");
        String fName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lName = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();


        String pId = Helper.generateId("PAT");
        LocalDate dob = LocalDate.of(1990, 1, 1); // مثال مبسط

        Patient newPatient;

        switch (type) {
            case "InPatient":
                newPatient = new InPatient(null, fName, dob, lName, "M", phone, "email@hosp.com", "Address", null, "O+", null, "INS123", LocalDate.now(), null, "Room 101", "Bed A", "DOC001", 200.0);
                break;
            case "OutPatient":
                newPatient = new OutPatient(null, fName, dob, lName, "M", phone, "email@hosp.com", "Address", null, "A-", null, "INS456", 0, null, "DOC002");
                break;
            case "Emergency":
                newPatient = new EmergencyPatient(null, fName, dob, lName, "M", phone, "email@hosp.com", "Address", null, "B+", null, "N/A", "Accident", "Ambulance", 1, true);
                break;
            default:
                newPatient = new Patient(null, fName, dob, lName, "M", phone, "email@hosp.com", "Address", null, "O+", null, "Private");
        }

        if (newPatient.validate()) {
            patients.add(newPatient);
            System.out.println("Success: Patient registered with ID: " + newPatient.getPatientId());
        } else {
            System.out.println("Error: Validation failed. Check input data.");
        }
    }

    private static void viewAllPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients found in the system.");
            return;
        }
        System.out.println("\n--- Registered Patients List ---");
        for (Patient p : patients) {
            p.displaySummary();
        }
    }

    private static void searchPatient() {
        System.out.print("Enter Patient ID to search: ");
        String id = scanner.nextLine();
        Patient found = findById(id);
        if (Helper.isNotNull(found)) {
            found.displayInfo();
        } else {
            System.out.println("Patient not found.");
        }
    }

    private static void updatePatient() {
        System.out.print("Enter Patient ID to update: ");
        String id = scanner.nextLine();
        Patient p = findById(id);
        if (Helper.isNotNull(p)) {
            System.out.print("Enter New Phone Number: ");
            String newPhone = scanner.nextLine();
            p.setPhoneNumber(newPhone);
            System.out.println("Information updated successfully.");
        } else {
            System.out.println("Patient not found.");
        }
    }

    private static void removePatient() {
        System.out.print("Enter Patient ID to remove: ");
        String id = scanner.nextLine();
        Patient p = findById(id);
        if (Helper.isNotNull(p)) {
            patients.remove(p);
            System.out.println("Patient removed successfully.");
        } else {
            System.out.println("Error: Patient not found.");
        }
    }

    private static void viewMedicalHistory() {
        System.out.print("Enter Patient ID to view history: ");
        String id = scanner.nextLine();
        Patient p = findById(id);
        if (Helper.isNotNull(p)) {
            System.out.println("Displaying Medical Records for: " + p.getFirstName());

            System.out.println("[No records linked yet]");
        } else {
            System.out.println("Patient not found.");
        }
    }


    private static Patient findById(String id) {
        for (Patient p : patients) {
            if (p.getPatientId().equalsIgnoreCase(id)) return p;
        }
        return null;
    }

    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }
}
