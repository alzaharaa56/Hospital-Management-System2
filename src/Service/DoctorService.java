package Service;

import Entity.Doctor;
import Entity.Patient;
import Behaviour.Manageable;
import Behaviour.Searchable;
import Utils.Helper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class DoctorService implements Manageable<Doctor>, Searchable<Doctor> {

    private Scanner scanner = new Scanner(System.in);
    private static List<Doctor> doctors = new ArrayList<>();



    @Override
    public void add(Doctor doctor) {

        if (Helper.isNotNull(doctor) && doctor.validate()) {
            doctors.add(doctor);
            System.out.println("Success: Doctor " + doctor.getLastName() + " added to the system.");
        } else {
            System.out.println("Error: Doctor validation failed. Record not saved.");
        }
    }

    @Override
    public void remove(String doctorId) {
        if (Helper.isNull(doctorId)) return;

        boolean removed = doctors.removeIf(d -> d.getDoctorId().equals(doctorId));
        if (removed) {
            System.out.println("Success: Doctor record removed.");
        } else {
            System.out.println("Error: Doctor ID not found.");
        }
    }

    @Override
    public List<Doctor> getAll() {
        return new ArrayList<>(doctors);
    }



    @Override
    public Doctor searchById(String doctorId) {
        if (Helper.isNull(doctorId)) return null;
        return doctors.stream()
                .filter(d -> d.getDoctorId().equals(doctorId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Doctor> search(String keyword) {
        if (Helper.isNull(keyword)) return new ArrayList<>();
        String key = keyword.toLowerCase();
        return doctors.stream()
                .filter(d -> d.getFirstName().toLowerCase().contains(key) ||
                        d.getLastName().toLowerCase().contains(key) ||
                        d.getSpecialization().toLowerCase().contains(key))
                .collect(Collectors.toList());
    }



    public Doctor addDoctorFromConsole() {
        try {
            System.out.println("\n--- Register New Doctor ---");
            System.out.print("First Name: ");
            String fName = scanner.nextLine();
            System.out.print("Last Name: ");
            String lName = scanner.nextLine();


            String docId = Helper.generateId("DOC", 4);

            System.out.print("Specialization: ");
            String spec = scanner.nextLine();
            System.out.print("Consultation Fee: ");
            double fee = scanner.nextDouble(); scanner.nextLine();


            Doctor doctor = new Doctor();
            doctor.setFirstName(fName);
            doctor.setLastName(lName);
            doctor.setDoctorId(docId);
            doctor.setSpecialization(spec);
            doctor.setConsultationFee(fee);

            add(doctor);
            return doctor;
        } catch (Exception e) {
            System.out.println("Input Error: " + e.getMessage());
            return null;
        }
    }


    public void assignPatient(String doctorId, String patientId) {
        Doctor doc = searchById(doctorId);
        if (Helper.isNotNull(doc) && Helper.isValidString(patientId)) {
            doc.getAssignedPatients().add(patientId);
            System.out.println("Success: Patient " + patientId + " assigned to Dr. " + doc.getLastName());
        } else {
            System.out.println("Error: Assignment failed. Check IDs.");
        }
    }

    // --- Display Methods ---

    public void displayDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors currently in registry.");
        } else {
            System.out.println("\n--- Hospital Medical Staff ---");
            for (Doctor doc : doctors) {
                doc.displaySummary();
            }
        }
    }
}