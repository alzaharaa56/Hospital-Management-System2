package Entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class InPatient extends Patient{

    private LocalDate admissionDate;
    private LocalDate dischargeDate;
    private String roomNumber;
    private String bedNumber;
    private String admittingDoctorId;
    private double dailyCharges;

    public InPatient(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender, String phoneNumber, String email, String address, String patientId, String bloodGroup, List<String> allergies, String emergencyContact, LocalDate registrationDate, List<MedicalRecord> medicalRecords, String insuranceId, List<Appointment> appointments, LocalDate admissionDate, LocalDate dischargeDate, String roomNumber, String bedNumber, String admittingDoctorId, double dailyCharges) {
        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address, patientId, bloodGroup, allergies, emergencyContact, registrationDate, medicalRecords, insuranceId, appointments);
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.roomNumber = roomNumber;
        this.bedNumber = bedNumber;
        this.admittingDoctorId = admittingDoctorId;
        this.dailyCharges = dailyCharges;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public LocalDate getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(LocalDate dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    public String getAdmittingDoctorId() {
        return admittingDoctorId;
    }

    public void setAdmittingDoctorId(String admittingDoctorId) {
        this.admittingDoctorId = admittingDoctorId;
    }

    public double getDailyCharges() {
        return dailyCharges;
    }

    public void setDailyCharges(double dailyCharges) {
        this.dailyCharges = dailyCharges;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();

        System.out.println("admission Date : " + admissionDate);
        System.out.println("discharge Date :"+ dischargeDate);
        System.out.println("room Number: " + roomNumber);
        System.out.println("bedNumber :" + bedNumber);
        System.out.println("admitting Doctor Id : "+ admittingDoctorId);
        System.out.println("daily Charges: "+ dailyCharges);
        System.out.println("Stay Duration      : " + calculateStayDuration() + " days");
        System.out.println("Total Charges      : " + calculateTotalCharges());
    }

    //calculate Stay Duration
    public long  calculateStayDuration(){

        if (admissionDate == null || dischargeDate == null) {
            return 0;
        }

        return ChronoUnit.DAYS.between(admissionDate, dischargeDate);

    }

    // calculateTotalCharges()
    public double calculateTotalCharges() {

        return calculateStayDuration() * dailyCharges;
    }
}