package Service;

import Entity.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorService {

    private static List<Doctor> doctors = new ArrayList<>();


    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        System.out.println("Doctor added successfully: " + doctor.getDoctorId());
    }


    public void editDoctor(String doctorId, Doctor updatedDoctor) {
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getDoctorId().equals(doctorId)) {
                doctors.set(i, updatedDoctor);
                System.out.println("Doctor updated successfully: " + doctorId);
                return;
            }
        }
        System.out.println("Doctor not found with ID: " + doctorId);
    }


    public void removeDoctor(String doctorId) {
        boolean removed = doctors.removeIf(d -> d.getDoctorId().equals(doctorId));
        if (removed) {
            System.out.println("Doctor removed successfully: " + doctorId);
        } else {
            System.out.println("Doctor not found with ID: " + doctorId);
        }
    }


    public Doctor getDoctorById(String doctorId) {
        for (Doctor d : doctors) {
            if (d.getDoctorId().equals(doctorId)) {
                return d;
            }
        }
        System.out.println("Doctor not found with ID: " + doctorId);
        return null;
    }


    public void displayAllDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors available.");
        } else {
            for (Doctor d : doctors) {
                d.displayInfo();
                System.out.println("----------------------");
            }
        }
    }


    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        List<Doctor> result = new ArrayList<>();
        for (Doctor d : doctors) {
            if (d.getSpecialization().equalsIgnoreCase(specialization)) {
                result.add(d);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No doctors found with specialization: " + specialization);
        }
        return result;
    }


    public List<Doctor> getAvailableDoctors() {
        List<Doctor> available = new ArrayList<>();
        for (Doctor d : doctors) {
            if (d.getAvailableSlots() != null && !d.getAvailableSlots().isEmpty()) {
                available.add(d);
            }
        }
        if (available.isEmpty()) {
            System.out.println("No doctors currently available.");
        }
        return available;
    }
}

