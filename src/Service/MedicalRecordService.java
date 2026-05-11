package Service;
import Entity.MedicalRecord;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class MedicalRecordService {
    Scanner scanner = new Scanner(System.in);
    static List<MedicalRecord> medicalRecords = new ArrayList<>();


           public void addMedicalRecord() {
            System.out.println("Enter Record ID:");
        String recordId = scanner.nextLine();
        System.out.println("Enter Patient ID:");
        String patientId = scanner.nextLine();
        System.out.println("Enter Doctor ID:");
        String doctorId = scanner.nextLine();
        System.out.println("Enter Visit Date (YYYY-MM-DD):");
        String dateInput = scanner.nextLine();
        LocalDate visitDate = LocalDate.parse(dateInput);
        System.out.println("Enter Diagnosis:");
        String diagnosis = scanner.nextLine();
        System.out.println("Enter Prescription:");
        String prescription = scanner.nextLine();
        System.out.println("Enter Test Results:");
        String testResults = scanner.nextLine();
        System.out.println("Enter Notes:");
        String notes = scanner.nextLine();
MedicalRecord record = new MedicalRecord(recordId, patientId, doctorId, visitDate, diagnosis, prescription, testResults, notes);
               medicalRecords.add(record);
                System.out.println("Medical Record added successfully!");
    }


public void editMedicalRecord(String recordId) {
        for (MedicalRecord record : medicalRecords) {
            if (record.getRecordId().equals(recordId)) {
                System.out.println("Enter updated Diagnosis:");
                record.setDiagnosis(scanner.nextLine());
                System.out.println("Enter updated Prescription:");
                record.setPrescription(scanner.nextLine());
                System.out.println("Enter updated Test Results:");
                record.setTestResults(scanner.nextLine());
                System.out.println("Enter updated Notes:");
                record.setNotes(scanner.nextLine());
                System.out.println("Record updated successfully!");
                return;
            }
        }
        System.out.println("Record not found.");
    }


    public void removeMedicalRecord(String recordId) {
        boolean removed = medicalRecords.removeIf(r -> r.getRecordId().equals(recordId));
        if (removed) {
            System.out.println("Record removed successfully.");
        } else {
            System.out.println("Record not found.");
        }
    }


    public List<MedicalRecord> getRecordsByPatientId(String patientId) {
            List<MedicalRecord> results = new ArrayList<>();
        for (MedicalRecord record : medicalRecords) {
            if (record.getPatientId().equals(patientId)) {
                results.add(record);
            }
        }
        return results;
    }

  public void displayRecordsByDoctorId(String doctorId) {
        for (MedicalRecord record : medicalRecords) {
            if (record.getDoctorId().equals(doctorId)) {
                record.displayInfo();
            }
        }
    }

   public void displayPatientHistory(String patientId) {
            System.out.println("--- Medical History for Patient: " + patientId + " ---");
        boolean found = false;
        for (MedicalRecord record : medicalRecords) {
            if (record.getPatientId().equals(patientId)) {
                record.displayInfo();
                        found = true;
            }
        }
        if (!found) {
            System.out.println("No records found for this patient.");
        }
    }
}
