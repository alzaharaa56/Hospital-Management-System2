package Entity;

import Utils.Helper;
import java.time.LocalDate;

public class OutPatient extends Patient {

    private int visitCount;
    private LocalDate lastVisitDate;
    private String preferredDoctorId;

    public OutPatient(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender,
                      String phoneNumber, String email, String address, String patientId, String bloodGroup,
                      LocalDate registrationDate, String insuranceId, int visitCount,
                      LocalDate lastVisitDate, String preferredDoctorId) {

        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address,
                patientId, bloodGroup, registrationDate, insuranceId);


        setVisitCount(visitCount);
        setLastVisitDate(lastVisitDate);
        setPreferredDoctorId(preferredDoctorId);
    }

    public OutPatient() {
        super();
    }



    @Override
    public boolean validate() {

        return super.validate() && visitCount >= 0;
    }

    public void scheduleFollowUp(LocalDate followUpDate) {

        if (Helper.isNotNull(followUpDate) && Helper.isPastDate(followUpDate)) {
            System.out.println("Error: Follow-up date cannot be in the past.");
            return;
        }
        System.out.println("Follow-up scheduled successfully.");
        System.out.println("Follow-up Date : " + followUpDate);
    }

    public void updateVisitCount() {
        this.visitCount++;
        this.lastVisitDate = LocalDate.now();
        System.out.println("Success: Visit count incremented to " + visitCount);
    }



    public void setVisitCount(int visitCount) {
        if (visitCount >= 0) {
            this.visitCount = visitCount;
        } else {
            System.out.println("Error: Visit count cannot be negative.");
        }
    }

    public void setLastVisitDate(LocalDate lastVisitDate) {

        if (Helper.isNotNull(lastVisitDate) && !Helper.isFutureDate(lastVisitDate)) {
            this.lastVisitDate = lastVisitDate;
        } else {
            this.lastVisitDate = null;
        }
    }

    public void setPreferredDoctorId(String preferredDoctorId) {

        if (Helper.isValidString(preferredDoctorId)) {
            this.preferredDoctorId = preferredDoctorId;
        } else {
            this.preferredDoctorId = null;
        }
    }

    // --- Display Methods ---

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Visit Count        : " + visitCount);
        System.out.println("Last Visit Date    : " + (Helper.isNotNull(lastVisitDate) ? lastVisitDate : "No previous visits"));
        System.out.println("Preferred Doctor ID: " + (Helper.isNotNull(preferredDoctorId) ? preferredDoctorId : "None assigned"));
    }

    @Override
    public void displaySummary() {
        System.out.println("Out-Patient: " + getFirstName() + " " + getLastName() +
                " | Total Visits: " + visitCount + " | Preferred MD: " + (Helper.isNotNull(preferredDoctorId) ? preferredDoctorId : "N/A"));
    }

    // --- Getters ---

    public int getVisitCount() { return visitCount; }
    public LocalDate getLastVisitDate() { return lastVisitDate; }
    public String getPreferredDoctorId() { return preferredDoctorId; }
}