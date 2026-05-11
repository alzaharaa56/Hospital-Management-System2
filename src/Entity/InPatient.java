package Entity;


import java.time.LocalDate;
public class InPatient extends Patient {
    private LocalDate admissionDate;
    private String roomNumber;
    private String bedNumber;
    private double dailyCharges;
    public InPatient(String id, String firstName, String lastName, LocalDate dateOfBirth, String gender, String phoneNumber, String email, String address, String patientId, String bloodGroup, String emergencyContact, String insuranceId, String roomNumber, double dailyCharges) {
        super(id,
                firstName,
                lastName,
                dateOfBirth,
                gender,
                phoneNumber,
                email,
                address,
                patientId,
                bloodGroup,
                emergencyContact,
                insuranceId);
        this.admissionDate = LocalDate.now();
        this.roomNumber = roomNumber;
        this.dailyCharges = dailyCharges;
    }
    public double calculateTotalCharges(int days) {
        return days * dailyCharges;
    }
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Status: In-Patient | Room: " + roomNumber);
    }
}
