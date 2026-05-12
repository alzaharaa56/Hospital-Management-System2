package Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Consultant extends Doctor {

    private List<String> consultationTypes;
    private boolean onlineConsultationAvailable;
    private int consultationDuration; // minutes

    public Consultant(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender,
                      String phoneNumber, String email, String address, String doctorId, String specialization,
                      String qualification, int experienceYears, String departmentId, double consultationFee,
                      List<String> availableSlots, List<String> assignedPatients,
                      List<String> consultationTypes, boolean onlineConsultationAvailable, int consultationDuration) {

        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address,
                doctorId, specialization, qualification, experienceYears, departmentId,
                consultationFee, availableSlots, assignedPatients);


        this.consultationTypes = (consultationTypes != null) ? consultationTypes : new ArrayList<>();
        this.onlineConsultationAvailable = onlineConsultationAvailable;
        this.consultationDuration = consultationDuration;
    }

    // Getters and Setters
    public List<String> getConsultationTypes() { return consultationTypes; }
    public void setConsultationTypes(List<String> consultationTypes) { this.consultationTypes = consultationTypes; }

    public boolean isOnlineConsultationAvailable() { return onlineConsultationAvailable; }
    public void setOnlineConsultationAvailable(boolean onlineConsultationAvailable) { this.onlineConsultationAvailable = onlineConsultationAvailable; }

    public int getConsultationDuration() { return consultationDuration; }
    public void setConsultationDuration(int consultationDuration) { this.consultationDuration = consultationDuration; }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Consultation Types: " + consultationTypes);
        System.out.println("Online Consultation: " + (onlineConsultationAvailable ? "Available" : "Not Available"));
        System.out.println("Standard Duration: " + consultationDuration + " minutes");
    }

    // Method: scheduleConsultation
    public void scheduleConsultation(String consultationType, boolean online) {
        if (!consultationTypes.contains(consultationType)) {
            System.out.println("Error: Consultation type '" + consultationType + "' is not offered by this consultant.");
            return;
        }

        if (online && !onlineConsultationAvailable) {
            System.out.println("Error: Online consultation is not supported by Dr. " + getLastName());
            return;
        }

        System.out.println("Consultation scheduled successfully.");
        System.out.println("Type: " + consultationType + " | Mode: " + (online ? "Online" : "In-Person"));
    }

    // Method: provideSecondOpinion
    public void provideSecondOpinion(String patientCase) {
        System.out.println("Dr. " + getLastName() + " is reviewing the case for a second opinion...");
        System.out.println("Case Summary: " + patientCase);
        System.out.println("Status: Review in progress.");
    }
}