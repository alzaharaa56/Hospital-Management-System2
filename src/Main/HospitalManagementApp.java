package Main;

import Entity.*;
import Behaviour.Displayable;
import Utils.Helper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HospitalManagementApp {


    private static List<Patient> patients = new ArrayList<>();
    private static List<Doctor> doctors = new ArrayList<>();
    private static List<Nurse> nurses = new ArrayList<>();
    private static List<Appointment> appointments = new ArrayList<>();
    private static List<Department> departments = new ArrayList<>();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        departments.add(new Department("DEPT-01", "Cardiology", "DOC-01", 20));
        departments.add(new Department("DEPT-02", "Emergency", "DOC-02", 50));

        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    patientManagementMenu();
                    break;
                case 2:
                    doctorManagementMenu();
                    break;
                case 3:
                    nurseManagementMenu();
                    break;
                case 4:
                    appointmentManagementMenu();
                    break;
                case 5:
                    medicalRecordsMenu();
                    break;
                case 6:
                    departmentManagementMenu();
                    break;
                case 7:
                    reportsMenu();
                    break;
                case 8:
                    running = false;
                    System.out.println("Exiting System... Finalizing logs... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select 0-8.");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("      HOSPITAL MANAGEMENT SYSTEM 2026   ");
        System.out.println("=".repeat(40));
        System.out.println("1. Patient Management      5. Medical Records");
        System.out.println("2. Doctor Management       6. Department Management");
        System.out.println("3. Nurse Management        7. Reports & Statistics");
        System.out.println("4. Appointment Management  8. Exit");
        System.out.print("Select Category: ");
    }


    private static void patientManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- [1] Patient Management ---");
            System.out.println("1.1 New (General) | 1.2 InPatient | 1.3 OutPatient | 1.4 Emergency");
            System.out.println("1.5 View All      | 1.6 Search    | 1.7 Update     | 1.8 Remove");
            System.out.println("0. Back");
            System.out.print("Action: ");
            String sub = scanner.nextLine();
            switch (sub) {
                case "1.1":
                    registerPatient("General");
                    break;
                case "1.2":
                    registerPatient("InPatient");
                    break;
                case "1.3":
                    registerPatient("OutPatient");
                    break;
                case "1.4":
                    registerPatient("Emergency");
                    break;
                case "1.5":
                    viewAllEntities(patients);
                    break;
                case "1.6":
                    searchEntity(patients);
                    break;
                case "1.8":
                    removeEntity(patients);
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid Input.");
            }
        }
    }

    private static void registerPatient(String type) {
        System.out.print("First Name: ");
        String fn = scanner.nextLine();
        System.out.print("Last Name: ");
        String ln = scanner.nextLine();
        System.out.print("Phone: ");
        String ph = scanner.nextLine();

        Patient p;
        String id = Helper.generateId("PAT");

        if (type.equals("InPatient"))
            p = new InPatient(id, fn, LocalDate.now(), ln, "M", ph, "p@mail.com", "Addr", id, "O+", null, "INS-01", LocalDate.now(), null, "Room 1", "Bed A", "DOC-01", 100);
        else if (type.equals("Emergency"))
            p = new EmergencyPatient(id, fn, LocalDate.now(), ln, "M", ph, "p@mail.com", "Addr", id, "A-", null, "None", "Trauma", "Ambulance", 1, true);
        else p = new Patient(id, fn, LocalDate.now(), ln, "M", ph, "p@mail.com", "Addr", id, "B+", null, "Standard");

        if (p.validate()) {
            patients.add(p);
            System.out.println("Successfully Registered: " + p.getPatientId());
        }
    }


    private static void doctorManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- [2] Doctor Management ---");
            System.out.println("2.1 Doctor | 2.2 Surgeon | 2.3 Consultant | 2.4 GP");
            System.out.println("2.5 View All | 2.6 Search Spec | 2.8 Assign Patient");
            System.out.println("0. Back");
            System.out.print("Action: ");
            String sub = scanner.nextLine();
            switch (sub) {
                case "2.1":
                    addDoctor("General");
                    break;
                case "2.2":
                    addDoctor("Surgeon");
                    break;
                case "2.5":
                    viewAllEntities(doctors);
                    break;
                case "2.8":
                    assignPatientToDoctor();
                    break;
                case "0":
                    back = true;
                    break;
            }
        }
    }

    private static void addDoctor(String type) {
        System.out.print("Dr. Last Name: ");
        String ln = scanner.nextLine();
        System.out.print("Specialization: ");
        String spec = scanner.nextLine();
        String id = Helper.generateId("DOC");
        Doctor d = new Doctor(id, "Dr.", LocalDate.now(), ln, "M", "999", "d@hosp.com", "Clinic", id, spec, "PhD", 10, "DEPT-01", 150, new ArrayList<>(), new ArrayList<>());
        doctors.add(d);
        System.out.println("Doctor Added: " + d.getDoctorId());
    }


    private static void appointmentManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- [4] Appointment Management ---");
            System.out.println("4.1 New Appointment | 4.2 View All | 4.7 Cancel");
            System.out.print("Action: ");
            String sub = scanner.nextLine();
            switch (sub) {
                case "4.1":
                    scheduleNewAppointment();
                    break;
                case "4.2":
                    viewAllEntities(appointments);
                    break;
                case "0":
                    back = true;
                    break;
            }
        }
    }

    private static void scheduleNewAppointment() {
        System.out.print("Patient ID: ");
        String pId = scanner.nextLine();
        System.out.print("Doctor ID: ");
        String dId = scanner.nextLine();
        System.out.print("Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        Appointment appt = new Appointment(Helper.generateId("APT"), pId, dId, LocalDate.parse(date), "10:00 AM", "General Checkup", "Scheduled");
        if (appt.validate()) {
            appointments.add(appt);
            System.out.println("Appointment Saved.");
        }
    }


    private static void departmentManagementMenu() {
        System.out.println("\n--- [6] Department Management ---");
        System.out.println("6.1 Add Dept | 6.2 View All | 6.4 Assign Doctor");
        System.out.print("Action: ");
        String sub = scanner.nextLine();
        if (sub.equals("6.1")) {
            System.out.print("Name: ");
            String n = scanner.nextLine();
            departments.add(new Department(Helper.generateId("DEPT"), n, "None", 30));
            System.out.println("Department Created.");
        } else if (sub.equals("6.2")) {
            viewAllEntities(departments);
        }
    }


    private static void viewAllEntities(List<?> list) {
        if (list.isEmpty()) {
            System.out.println("Repository is empty.");
            return;
        }
        for (Object item : list) {
            if (item instanceof Displayable) ((Displayable) item).displaySummary();
            else System.out.println(item.toString());
        }
    }

    private static void searchEntity(List<? extends Object> list) {
        System.out.print("Enter ID to find: ");
        String id = scanner.nextLine();
        for (Object item : list) {
            if (item instanceof Patient && ((Patient) item).getPatientId().equalsIgnoreCase(id)) {
                ((Patient) item).displayInfo();
                return;
            }
            if (item instanceof Doctor && ((Doctor) item).getDoctorId().equalsIgnoreCase(id)) {
                ((Doctor) item).displayInfo();
                return;
            }
        }
        System.out.println("Not found.");
    }

    private static void removeEntity(List<? extends Object> list) {
        System.out.print("Enter ID to remove: ");
        String id = scanner.nextLine();
        boolean removed = list.removeIf(obj -> {
            if (obj instanceof Patient) return ((Patient) obj).getPatientId().equalsIgnoreCase(id);
            if (obj instanceof Doctor) return ((Doctor) obj).getDoctorId().equalsIgnoreCase(id);
            return false;
        });
        System.out.println(removed ? "Removed successfully." : "Object not found.");
    }

    private static void assignPatientToDoctor() {
        System.out.print("Doctor ID: ");
        String dId = scanner.nextLine();
        System.out.print("Patient ID: ");
        String pId = scanner.nextLine();
        for (Doctor d : doctors) {
            if (d.getDoctorId().equalsIgnoreCase(dId)) {
                d.assignPatient(pId);
                return;
            }
        }
        System.out.println("Doctor not found.");
    }

    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }


    private static void nurseManagementMenu() {
        System.out.println("Nurse Management system active...");
    }

    private static void medicalRecordsMenu() {
        System.out.println("Medical Records database active...");
    }

    private static void reportsMenu() {
        System.out.println("\n--- Hospital Statistics & Reports ---");
        System.out.println("Total Patients: " + patients.size());
        System.out.println("Total Doctors: " + doctors.size());
        System.out.println("Total Appointments: " + appointments.size());
    }
}
