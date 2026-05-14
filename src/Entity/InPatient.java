package Entity;

import java.time.LocalDate;


public class InPatient extends Patient {

    private LocalDate admissionDate;
    private LocalDate dischargeDate;
    private String roomNumber;
    private String bedNumber;
    private String admittingDoctorId;
    private double dailyCharges;



    public InPatient() {
        super();
    }

    public InPatient(String id, String firstName, LocalDate dateOfBirth, String lastName,
                     String gender, String phoneNumber, String email, String address,
                     String patientId, LocalDate admissionDate, String roomNumber, double dailyCharges) {

        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address, patientId);
        this.admissionDate = admissionDate;
        this.roomNumber = roomNumber;
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
        System.out.println("Admission Date: " + admissionDate);
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Daily Charges: OMR " + dailyCharges);
    }
}