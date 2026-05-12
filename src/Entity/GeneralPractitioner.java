package Entity;

import java.time.LocalDate;
import java.util.List;

/**
 * Task 3.2: GeneralPractitioner inherits from Doctor.
 * Represents a primary care physician with walk-in and home visit capabilities.
 */
public class GeneralPractitioner extends Doctor {

    private boolean walkinAvailable;
    private boolean homeVisitAvailable;
    private boolean vaccinationCertified;

    // Full Constructor
    public GeneralPractitioner(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender,
                               String phoneNumber, String email, String address, String doctorId, String specialization,
                               String qualification, int experienceYears, String departmentId, double consultationFee,
                               List<String> availableSlots, List<String> assignedPatients, boolean walkinAvailable,
                               boolean homeVisitAvailable, boolean vaccinationCertified) {

        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address, doctorId,
                specialization, qualification, experienceYears, departmentId, consultationFee,
                availableSlots, assignedPatients);

        this.walkinAvailable = walkinAvailable;
        this.homeVisitAvailable = homeVisitAvailable;
        this.vaccinationCertified = vaccinationCertified;
    }

    // Default Constructor
    public GeneralPractitioner() {
        super();
    }

    // --- Task 3.2: Implementation of Displayable ---

    @Override
    public void displayInfo() {
        super.displayInfo(); // Displays details from Person and Doctor
        System.out.println("Walk-in Available      : " + (walkinAvailable ? "Yes" : "No"));
        System.out.println("Home Visit Available   : " + (homeVisitAvailable ? "Yes" : "No"));
        System.out.println("Vaccination Certified : " + (vaccinationCertified ? "Yes" : "No"));
    }

    @Override
    public void displaySummary() {
        System.out.println("GP: Dr. " + getLastName() + " | Specialty: " + getSpecialization() +
                " | Walk-in: " + (walkinAvailable ? "Enabled" : "Disabled"));
    }

    // --- Task 3.2: Validation ---

    @Override
    public boolean validate() {
        // Inherits validation from Person and ensures doctor has a specialization
        return super.validate() && getSpecialization() != null;
    }

    // --- Business Logic Methods ---

    /**
     * Schedules a home visit for a specific patient.
     */
    public void scheduleHomeVisit(String patientName, String address) {
        if (!homeVisitAvailable) {
            System.out.println("Error: Home visits are not offered by Dr. " + getLastName());
            return;
        }
        System.out.println("Home visit scheduled successfully.");
        System.out.println("Patient Name : " + patientName);
        System.out.println("Address      : " + address);
    }

    /**
     * Records the administration of a vaccine.
     */
    public void administerVaccine(String patientName, String vaccineName) {
        if (!vaccinationCertified) {
            System.out.println("Error: Dr. " + getLastName() + " is not certified to administer vaccinations.");
            return;
        }
        System.out.println("Vaccine administered successfully.");
        System.out.println("Patient Name : " + patientName);
        System.out.println("Vaccine      : " + vaccineName);
    }

    // --- Getters and Setters ---

    public boolean isWalkinAvailable() { return walkinAvailable; }
    public void setWalkinAvailable(boolean walkinAvailable) { this.walkinAvailable = walkinAvailable; }

    public boolean isVaccinationCertified() { return vaccinationCertified; }
    public void setVaccinationCertified(boolean vaccinationCertified) { this.vaccinationCertified = vaccinationCertified; }

    public boolean isHomeVisitAvailable() { return homeVisitAvailable; }
    public void setHomeVisitAvailable(boolean homeVisitAvailable) { this.homeVisitAvailable = homeVisitAvailable; }
}