package Entity;

import Utils.Helper;
import java.time.LocalDate;
import java.util.List;

public class GeneralPractitioner extends Doctor {

    private boolean walkinAvailable;
    private boolean homeVisitAvailable;
    private boolean vaccinationCertified;

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

    public GeneralPractitioner() {
        super();
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Walk-in Available      : " + (walkinAvailable ? "Yes" : "No"));
        System.out.println("Home Visit Available   : " + (homeVisitAvailable ? "Yes" : "No"));
        System.out.println("Vaccination Certified : " + (vaccinationCertified ? "Yes" : "No"));
    }

    @Override
    public void displaySummary() {
        System.out.println("GP: Dr. " + getLastName() + " | Specialty: " + getSpecialization() +
                " | Walk-in: " + (walkinAvailable ? "Enabled" : "Disabled"));
    }

    @Override
    public boolean validate() {
        return super.validate() && Helper.isValidString(getSpecialization());
    }

    public void scheduleHomeVisit(String patientName, String address) {
        if (!homeVisitAvailable) {
            System.out.println("Error: Home visits are not offered by Dr. " + getLastName());
            return;
        }
        System.out.println("Home visit scheduled successfully.");
        System.out.println("Patient Name : " + patientName);
        System.out.println("Address      : " + address);
    }

    public void administerVaccine(String patientName, String vaccineName) {
        if (!vaccinationCertified) {
            System.out.println("Error: Dr. " + getLastName() + " is not certified to administer vaccinations.");
            return;
        }
        System.out.println("Vaccine administered successfully.");
        System.out.println("Patient Name : " + patientName);
        System.out.println("Vaccine      : " + vaccineName);
    }

    public boolean isWalkinAvailable() { return walkinAvailable; }
    public void setWalkinAvailable(boolean walkinAvailable) { this.walkinAvailable = walkinAvailable; }

    public boolean isVaccinationCertified() { return vaccinationCertified; }
    public void setVaccinationCertified(boolean vaccinationCertified) { this.vaccinationCertified = vaccinationCertified; }

    public boolean isHomeVisitAvailable() { return homeVisitAvailable; }
    public void setHomeVisitAvailable(boolean homeVisitAvailable) { this.homeVisitAvailable = homeVisitAvailable; }
}