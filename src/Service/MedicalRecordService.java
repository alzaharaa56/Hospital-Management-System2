package Service;


import Entity.MedicalRecord;

import java.util.ArrayList;
import java.util.List;
public class MedicalRecordService {
    private static List<MedicalRecord> medicalRecords = new ArrayList<>();
    public void addMedicalRecord(MedicalRecord record) {
        medicalRecords.add(record);
        System.out.println("Medical record added successfully.");
    }
    public List<MedicalRecord> getRecordsByPatientId(String patientId) {
        List<MedicalRecord> result = new ArrayList<>();
        for (MedicalRecord record : medicalRecords) {
            if (record.getPatientId().equals(patientId)) {
                result.add(record);
            }
        }
        return result;
    }
    public List<MedicalRecord> getRecordsByDoctorId(String doctorId) {
        List<MedicalRecord> result = new ArrayList<>();
        for (MedicalRecord record : medicalRecords) {
            if (record.getDoctorId().equals(doctorId)) {
                result.add(record);
            }
        }
        return result;
    }
    public void displayPatientHistory(String patientId) {
        System.out.println("--- Medical History for Patient ID: " + patientId + " ---");
        List<MedicalRecord> history = getRecordsByPatientId(patientId);
        if (history.isEmpty()) {
            System.out.println("No records found for this patient.");
        } else {
            for (MedicalRecord record : history) {
                record.displayInfo();
            }
        }
    }
    public List<MedicalRecord> getAllRecords() {
        return medicalRecords;
    }
    public void removeRecord(String recordId) {
        medicalRecords.removeIf(record -> record.getRecordId().equals(recordId));
    }
}
