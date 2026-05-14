package Service;

import Behavior.Manageable;
import Behavior.Searchable;
import Entity.Doctor;
import Utils.HelperUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DoctorService implements Manageable, Searchable {

    private static List<Doctor> doctors = new ArrayList<>();



    @Override
    public void add(Object entity) {
        if (entity instanceof Doctor doc) {

            if (getDoctorById(doc.getDoctorId()) == null) {
                doctors.add(doc);
                System.out.println("Success: Doctor [" + doc.getFirstName() + "] added.");
            } else {
                System.out.println("Error: Doctor ID already exists.");
            }
        }
    }

    public void addDoctor(String firstName, String specialization, String phone, double fee) {
        if (HelperUtils.isValidString(firstName) && !HelperUtils.isNegative(fee)) {
            Doctor doc = new Doctor();
            doc.setDoctorId(HelperUtils.generateId("DOC"));
            doc.setFirstName(firstName);
            doc.setLastName("Omani");
            doc.setSpecialization(specialization);
            doc.setPhoneNumber(phone);
            doc.setConsultationFee(fee);
            doctors.add(doc);
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



    @Override
    public Object searchById(String id) {
        return doctors.stream()
                .filter(d -> d.getDoctorId().equalsIgnoreCase(id))
                .findFirst().orElse(null);
    }


    public Doctor getDoctorById(String doctorId) {
        return (Doctor) searchById(doctorId);
    }

    @Override
    public void search(String keyword) {
        System.out.println("\n--- Search Results for: '" + keyword + "' ---");
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



    public void displayDoctors() {
        System.out.println("\n===== HOSPITAL DOCTOR DIRECTORY =====");
        if (doctors.isEmpty()) {
            System.out.println("No doctors registered.");
        } else {

            doctors.forEach(Doctor::displaySummary);
        }
    }


    public void displayDoctors(String specialization) {
        System.out.println("\n--- Doctors in " + specialization + " ---");
        doctors.stream()
                .filter(d -> d.getSpecialization().equalsIgnoreCase(specialization))
                .forEach(Doctor::displaySummary);
    }



    public void printFullDoctorReport() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("       FULL MEDICAL STAFF REPORT       ");
        System.out.println("=".repeat(40));
        for (Doctor d : doctors) {
            d.displayInfo();
            System.out.println("-".repeat(40));
        }
    }
}