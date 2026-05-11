package Entity.Doctor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Surgeon extends Doctor {

    private int surgeriesPerformed;
    private List<String> surgeryTypes;
    private boolean operationTheatreAccess;

    public Surgeon(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender, String phoneNumber, String email, String address, String doctorId, String specialization, String qualification, int experienceYears, String departmentId, double consultationFee, List<String> availableSlots, List<String> assignedPatients, int surgeriesPerformed, List<String> surgeryTypes, boolean operationTheatreAccess) {

        super(id, firstName, dateOfBirth, lastName, gender, phoneNumber, email, address, doctorId, specialization, qualification, experienceYears, departmentId, consultationFee, availableSlots, assignedPatients);

        this.surgeriesPerformed = surgeriesPerformed;
        this.surgeryTypes = new ArrayList<>();
        this.operationTheatreAccess = operationTheatreAccess;
    }

    public int getSurgeriesPerformed() {
        return surgeriesPerformed;
    }

    public void setSurgeriesPerformed(int surgeriesPerformed) {
        this.surgeriesPerformed = surgeriesPerformed;
    }

    public List<String> getSurgeryTypes() {
        return surgeryTypes;
    }

    public void setSurgeryTypes(List<String> surgeryTypes) {
        this.surgeryTypes = surgeryTypes;
    }

    public boolean isOperationTheatreAccess() {
        return operationTheatreAccess;
    }

    public void setOperationTheatreAccess(boolean operationTheatreAccess) {
        this.operationTheatreAccess = operationTheatreAccess;
    }

    //performSurgery()

    public boolean  performSurgery(String surgeryType){

        if(!surgeryTypes.contains(surgeryType)){
            return false;
        }

        if(!operationTheatreAccess){
            return false;
        }

        surgeriesPerformed++;
        return true;
    }

    // updateSurgeryCount()
    public void updateSurgeryCount(int count) {

        if (count < 0) {
            System.out.println("Invalid surgery count.");
            return;
        }

        this.surgeriesPerformed = count;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("surgeries Performed : " + surgeriesPerformed);
        System.out.println("surgery Types : " + surgeryTypes);
        System.out.println("operation Theatre Access : " + operationTheatreAccess);



    }
}