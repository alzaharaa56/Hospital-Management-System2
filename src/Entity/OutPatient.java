package Entity;

import Behavior.Displayable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.List;


public class OutPatient extends Patient implements Displayable {

    private int visitCount;
    private LocalDate lastVisitDate;
    private String preferredDoctorId;


    public OutPatient(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender, String phoneNumber, String email, String address, String patientId, String bloodGroup, List<String> allergies, String emergencyContact, LocalDate registrationDate, List<MedicalRecord> medicalRecords, String insuranceId, List<Appointment> appointments, int visitCount, LocalDate lastVisitDate, String preferredDoctorId) {


        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address, patientId, bloodGroup, allergies, emergencyContact, registrationDate, medicalRecords, insuranceId, appointments);
        this.visitCount = visitCount;
        this.lastVisitDate = lastVisitDate;
        this.preferredDoctorId = preferredDoctorId;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public LocalDate getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(LocalDate lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    public String getPreferredDoctorId() {
        return preferredDoctorId;
    }

    public void setPreferredDoctorId(String preferredDoctorId) {
        this.preferredDoctorId = preferredDoctorId;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Visit Count        : " + visitCount);
        System.out.println("Last Visit Date    : " + lastVisitDate);
        System.out.println("Preferred DoctorId : " + preferredDoctorId);

    }

    @Override
    public void displaySummary() {

    }


    public void scheduleFollowUp(LocalDate followUpDate ) {


        if (!HelperUtils.isValidDate(followUpDate)) {
            System.out.println("Invalid follow-up date.");
            return;
        }


        if (HelperUtils.isPastDate(followUpDate)) {
            System.out.println("Follow-up date cannot be in the past.");
            return;
        }

        System.out.println("Follow-up scheduled successfully.");
        System.out.println("Follow-up Date : " + followUpDate);
    }


    public void updateVisitCount(){


        if (!HelperUtils.isValidDate(lastVisitDate)) {
            System.out.println("Patient record is not initialized properly.");
            return;
        }



        visitCount++;


        lastVisitDate = LocalDate.now();

        System.out.println("Visit count updated successfully.");
        System.out.println("Total Visits: " + visitCount);
        System.out.println("Last Visit Date: " + lastVisitDate);

    }
}