package Entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class InPatient extends Patient {
    private LocalDate admissionDate;
    private LocalDate dischargeDate;
    private String roomNumber;
    private String bedNumber;
    private String admittingDoctorId;
    private double dailyCharges;

    public InPatient(String id, String fName, String lName, LocalDate dob, String gender, String phone,
                     String email, String address, String patientId, String bloodGroup,
                     String emergencyContact, String room, String bed, double charges) {
        super(id, fName, lName, dob, gender, phone, email, address, patientId, bloodGroup, emergencyContact);
        this.admissionDate = LocalDate.now();
        this.roomNumber = room;
        this.bedNumber = bed;
        this.dailyCharges = charges;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("Room: %s | Bed: %s | Admission: %s | Admitting Doctor: %s\n",
                roomNumber, bedNumber, admissionDate, admittingDoctorId);
    }

    public long calculateStayDuration() {
        LocalDate end = (dischargeDate != null) ? dischargeDate : LocalDate.now();
        return ChronoUnit.DAYS.between(admissionDate, end);
    }

    public double calculateTotalCharges() {
        return calculateStayDuration() * dailyCharges;
    }

    public void setDischargeDate(LocalDate dischargeDate) {
        this.dischargeDate = dischargeDate;
    }
}
