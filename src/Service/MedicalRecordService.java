package Service;

import Entity.MedicalRecord;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MedicalRecordService {
    private Scanner scanner = new Scanner(System.in);

    private static List<MedicalRecord> medicalRecords = new ArrayList<>();


    public void addMedicalRecordFromConsole() {
        try {
            System.out.println("\n--- Add New Medical Record ---");
            System.out.print("Enter Record ID: ");
            String recordId = scanner.nextLine();
            System.out.print("Enter Patient ID: ");
            String patientId = scanner.nextLine();
            System.out.print("Enter Doctor ID: ");
            String doctorId = scanner.nextLine();
            System.out.print("Enter Visit Date (YYYY-MM-DD): ");
            LocalDate visitDate = LocalDate.parse(scanner.nextLine());
            System.out.print("Enter Diagnosis: ");
            String diagnosis = scanner.nextLine();
            System.out.print("Enter Prescription: ");
            String prescription = scanner.nextLine();
            System.out.print("Enter Test Results: ");
            String testResults = scanner.nextLine();
            System.out.print("Enter Notes: ");
            String notes = scanner.nextLine();

            MedicalRecord record = new MedicalRecord(recordId, patientId, doctorId, visitDate, diagnosis, prescription, testResults, notes);
            medicalRecords.add(record);
            System.out.println("Medical Record added successfully!");

        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format. Use YYYY-MM-DD.");
        }
    }


    public void addMedicalRecord(String patientId, String doctorId, String diagnosis) {
        String recordId = "REC-" + (medicalRecords.size() + 1);
        MedicalRecord record = new MedicalRecord(recordId, patientId, doctorId, LocalDate.now(), diagnosis, "N/A", "N/A", "Quick Entry");
        medicalRecords.add(record);
        System.out.println("Quick medical record created.");
    }


    public MedicalRecord getRecordById(String recordId) {
        for (MedicalRecord r : medicalRecords) {
            if (r.getRecordId().equals(recordId)) return r;
        }
        return null;
    }


    public void editMedicalRecord(String recordId) {
        MedicalRecord record = getRecordById(recordId);
        if (record != null) {
            System.out.print("Enter updated Diagnosis (Current: " + record.getDiagnosis() + "): ");
            record.setDiagnosis(scanner.nextLine());
            System.out.print("Enter updated Prescription: ");
            record.setPrescription(scanner.nextLine());
            System.out.print("Enter updated Notes: ");
            record.setNotes(scanner.nextLine());
            System.out.println("Record updated successfully!");
        } else {
            System.out.println("Record not found.");
        }
    }


    public void removeMedicalRecord(String recordId) {
        if (medicalRecords.removeIf(r -> r.getRecordId().equals(recordId))) {
            System.out.println("Record removed.");
        } else {
            System.out.println("Record not found.");
        }
    }


    public void displayPatientHistory(String patientId) {
        System.out.println("\n--- Medical History for Patient ID: " + patientId + " ---");
        boolean found = false;
        for (MedicalRecord record : medicalRecords) {
            if (record.getPatientId().equals(patientId)) {
                record.displayInfo();
                System.out.println("-----------------------");
                found = true;
            }
        }
        if (!found) System.out.println("No records found for this patient.");
    }


    public List<MedicalRecord> getRecordsByDoctor(String doctorId) {
        List<MedicalRecord> results = new ArrayList<>();
        for (MedicalRecord r : medicalRecords) {
            if (r.getDoctorId().equals(doctorId)) results.add(r);
        }
        return results;
    }
}