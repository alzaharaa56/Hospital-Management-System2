package Entity;

import Utils.Helper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Consultant extends Doctor {

    private List<String> consultationTypes;
    private boolean onlineConsultationAvailable;
    private int consultationDuration;

    // Full Constructor
    public Consultant(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender,
                      String phoneNumber, String email, String address, String doctorId, String specialization,
                      String qualification, int experienceYears, String departmentId, double consultationFee,
                      List<String> availableSlots, List<String> assignedPatients,
                      List<String> consultationTypes, boolean onlineConsultationAvailable, int consultationDuration) {

        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address,
                doctorId, specialization, qualification, experienceYears, departmentId,
                consultationFee, availableSlots, assignedPatients);


        setConsultationTypes(consultationTypes);
        setConsultationDuration(consultationDuration);
        this.onlineConsultationAvailable = onlineConsultationAvailable;
    }

    public Consultant() {
        super();
        this.consultationTypes = new ArrayList<>();
    }

    // --- Business Logic & Validation ---

    @Override
    public boolean validate() {

        return super.validate() &&
                consultationDuration > 0 &&
                Helper.isNotNull(consultationTypes) && !consultationTypes.isEmpty();
    }

    public void scheduleConsultation(String consultationType, boolean online) {

        if (Helper.isNull(consultationType) || !consultationTypes.contains(consultationType)) {
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

    public void provideSecondOpinion(String patientCase) {

        if (Helper.isValidString(patientCase)) {
            System.out.println("Dr. " + getLastName() + " is reviewing the case for a second opinion...");
            System.out.println("Case Summary: " + patientCase);
            System.out.println("Status: Review in progress.");
        } else {
            System.out.println("Error: Patient case summary is required for a second opinion.");
        }
    }



    public void setConsultationTypes(List<String> consultationTypes) {

        if (Helper.isNotNull(consultationTypes)) {
            this.consultationTypes = consultationTypes;
        } else {
            this.consultationTypes = new ArrayList<>();
        }
    }

    public void setConsultationDuration(int consultationDuration) {

        if (consultationDuration > 0) {
            this.consultationDuration = consultationDuration;
        } else {
            System.out.println("Error: Consultation duration must be positive. Setting default to 30 mins.");
            this.consultationDuration = 30;
        }
    }

    public void setOnlineConsultationAvailable(boolean onlineConsultationAvailable) {
        this.onlineConsultationAvailable = onlineConsultationAvailable;
    }

    // --- Display Methods ---

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Consultation Types: " + (consultationTypes.isEmpty() ? "None Listed" : consultationTypes));
        System.out.println("Online Consultation: " + (onlineConsultationAvailable ? "Available" : "Not Available"));
        System.out.println("Standard Duration: " + consultationDuration + " minutes");
    }

    @Override
    public void displaySummary() {
        System.out.println("Consultant: Dr. " + getLastName() + " | Specialty: " + (Helper.isNotNull(getSpecialization()) ? getSpecialization() : "N/A") +
                " | Online: " + (onlineConsultationAvailable ? "Yes" : "No"));
    }

    // --- Getters ---

    public List<String> getConsultationTypes() { return consultationTypes; }
    public boolean isOnlineConsultationAvailable() { return onlineConsultationAvailable; }
    public int getConsultationDuration() { return consultationDuration; }
}