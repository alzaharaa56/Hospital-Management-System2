package Entity;

import java.time.LocalDate;
import java.util.List;


import java.time.LocalDate;
import java.util.List;

public class EmergencyPatient extends Patient {

    String emergencyType;
    String arrivalMode;
    Integer triageLevel;
    Boolean admittedViaER;

    public EmergencyPatient(String id, String firstName, String lastName, LocalDate dateOfBirth, String gender, String phoneNumber, String email, String address, String bloodGroup, String emergencyContact, LocalDate registrationDate, String insuranceId, List<String> allergies,
                            String emergencyType , String arrivalMode , Integer triageLevel , Boolean admittedViaER) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address, bloodGroup, emergencyContact, registrationDate, insuranceId, allergies);
        this.emergencyType = emergencyType;
        this.arrivalMode = arrivalMode;
        this.triageLevel = triageLevel;
        this.admittedViaER = admittedViaER;
    }


    @Override
    public void displayInfo() {
        super.displayInfo();

        System.out.println("Emergency Type: " + emergencyType);
        System.out.println("Arrival Mode: " + arrivalMode);
        System.out.println("Triage Level: " + triageLevel);
        System.out.println("Admitted Via ER: " + admittedViaER);

    }
}

