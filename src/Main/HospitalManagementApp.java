package Main;

import Entity.*;
import Service.PatientService;
import Service.DoctorService;
import Utils.HelperUtils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class HospitalManagementApp {
    // Core Services and Utilities
    private static final Scanner scanner = new Scanner(System.in);
    private static final PatientService patientService = new PatientService();
    private static final DoctorService doctorService = new DoctorService();

    public static void main(String[] args) {
        // Task 3.7: Populate sample data on startup
        populateInitialData();

        System.out.println("Welcome to the Smart Hospital Management System");

        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getIntInput("Select an option: ");

            switch (choice) {
                case 1 -> patientManagementMenu();
                case 2 -> doctorManagementMenu();
                case 3 -> System.out.println("\n[Info] Appointment module is under maintenance.");
                case 4 -> generateFullSystemReport();
                case 0 -> {
                    System.out.println("Exiting the system... Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid selection. Try again.");
            }
        }
    }

    // --- Menu Structures ---

    private static void displayMainMenu() {
        System.out.println("\n========================================");
        System.out.println("      HOSPITAL ADMINISTRATION MENU      ");
        System.out.println("========================================");
        System.out.println("1. Patient Management");
        System.out.println("2. Doctor Management");
        System.out.println("3. Appointment Management");
        System.out.println("4. System Reports (Full Details)");
        System.out.println("0. Exit");
        System.out.println("----------------------------------------");
    }

    private static void patientManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Patient Operations ---");
            System.out.println("1. Quick Register (Basic Info)");
            System.out.println("2. Emergency Register (Full Info)");
            System.out.println("3. Search Patient (ID or Name)");
            System.out.println("4. Delete Patient Record");
            System.out.println("5. Back to Main Menu");

            int choice = getIntInput("Choice: ");
            switch (choice) {
                case 1 -> registerQuickPatient();
                case 2 -> registerEmergency();
                case 3 -> patientService.search(getStringInput("Search Keyword: "));
                case 4 -> patientService.remove(getStringInput("Patient ID to remove: "));
                case 5 -> back = true;
            }
        }
    }

    private static void doctorManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Doctor Operations ---");
            System.out.println("1. Register Doctor");
            System.out.println("2. Search for Doctor");
            System.out.println("3. Display All Staff");
            System.out.println("4. Back to Main Menu");

            int choice = getIntInput("Choice: ");
            switch (choice) {
                case 1 -> {
                    String fn = getStringInput("First Name: ");
                    String ln = getStringInput("Last Name: ");
                    String sp = getStringInput("Specialization: ");
                    doctorService.add(fn, ln, sp);
                }
                case 2 -> doctorService.search(getStringInput("Keyword: "));
                case 3 -> doctorService.displayAllDoctors();
                case 4 -> back = true;
            }
        }
    }

    // --- Core Functionalities ---

    private static void registerQuickPatient() {
        String fn = getStringInput("First Name: ");
        String ln = getStringInput("Last Name: ");
        String ph = getStringInput("Phone: ");
        patientService.add(fn, ln, ph);
    }

    private static void registerEmergency() {
        String fn = getStringInput("First Name: ");
        String ln = getStringInput("Last Name: ");
        int triage = getIntInput("Triage Level (1-5): ");

        // Using Setters to avoid constructor signature errors
        EmergencyPatient ep = new EmergencyPatient();
        ep.setPatientId(HelperUtils.generateId("PAT"));
        ep.setFirstName(fn);
        ep.setLastName(ln);
        ep.setTriageLevel(triage);
        ep.setEmergencyType("General Emergency");

        patientService.add(ep);
    }

    private static void generateFullSystemReport() {
        System.out.println("\n********** FULL SYSTEM REPORT **********");
        patientService.printFullPatientReport();
        doctorService.displayAllDoctors();
        System.out.println("****************************************");
    }

    // --- Input Handling Utilities ---

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Error: Please enter a number.");
            scanner.next();
        }
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // --- Task 3.7: Population of Sample Data ---

    private static void populateInitialData() {
        System.out.println("Initializing sample data for Task 3.7...");

        // 1. Populate 10 Patients
        patientService.add("Ahmed", "Al-Saidi", "91111111");
        patientService.add("Sara", "Al-Balushi", "92222222");
        patientService.add("Mazin", "Al-Abri", "93333333");
        patientService.add("Laila", "Al-Hosni", "94444444");
        patientService.add("Zahra", "Al-Farsi", "95555555");
        patientService.add("Hamood", "Al-Kalbani", "96666666");
        patientService.add("Fatma", "Al-Hasni", "97777777");
        patientService.add("Ali", "Al-Shehi", "98888888");
        patientService.add("Maryam", "Al-Raisi", "99999999");

        // 10th Patient as Emergency using Setters
        EmergencyPatient ep = new EmergencyPatient();
        ep.setPatientId("PAT-999");
        ep.setFirstName("Othman");
        ep.setLastName("Al-Rawahi");
        ep.setTriageLevel(1);
        ep.setEmergencyType("Chest Pain");
        patientService.add(ep);

        // 2. Populate 8 Doctors
        doctorService.add("Dr. John", "Smith", "Cardiology");
        doctorService.add("Dr. Maria", "Garcia", "Neurology");
        doctorService.add("Dr. Salim", "Al-Omani", "General Surgery");
        doctorService.add("Dr. Aisha", "Al-Zadjali", "Pediatrics");
        doctorService.add("Dr. Robert", "Brown", "Emergency");
        doctorService.add("Dr. Linda", "White", "Orthopedics");
        doctorService.add("Dr. Kevin", "Lee", "Dermatology");
        doctorService.add("Dr. Noor", "Al-Harthy", "Oncology");

        System.out.println("Initialization Complete: 10 Patients and 8 Doctors loaded.\n");
    }
}