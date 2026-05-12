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

    // Full Constructor
    public MedicalRecord(String recordId, String patientId, String doctorId, LocalDate visitDate,
                         String diagnosis, String testResults, String prescription, String notes) {


        this.recordId = Helper.isNotNull(recordId) ? recordId : Helper.generateId("REC");


        setPatientId(patientId);
        setDoctorId(doctorId);
        setVisitDate(visitDate);
        setDiagnosis(diagnosis);
        setTestResults(testResults);
        setPrescription(prescription);
        setNotes(notes);
    }


    public MedicalRecord() {
        this.recordId = Helper.generateId("REC");
        this.visitDate = LocalDate.now();
    }


    @Override
    public void displayInfo() {
        System.out.println("\n--- Clinical Medical Record ---");
        System.out.println("Record ID    : " + recordId);
        System.out.println("Patient ID   : " + (Helper.isNotNull(patientId) ? patientId : "N/A"));
        System.out.println("Doctor ID    : " + (Helper.isNotNull(doctorId) ? doctorId : "N/A"));
        System.out.println("Visit Date   : " + visitDate);
        System.out.println("Diagnosis    : " + (Helper.isNotNull(diagnosis) ? diagnosis : "Pending"));
        System.out.println("Prescription : " + (Helper.isNotNull(prescription) ? prescription : "None"));
        System.out.println("Test Results : " + (Helper.isNotNull(testResults) ? testResults : "No results"));
        System.out.println("Clinical Notes: " + (Helper.isNotNull(notes) ? notes : "No notes available."));
    }

    @Override
    public void displaySummary() {
        System.out.println("Record: " + recordId + " | Patient: " + patientId + " | Date: " + visitDate);
    }



    @Override
    public void edit(Object updatedData) {
        if (Helper.isNotNull(updatedData) && updatedData instanceof MedicalRecord) {
            MedicalRecord record = (MedicalRecord) updatedData;
            setDiagnosis(record.diagnosis);
            setPrescription(record.prescription);
            setTestResults(record.testResults);
            setNotes(record.notes);
            System.out.println("Medical record " + recordId + " has been updated.");
        }
    }

    @Override
    public boolean validate() {

        return Helper.isValidString(recordId) &&
                Helper.isValidString(diagnosis) &&
                Helper.isValidString(patientId) &&
                Helper.isValidString(doctorId);
    }



    public void setRecordId(String recordId) {
        if (Helper.isValidString(recordId)) {
            this.recordId = recordId;
        }
    }

    public void setPatientId(String patientId) {
        if (Helper.isValidString(patientId)) {
            this.patientId = patientId;
        }
    }

    public void setDoctorId(String doctorId) {
        if (Helper.isValidString(doctorId)) {
            this.doctorId = doctorId;
        }
    }

    public void setVisitDate(LocalDate visitDate) {

        if (Helper.isNotNull(visitDate) && !Helper.isFutureDate(visitDate)) {
            this.visitDate = visitDate;
        } else {
            this.visitDate = LocalDate.now();
        }
    }

    public void setDiagnosis(String diagnosis) {
        if (Helper.isNotNull(diagnosis)) {
            this.diagnosis = diagnosis;
        }
    }

    public void setPrescription(String prescription) {
        if (Helper.isNotNull(prescription)) {
            this.prescription = prescription;
        }
    }

    public void setTestResults(String testResults) {
        if (Helper.isNotNull(testResults)) {
            this.testResults = testResults;
        }
    }

    public void setNotes(String notes) {
        if (Helper.isNotNull(notes)) {
            this.notes = notes;
        }
    }



    public String getRecordId() { return recordId; }
    public String getPatientId() { return patientId; }
    public String getDoctorId() { return doctorId; }
    public LocalDate getVisitDate() { return visitDate; }
    public String getDiagnosis() { return diagnosis; }
    public String getPrescription() { return prescription; }
    public String getTestResults() { return testResults; }
    public String getNotes() { return notes; }
}