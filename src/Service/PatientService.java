package Service;

import Entity.Patient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatientService {
    private Scanner scanner = new Scanner(System.in);
    private static List<Patient> patients = new ArrayList<>();




    public void addPatientFromConsole() {
        try {
            System.out.println("\n--- Register New Patient ---");
            System.out.print("Enter First Name: ");
            String fName = scanner.nextLine();
            System.out.print("Enter Last Name: ");
            String lName = scanner.nextLine();
            System.out.print("Enter Phone Number: ");
            String phone = scanner.nextLine();


            System.out.print("Enter Patient ID: ");
            String pId = scanner.nextLine();
            System.out.print("Enter Blood Group: ");
            String blood = scanner.nextLine();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();


            Patient p = new Patient();
            p.setFirstName(fName); p.setLastName(lName); p.setPhoneNumber(phone);
            p.setPatientId(pId); p.setBloodGroup(blood); p.setEmail(email);
            p.setRegistrationDate(LocalDate.now());

            addPatient(p);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public void addPatient(String firstName, String lastName, String phone) {
        Patient patient = new Patient();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setPhoneNumber(phone);
        patient.setRegistrationDate(LocalDate.now());
        patients.add(patient);
        System.out.println("Basic profile created for: " + firstName);
    }


    public void addPatient(String firstName, String lastName, String phone, String bloodGroup, String email) {
        Patient patient = new Patient();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setPhoneNumber(phone);
        patient.setBloodGroup(bloodGroup);
        patient.setEmail(email);
        patient.setRegistrationDate(LocalDate.now());
        patients.add(patient);
        System.out.println("Detailed profile created for: " + firstName);
    }


    public void addPatient(Patient patient) {
        if (patient != null) {
            patients.add(patient);
            System.out.println("Full Patient object added successfully.");
        }
    }




    public List<Patient> searchPatients(String keyword) {
        List<Patient> found = new ArrayList<>();
        String key = keyword.toLowerCase();
        for (Patient p : patients) {
            if (p.getFirstName().toLowerCase().contains(key) ||
                    p.getLastName().toLowerCase().contains(key) ||
                    (p.getPatientId() != null && p.getPatientId().contains(keyword)) ||
                    (p.getPhoneNumber() != null && p.getPhoneNumber().contains(keyword))) {
                found.add(p);
            }
        }
        return found;
    }


    public List<Patient> searchPatients(String firstName, String lastName) {
        List<Patient> found = new ArrayList<>();
        for (Patient p : patients) {
            if (p.getFirstName().equalsIgnoreCase(firstName) &&
                    p.getLastName().equalsIgnoreCase(lastName)) {
                found.add(p);
            }
        }
        return found;
    }




    public void displayPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
        } else {
            System.out.println("\n--- All Registered Patients ---");
            for (Patient p : patients) {
                p.displayInfo();
                System.out.println("--------------------------------");
            }
        }
    }


    public void displayPatients(String filter) {
        System.out.println("\n--- Filtered Patients (" + filter + ") ---");
        boolean found = false;
        for (Patient p : patients) {
            if ((p.getBloodGroup() != null && p.getBloodGroup().equalsIgnoreCase(filter)) ||
                    (p.getGender() != null && p.getGender().equalsIgnoreCase(filter))) {
                p.displayInfo();
                found = true;
            }
        }
        if (!found) System.out.println("No patients match this criteria.");
    }


    public void displayPatients(int limit) {
        System.out.println("\n--- Displaying first " + limit + " patients ---");
        int count = 0;
        for (Patient p : patients) {
            if (count >= limit) break;
            p.displayInfo();
            count++;
        }
    }



    public Patient getPatientById(String patientId) {
        for (Patient p : patients) {
            if (p.getPatientId() != null && p.getPatientId().equals(patientId)) {
                return p;
            }
        }
        return null;
    }

    public void removePatient(String patientId) {
        if (patients.removeIf(p -> p.getPatientId() != null && p.getPatientId().equals(patientId))) {
            System.out.println("Patient removed successfully.");
        } else {
            System.out.println("Error: Patient ID not found.");
        }
    }
}