package Entity;

import Behaviour.Displayable;
import Behaviour.Editable;
import Utils.Helper;
import java.time.LocalDate;

public class MedicalRecord implements Displayable, Editable {
    private String recordId;
    private String patientId;
    private String doctorId;
    private LocalDate visitDate;
    private String diagnosis;
    private String prescription;
    private String testResults;
    private String notes;

    public MedicalRecord(String recordId, String patientId, String doctorId, LocalDate visitDate,
                         String diagnosis, String testResults, String prescription, String notes) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.visitDate = visitDate;
        this.diagnosis = diagnosis;
        this.testResults = testResults;
        this.prescription = prescription;
        this.notes = notes;
    }

    public MedicalRecord() {}

    @Override
    public void displayInfo() {
        System.out.println("\n--- Clinical Medical Record ---");
        System.out.println("Record ID    : " + recordId);
        System.out.println("Patient ID   : " + patientId);
        System.out.println("Doctor ID    : " + doctorId);
        System.out.println("Visit Date   : " + visitDate);
        System.out.println("Diagnosis    : " + diagnosis);
        System.out.println("Prescription : " + prescription);
        System.out.println("Test Results : " + testResults);
        System.out.println("Clinical Notes: " + (notes == null || notes.isEmpty() ? "No notes available." : notes));
    }

    @Override
    public void displaySummary() {
        System.out.println("Record: " + recordId + " | Patient: " + patientId + " | Date: " + visitDate);
    }

    @Override
    public void edit(Object updatedData) {
        if (updatedData instanceof MedicalRecord) {
            MedicalRecord record = (MedicalRecord) updatedData;
            this.diagnosis = record.diagnosis;
            this.prescription = record.prescription;
            this.testResults = record.testResults;
            this.notes = record.notes;
            System.out.println("Medical record " + recordId + " has been updated.");
        }
    }

    @Override
    public boolean validate() {
        return Helper.isValidString(recordId) && Helper.isValidString(diagnosis) &&
                Helper.isValidString(patientId) && Helper.isValidString(doctorId);
    }

    public String getRecordId() { return recordId; }
    public void setRecordId(String recordId) { this.recordId = recordId; }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }

    public LocalDate getVisitDate() { return visitDate; }
    public void setVisitDate(LocalDate visitDate) { this.visitDate = visitDate; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public String getPrescription() { return prescription; }
    public void setPrescription(String prescription) { this.prescription = prescription; }

    public String getTestResults() { return testResults; }
    public void setTestResults(String testResults) { this.testResults = testResults; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}