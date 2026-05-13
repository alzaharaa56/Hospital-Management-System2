package Service;

import Entity.Doctor;
import Entity.Surgeon;
import Behavior.Manageable;
import Behavior.Searchable;
import Utils.HelperUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DoctorService implements Manageable, Searchable {

    private static List<Doctor> doctors = new ArrayList<>();

    // --- Manageable Interface Implementation ---

    @Override
    public void add(Object entity) {
        if (entity instanceof Doctor) {
            Doctor doc = (Doctor) entity;
            if (searchById(doc.getDoctorId()) == null) {
                doctors.add(doc);
                System.out.println("Success: Doctor [" + doc.getFirstName() + "] added successfully.");
            } else {
                System.out.println("Error: Doctor ID already exists.");
            }
        }
    }

    // Overloaded Method: Quick add with basic info
    public void add(String firstName, String lastName, String specialization) {
        if (HelperUtils.isValidString(firstName) && HelperUtils.isValidString(specialization)) {
            String newId = HelperUtils.generateId("DOC");
            Doctor doc = new Doctor();
            doc.setDoctorId(newId);
            doc.setFirstName(firstName);
            doc.setLastName(lastName);
            doc.setSpecialization(specialization);
            doctors.add(doc);
            System.out.println("Quick registration successful. ID: " + newId);
        }
    }

    @Override
    public void remove(String id) {
        boolean removed = doctors.removeIf(d -> d.getDoctorId().equalsIgnoreCase(id));
        if (removed) {
            System.out.println("Doctor record " + id + " removed.");
        } else {
            System.out.println("Error: Doctor not found.");
        }
    }

    @Override
    public List<Object> getAll() {
        return new ArrayList<>(doctors);
    }

    // --- Searchable Interface Implementation ---

    @Override
    public Object searchById(String id) {
        return doctors.stream()
                .filter(d -> d.getDoctorId().equalsIgnoreCase(id))
                .findFirst().orElse(null);
    }

    @Override
    public void search(String keyword) {
        System.out.println("\nSearching Doctors for: '" + keyword + "'");
        List<Doctor> results = doctors.stream()
                .filter(d -> d.getFirstName().toLowerCase().contains(keyword.toLowerCase()) ||
                        d.getSpecialization().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            System.out.println("No matching doctors found.");
        } else {
            results.forEach(Doctor::displaySummary);
        }
    }

    // --- Specific Reports ---

    public void displayAllDoctors() {
        System.out.println("\n===== HOSPITAL DOCTOR DIRECTORY =====");
        if (doctors.isEmpty()) {
            System.out.println("No doctors registered.");
        } else {
            for (Doctor d : doctors) {
                d.displayInfo();
                System.out.println("-------------------------------------");
            }
        }
    }

    public Doctor getDoctorById(String doctorId) {
        return null;
    }
}