
package Service;

import Entities.Appointment;
import Entities.MedicalRecord;
import Entities.Patient.Patient;

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


    public Patient addPatient(String ali, String hassan, String number, String s, String mail) {

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

            patients.add(addPatient("Ali", "Hassan", "9876543210", "O+", "sara@example.com"));
            System.out.println("Patient add successfully");

            System.out.println("Enter c to add more , and q to exit");
            if (scanner.nextLine().equalsIgnoreCase("q")) {
                continueFlag = false;
            }
        }
        return patients;

    }


    public void editPatient(String patientId) {

        for (Patient patient : patients) {

            if (patient.getPatientId().equals(patientId)) {

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


    public void removePatient(String patientId) {

        patients.removeIf(b -> b.getPatientId() == patientId);
        System.out.println("patient removed successfully");

        System.out.println("patient not found");

    }


    public Patient getPatientById(String patientId) {

        for (Patient patient : patients) {
            if (patient.getPatientId().equals(patientId)) {
                return patient;
            }

        }
        System.out.println("patient not found");
        return null;
    }


    public void displayAllPatients() {

        for (Patient patient : patients) {
            patient.displayInfo();
        }

    }


    public void searchPatientsByName(String name) {

        boolean found = false;


        for (Patient patient : patients) {

            String fullName = patient.getFirstName() + " " + patient.getLastName();

            if (fullName.toLowerCase().contains(name.toLowerCase())) {
                patient.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No patients found with this name");
        }

    }

    public class PatientServiceDemo {
        public static void main(String[] args) {
            PatientService service = new PatientService();

            // Add patients using different overloads
            service.addPatient("Ali", "Hassan", "9876543210", "O+", "sara@example.com");
            service.addPatient("Sara", "Ahmed", "9123456789", "O+", "sara@example.com");

            Patient fullPatient = new Patient("ID001", "Omar", "Khalid", LocalDate.of(1990, 5, 12),
                    "Male", "999888777", "omar@example.com", "Muscat, Oman",
                    "P003", "A+", List.of("Penicillin"), "Ali Hassan",
                    LocalDate.of(2026, 5, 10), "INS123", new ArrayList<>(), new ArrayList<>());
            service.addPatient(fullPatient);

            // Display all patients
            System.out.println("---- All Patients ----");
            service.displayPatients(2);

            // Display filtered patients
            System.out.println("---- Filtered Patients (Sara) ----");
            service.displayPatients(Integer.parseInt("Sara"));

            // Display limited patients
            System.out.println("---- First 2 Patients ----");
            service.displayPatients(2);

            // Search patients
            System.out.println("---- Search by Keyword (Omar) ----");
            List<Patient> results = service.searchPatients("Omar");
            results.forEach(Patient::displayInfo);
        }
    }

    private List<Patient> searchPatients(String omar) {
        return null;
    }

    private void displayPatients(int i) {

    }

    private void addPatient(Patient fullPatient) {

    }
}


