package Entity;

import java.time.LocalDate;

public class OutPatient extends Patient {
    private int visitCount;
    private LocalDate lastVisitDate;
    private final String preferredDoctorId;

    public OutPatient(String id, String fName, String lName, LocalDate dob, String gender, String phone,
                      String email, String address, String patientId, String bloodGroup,
                      String emergencyContact, String prefDoctorId) {
        super(id, fName, lName, dob, gender, phone, email, address, patientId, bloodGroup, emergencyContact);
        this.visitCount = 0;
        this.preferredDoctorId = prefDoctorId;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Visit Count: " + visitCount + " | Preferred Doctor: " + preferredDoctorId);
        if (lastVisitDate != null) System.out.println("Last Visit: " + lastVisitDate);
    }

    public void updateVisitCount() {
        this.visitCount++;
        this.lastVisitDate = LocalDate.now();
    }

    public void scheduleFollowUp(LocalDate date) {
        System.out.println("Follow-up scheduled for: " + date + " with Doctor " + preferredDoctorId);
    }
}
