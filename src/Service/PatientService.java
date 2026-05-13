package Service;

import Entity.Patient;
import Entity.InPatient;
import Entity.EmergencyPatient;
import Behavior.Manageable;
import Behavior.Searchable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PatientService implements Manageable, Searchable {


    private static List<Patient> patients = new ArrayList<>();



    @Override
    public void add(Object entity) {
        if (entity instanceof Patient) {
            Patient p = (Patient) entity;

            if (searchById(p.getPatientId()) == null) {
                patients.add(p);
                System.out.println("Success: Patient [" + p.getFirstName() + "] added to the system.");
            } else {
                System.out.println("Error: Patient ID already exists.");
            }
        }
    }


    public void add(String firstName, String lastName, String phone) {
        if (HelperUtils.isValidString(firstName) && HelperUtils.isValidString(phone)) {
            String generatedId = HelperUtils.generateId("PAT");
            Patient newPatient = new Patient();
            newPatient.setPatientId(generatedId);
            newPatient.setFirstName(firstName);
            newPatient.setLastName(lastName);
            newPatient.setPhoneNumber(phone);
            newPatient.setRegistrationDate(LocalDate.now());

            patients.add(newPatient);
            System.out.println("Success: Quick registration complete. ID: " + generatedId);
        } else {
            System.out.println("Error: Basic info validation failed.");
        }
    }

    @Override
    public void remove(String id) {
        Patient p = (Patient) searchById(id);
        if (p != null) {
            patients.remove(p);
            System.out.println("Success: Patient " + id + " has been removed.");
        } else {
            System.out.println("Error: Patient not found.");
        }
    }

    @Override
    public List<Object> getAll() {

        return new ArrayList<>(patients);
    }



    @Override
    public Object searchById(String id) {
        for (Patient p : patients) {
            if (p.getPatientId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void search(String keyword) {
        System.out.println("--- Search Results for: '" + keyword + "' ---");
        List<Patient> results = patients.stream()
                .filter(p -> p.getFirstName().toLowerCase().contains(keyword.toLowerCase()) ||
                        p.getPatientId().equalsIgnoreCase(keyword))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            System.out.println("No patients found.");
        } else {
            for (Patient p : results) {
                p.displaySummary();
            }
        }
    }




    public void displayInPatients() {
        System.out.println("--- List of Current In-Patients ---");
        for (Patient p : patients) {
            if (p instanceof InPatient) {
                p.displaySummary();
            }
        }
    }


    public void displayCriticalEmergencyPatients() {
        System.out.println("--- Critical Emergency Cases ---");
        for (Patient p : patients) {
            if (p instanceof EmergencyPatient) {
                EmergencyPatient ep = (EmergencyPatient) p;
                if (ep.getTriageLevel() <= 2) {
                    ep.displaySummary();
                }
            }
        }
    }


    public void displayAllFullInfo() {
        if (patients.isEmpty()) {
            System.out.println("The system is currently empty.");
            return;
        }
        for (Patient p : patients) {
            p.displayInfo();
            System.out.println("------------------------------------");
        }
    }

    public Patient getPatientById(String patientId) {
        return null;
    }

    public void printFullPatientReport() {

    }
}