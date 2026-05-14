package Entity;

import Behavior.Displayable;
import java.time.LocalDate;

public class MedicalRecord implements Displayable {
    private String recordId;
    private String patientId;
    private String doctorId;
    private LocalDate visitDate;
    private String diagnosis;
    private String prescription;
    private String testResults;
    private String notes;

    public MedicalRecord(String recordId, String patientId, String doctorId, LocalDate visitDate, String diagnosis, String testResults, String prescription, String notes) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.visitDate = visitDate;
        this.diagnosis = diagnosis;
        this.testResults = testResults;
        this.prescription = prescription;
        this.notes = notes;
    }

    public MedicalRecord() {
        this.visitDate = LocalDate.now();
    }


    @Override
    public void displayInfo() {
        System.out.println("\n--- Medical Record Details ---");
        System.out.println("Record ID    : " + recordId);
        System.out.println("Patient ID   : " + patientId);
        System.out.println("Doctor ID    : " + doctorId);
        System.out.println("Visit Date   : " + visitDate);
        System.out.println("Diagnosis    : " + (diagnosis != null ? diagnosis : "Pending"));
        System.out.println("Prescription : " + (prescription != null ? prescription : "None"));
        System.out.println("Test Results : " + (testResults != null ? testResults : "No results yet"));
        System.out.println("Notes        : " + (notes != null ? notes : "No additional notes"));
    }

    @Override
    public void displaySummary() {
        // تم تفعيل هذه الميثود لتظهر السجلات الطبية بشكل مختصر ومنظم في التقارير
        System.out.printf("Record: %-10s | Date: %-12s | Diagnosis: %-15s\n",
                recordId, visitDate, (diagnosis != null ? diagnosis : "N/A"));
    }

    public String getRecordId() {

        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}