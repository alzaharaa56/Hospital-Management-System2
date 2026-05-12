package Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Surgeon extends Doctor {

    private int surgeriesPerformed;
    private List<String> surgeryTypes;
    private boolean operationTheatreAccess;


    public Surgeon(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender,
                   String phoneNumber, String email, String address, String doctorId, String specialization,
                   String qualification, int experienceYears, String departmentId, double consultationFee,
                   List<String> availableSlots, List<String> assignedPatients,
                   int surgeriesPerformed, List<String> surgeryTypes, boolean operationTheatreAccess) {


        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address,
                doctorId, specialization, qualification, experienceYears, departmentId,
                consultationFee, availableSlots, assignedPatients);

        this.surgeriesPerformed = surgeriesPerformed;

        this.surgeryTypes = (surgeryTypes != null) ? surgeryTypes : new ArrayList<>();
        this.operationTheatreAccess = operationTheatreAccess;
    }

    // Getters and Setters
    public int getSurgeriesPerformed() { return surgeriesPerformed; }
    public void setSurgeriesPerformed(int surgeriesPerformed) { this.surgeriesPerformed = surgeriesPerformed; }

    public List<String> getSurgeryTypes() { return surgeryTypes; }
    public void setSurgeryTypes(List<String> surgeryTypes) { this.surgeryTypes = surgeryTypes; }

    public boolean isOperationTheatreAccess() { return operationTheatreAccess; }
    public void setOperationTheatreAccess(boolean operationTheatreAccess) { this.operationTheatreAccess = operationTheatreAccess; }


    public boolean performSurgery(String surgeryType) {

        if (operationTheatreAccess && surgeryTypes.contains(surgeryType)) {
            surgeriesPerformed++;
            System.out.println("Surgery '" + surgeryType + "' performed successfully by Dr. " + getFirstName());
            return true;
        } else {
            System.out.println("Surgery failed: Access denied or surgery type not supported.");
            return false;
        }
    }


    public void updateSurgeryCount(int count) {
        if (count >= 0) {
            this.surgeriesPerformed = count;
            System.out.println("Surgery count updated to: " + count);
        } else {
            System.out.println("Invalid surgery count.");
        }
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Surgeries Performed: " + surgeriesPerformed);
        System.out.println("Surgery Specializations: " + surgeryTypes);
        System.out.println("Operation Theatre Access: " + (operationTheatreAccess ? "Granted" : "Denied"));
    }
}