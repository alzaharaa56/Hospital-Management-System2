package Service;

import Entity.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientService {

    private static List<Patient> patients = new ArrayList<>();


    public void addPatient(Patient patient) {
        patients.add(patient);
        System.out.println("Patient added successfully: " + patient.getPatientId());
    }


    public void editPatient(String patientId, Patient updatedPatient) {
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getPatientId().equals(patientId)) {
                patients.set(i, updatedPatient);
                System.out.println("Patient updated successfully: " + patientId);
                return;
            }
        }
        System.out.println("Patient not found with ID: " + patientId);
    }


    public void removePatient(String patientId) {
        boolean removed = patients.removeIf(p -> p.getPatientId().equals(patientId));
        if (removed) {
            System.out.println("Patient removed successfully: " + patientId);
        } else {
            System.out.println("Patient not found with ID: " + patientId);
        }
    }


    public Patient getPatientById(String patientId) {
        for (Patient p : patients) {
            if (p.getPatientId().equals(patientId)) {
                return p;
            }
        }
        System.out.println("Patient not found with ID: " + patientId);
        return null;
    }


    public void displayAllPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients available.");
        } else {
            for (Patient p : patients) {
                p.displayInfo();
                System.out.println("----------------------");
            }
        }
    }


    public List<Patient> searchPatientsByName(String name) {
        List<Patient> result = new ArrayList<>();
        for (Patient p : patients) {
            String fullName = p.getFirstName() + " " + p.getLastName();
            if (fullName.toLowerCase().contains(name.toLowerCase())) {
                result.add(p);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No patients found with name: " + name);
        }
        return result;
    }
}

