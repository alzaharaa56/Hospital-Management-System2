package Entity;

import Behavior.Displayable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Consultant extends Doctor implements Displayable {

    private List<String> consultationTypes;
    private boolean onlineConsultationAvailable;
    private int consultationDuration;

    public Consultant(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender, String phoneNumber, String email, String address, String doctorId, String specialization, String qualification, int experienceYears, String departmentId, double consultationFee, List<String> availableSlots, List<Patient> assignedPatients, List<String> consultationTypes, boolean onlineConsultationAvailable, int consultationDuration) {

        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address, doctorId, specialization, qualification, experienceYears, departmentId, consultationFee, availableSlots, assignedPatients);

        this.consultationTypes = new ArrayList<>();
        this.onlineConsultationAvailable = onlineConsultationAvailable;
        this.consultationDuration = consultationDuration;
    }

    public List<String> getConsultationTypes() {
        return consultationTypes;
    }

    public void setConsultationTypes(List<String> consultationTypes) {
        this.consultationTypes = consultationTypes;
    }

    public boolean isOnlineConsultationAvailable() {
        return onlineConsultationAvailable;
    }

    public void setOnlineConsultationAvailable(boolean onlineConsultationAvailable) {
        this.onlineConsultationAvailable = onlineConsultationAvailable;
    }

    public int getConsultationDuration() {
        return consultationDuration;
    }

    public void setConsultationDuration(int consultationDuration) {
        this.consultationDuration = consultationDuration;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println(" consultation Types : " + consultationTypes);
        System.out.println(" online Consultation Available : " + onlineConsultationAvailable);
        System.out.println(" consultation Duration : " + consultationDuration);


    }

    @Override
    public void displaySummary() {

    }

    // scheduleConsultation()

    public void scheduleConsultation(String consultationType, boolean online) {


        if (HelperUtils.isNull(consultationType)) {
            System.out.println("Invalid consultation type.");
            return;
        }

        if (!consultationTypes.contains(consultationType)) {
            System.out.println("Consultation type not available.");
            return;
        }

        if(online && !onlineConsultationAvailable){
            System.out.println("Online consultation is not available.");
            return;
        }

        System.out.println("Consultation scheduled successfully.");
        System.out.println("Type      : " + consultationType);
        System.out.println("Mode      : " + (online ? "Online" : "Offline"));
        System.out.println("Duration  : " + consultationDuration + " minutes");

    }


    public void provideSecondOpinion(String patientCase) {


        if (!HelperUtils.isValidString(patientCase)) {
            System.out.println("Invalid patient case.");
            return;
        }

        System.out.println("Providing second opinion for case:");
        System.out.println(patientCase);
    }


}