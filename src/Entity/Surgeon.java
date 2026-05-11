package Entity;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
public class Surgeon extends Doctor {

    private int surgeriesPerformed;
    private List<String> surgeryTypes;
    private boolean operationTheatreAccess;


    public Surgeon(String id, String firstName, String lastName, LocalDate dateOfBirth, String gender, String phoneNumber, String email, String address, String doctorId, String specialization, String departmentId, double consultationFee, boolean operationTheatreAccess) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address, doctorId, specialization, departmentId, consultationFee);
        this.operationTheatreAccess = operationTheatreAccess;
        this.surgeryTypes = new ArrayList<>();
        this.surgeriesPerformed = 0;
    }

    public void performSurgery() {
        this.surgeriesPerformed++;
        System.out.println("Surgery performed successfully.");
    }
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Role: Surgeon | Surgeries: " + surgeriesPerformed);
    }
}