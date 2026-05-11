package Entity;

import java.time.LocalDate;
import java.util.List;

class Consultant extends Doctor {
    private List<String> consultationTypes;
    private boolean onlineConsultationAvailable;
    private int consultationDuration;

    public Consultant(String id, String firstName, String lastName, LocalDate dob,
                      String gender, String phone, String email, String address,
                      String doctorId, String specialization, String qualification,
                      int experienceYears, String departmentId, double consultationFee,
                      List<String> slots, List<Patient> patients,
                      List<String> consultationTypes, boolean online, int duration) {
        super(id,
                experienceYears
        );
        this.consultationTypes = consultationTypes;
        this.onlineConsultationAvailable = online;
        this.consultationDuration = duration;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Consultation Types: " + consultationTypes);
        System.out.println("Online Available: " + onlineConsultationAvailable);
        System.out.println("Duration: " + consultationDuration + " minutes");
    }

    public void scheduleConsultation(String patientName, String type) {
        System.out.println("Consultation scheduled with " + patientName + " for " + type);
    }

    public void provideSecondOpinion(String patientName, String diagnosis) {
        System.out.println("Second opinion for " + patientName + " on " + diagnosis);
    }
}