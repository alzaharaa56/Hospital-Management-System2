package Entity;

import java.time.LocalDate;

/**
 * Task 3.2: OutPatient inherits from Patient.
 * Represents patients who visit the clinic without being admitted.
 */
public class OutPatient extends Patient {

    private int visitCount;
    private LocalDate lastVisitDate;
    private String preferredDoctorId;

    // Full Constructor aligned with our standard Patient structure
    public OutPatient(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender,
                      String phoneNumber, String email, String address, String patientId, String bloodGroup,
                      LocalDate registrationDate, String insuranceId, int visitCount,
                      LocalDate lastVisitDate, String preferredDoctorId) {

        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address,
                patientId, bloodGroup, registrationDate, insuranceId);

        this.visitCount = visitCount;
        this.lastVisitDate = lastVisitDate;
        this.preferredDoctorId = preferredDoctorId;
    }

    // Default Constructor
    public OutPatient() {
        super();
    }

    // --- Task 3.2: Implementation of Displayable ---

    @Override
    public void displayInfo() {
        super.displayInfo(); // Displays Person and Patient details
        System.out.println("Visit Count        : " + visitCount);
        System.out.println("Last Visit Date    : " + (lastVisitDate == null ? "No previous visits" : lastVisitDate));
        System.out.println("Preferred Doctor ID: " + (preferredDoctorId == null ? "None assigned" : preferredDoctorId));
    }

    @Override
    public void displaySummary() {
        System.out.println("Out-Patient: " + getFirstName() + " " + getLastName() +
                " | Total Visits: " + visitCount + " | Preferred MD: " + preferredDoctorId);
    }

    // --- Task 3.2: Validation logic ---

    @Override
    public boolean validate() {
        // Inherits validation from Person + ensures visit count isn't negative
        return super.validate() && visitCount >= 0;
    }

    // --- Business Logic Methods ---

    /**
     * Schedules a follow-up appointment date.
     */
    public void scheduleFollowUp(LocalDate followUpDate) {
        if (followUpDate.isBefore(LocalDate.now())) {
            System.out.println("Error: Follow-up date cannot be in the past.");
            return;
        }
        System.out.println("Follow-up scheduled successfully.");
        System.out.println("Follow-up Date : " + followUpDate);
    }

    /**
     * Increments the visit count and updates the last visit timestamp to today.
     */
    public void updateVisitCount() {
        this.visitCount++;
        this.lastVisitDate = LocalDate.now();
        System.out.println("Success: Visit count incremented to " + visitCount);
    }

    // --- Getters and Setters ---

    public int getVisitCount() { return visitCount; }
    public void setVisitCount(int visitCount) { this.visitCount = visitCount; }

    public LocalDate getLastVisitDate() { return lastVisitDate; }
    public void setLastVisitDate(LocalDate lastVisitDate) { this.lastVisitDate = lastVisitDate; }

    public String getPreferredDoctorId() { return preferredDoctorId; }
    public void setPreferredDoctorId(String preferredDoctorId) { this.preferredDoctorId = preferredDoctorId; }
}