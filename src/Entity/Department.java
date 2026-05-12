package Entity;

import Behaviour.Displayable;
import Behaviour.Editable;
import java.util.ArrayList;
import java.util.List;

/**
 * Task 3.2: Department implements Displayable and Editable.
 * This class manages hospital departments, including staff and bed resources.
 */
public class Department implements Displayable, Editable {

    // Fields
    private String departmentId;
    private String departmentName;
    private String headDoctorId;
    private List<Doctor> doctors;
    private List<Nurse> nurses;
    private int bedCapacity;
    private int availableBeds;

    // Full Constructor
    public Department(String departmentId, String departmentName, String headDoctorId, int bedCapacity) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.headDoctorId = headDoctorId;
        this.doctors = new ArrayList<>();
        this.nurses = new ArrayList<>();
        this.bedCapacity = bedCapacity;
        this.availableBeds = bedCapacity; // Initially, all beds are available
    }

    // Default Constructor
    public Department() {
        this.doctors = new ArrayList<>();
        this.nurses = new ArrayList<>();
    }

    // --- Task 3.2: Implementation of Displayable ---

    @Override
    public void displayInfo() {
        System.out.println("\n--- Department Detailed Information ---");
        System.out.println("Department ID   : " + departmentId);
        System.out.println("Department Name : " + departmentName);
        System.out.println("Head of Dept ID : " + headDoctorId);
        System.out.println("Staff Count     : Doctors (" + doctors.size() + "), Nurses (" + nurses.size() + ")");
        System.out.println("Bed Status      : " + availableBeds + " available out of " + bedCapacity);
    }

    @Override
    public void displaySummary() {
        System.out.println("Dept: " + departmentName + " | Capacity: " + availableBeds + "/" + bedCapacity);
    }

    // --- Task 3.2: Implementation of Editable ---

    @Override
    public void edit(Object updatedData) {
        if (updatedData instanceof Department) {
            Department dept = (Department) updatedData;
            this.departmentName = dept.departmentName;
            this.headDoctorId = dept.headDoctorId;
            this.bedCapacity = dept.bedCapacity;
            System.out.println("Department information updated successfully.");
        }
    }

    @Override
    public boolean validate() {
        // Ensure name is provided and capacity is not negative
        return departmentName != null && !departmentName.isEmpty() && bedCapacity >= 0;
    }

    // --- Staff Management Methods ---

    /**
     * Assigns a doctor to the department if not already assigned.
     */
    public void assignDoctor(Doctor doctor) {
        if (doctor != null && !doctors.contains(doctor)) {
            doctors.add(doctor);
            System.out.println("Doctor " + doctor.getLastName() + " assigned to " + departmentName);
        } else {
            System.out.println("Assignment failed: Doctor already in department or null.");
        }
    }

    /**
     * Assigns a nurse to the department if not already assigned.
     */
    public void assignNurse(Nurse nurse) {
        if (nurse != null && !nurses.contains(nurse)) {
            nurses.add(nurse);
            System.out.println("Nurse assigned successfully to " + departmentName);
        } else {
            System.out.println("Assignment failed: Nurse already in department or null.");
        }
    }

    /**
     * Updates the count of available beds.
     */
    public void updateBedAvailability(int beds) {
        if (beds >= 0 && beds <= bedCapacity) {
            availableBeds = beds;
            System.out.println("Available beds updated to: " + availableBeds);
        } else {
            System.out.println("Error: Invalid bed count provided.");
        }
    }

    // --- Getters and Setters ---

    public String getDepartmentId() { return departmentId; }
    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }

    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    public String getHeadDoctorId() { return headDoctorId; }
    public void setHeadDoctorId(String headDoctorId) { this.headDoctorId = headDoctorId; }

    public List<Doctor> getDoctors() { return doctors; }
    public void setDoctors(List<Doctor> doctors) { this.doctors = doctors; }

    public List<Nurse> getNurses() { return nurses; }
    public void setNurses(List<Nurse> nurses) { this.nurses = nurses; }

    public int getBedCapacity() { return bedCapacity; }
    public void setBedCapacity(int bedCapacity) { this.bedCapacity = bedCapacity; }

    public int getAvailableBeds() { return availableBeds; }
    public void setAvailableBeds(int availableBeds) { this.availableBeds = availableBeds; }
}