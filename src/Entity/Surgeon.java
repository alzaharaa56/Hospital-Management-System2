package Entity;

import Entity.Doctor;
import Entity.Patient;

import java.time.LocalDate;
import java.util.List;

// Surgeon Class
class Surgeon extends Doctor {
    private int surgeriesPerformed;
    private List<String> surgeryTypes;
    private boolean operationTheatreAccess;

    public Surgeon(String id, String firstName, String lastName, LocalDate dob,
                   String gender, String phone, String email, String address,
                   String doctorId, String specialization, String qualification,
                   int experienceYears, String departmentId, double consultationFee,
                   List<String> slots, List<Patient> patients,
                   int surgeriesPerformed, List<String> surgeryTypes, boolean access) {
        super(id,
                experienceYears
        );
        this.surgeriesPerformed = surgeriesPerformed;
        this.surgeryTypes = surgeryTypes;
        this.operationTheatreAccess = access;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Surgeries Performed: " + surgeriesPerformed);
        System.out.println("Surgery Types: " + surgeryTypes);
        System.out.println("Operation Theatre Access: " + operationTheatreAccess);
    }

    public void performSurgery(String type) {
        if (operationTheatreAccess && surgeryTypes.contains(type)) {
            System.out.println("Performing surgery: " + type);
            updateSurgeryCount();
        } else {
            System.out.println("Cannot perform surgery: " + type);
        }
    }

    public void updateSurgeryCount() {
        surgeriesPerformed++;
    }
}

