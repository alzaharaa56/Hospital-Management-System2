package Service;

import Behavior.Manageable;
import Behavior.Searchable;
import Entity.MedicalRecord;
import Utils.HelperUtils;
import Utils.InputHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MedicalRecordService implements Manageable, Searchable {

    private static List<MedicalRecord> medicalRecordList = new ArrayList<>();



    @Override
    public void add(Object entity) {
        if (entity instanceof MedicalRecord record) {

            if (searchById(record.getRecordId()) == null) {
                medicalRecordList.add(record);
                System.out.println("Success: Medical record added.");
            } else {
                System.out.println("Error: Record ID already exists.");
            }
        }
    }

    @Override
    public void remove(String id) {

        boolean removed = medicalRecordList.removeIf(m -> m.getRecordId().equalsIgnoreCase(id));
        if (removed) {
            System.out.println("Success: Medical record [" + id + "] removed.");
        } else {
            System.out.println("Error: Record not found.");
        }
    }

    @Override
    public List<Object> getAll() {
        return new ArrayList<>(medicalRecordList);
    }



    @Override
    public Object searchById(String id) {
        return medicalRecordList.stream()
                .filter(m -> m.getRecordId().equalsIgnoreCase(id))
                .findFirst().orElse(null);
    }

    @Override
    public void search(String keyword) {
        System.out.println("\n--- Search Results for Medical Records: '" + keyword + "' ---");
        List<MedicalRecord> results = medicalRecordList.stream()
                .filter(m -> m.getPatientId().equalsIgnoreCase(keyword) ||
                        m.getDiagnosis().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            System.out.println("No records match your search.");
        } else {
            results.forEach(MedicalRecord::displaySummary);
        }
    }



    public void createAndAddRecord() {

        String recordId = HelperUtils.generateId("REC");
        String patientId = InputHandler.getStringInput("Enter Patient ID: ");
        String doctorId = InputHandler.getStringInput("Enter Doctor ID: ");
        LocalDate date = InputHandler.getDateInput("Enter Visit Date");
        String diagnosis = InputHandler.getStringInput("Diagnosis: ");
        String prescription = InputHandler.getStringInput("Prescription: ");
        String tests = InputHandler.getStringInput("Test Results: ");
        String notes = InputHandler.getStringInput("Notes: ");

        MedicalRecord record = new MedicalRecord(recordId, patientId, doctorId, date, diagnosis, tests, prescription, notes);
        add(record);
    }

    public void displayPatientHistory(String patientId) {
        System.out.println("\n--- Medical History for Patient: " + patientId + " ---");
        boolean found = false;
        for (MedicalRecord record : medicalRecordList) {
            if (record.getPatientId().equalsIgnoreCase(patientId)) {
                record.displayInfo();
                found = true;
            }
        }
        if (!found) System.out.println("No history found for this patient.");
    }

    public void displayPatientHistory() {


    }
}