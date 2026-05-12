package Entity;

import Behaviour.Displayable;
import Behaviour.Editable;
import Utils.Helper;
import java.util.ArrayList;
import java.util.List;

public class Department implements Displayable, Editable {

    private String departmentId;
    private String departmentName;
    private String headDoctorId;
    private List<Doctor> doctors;
    private List<Nurse> nurses;
    private int bedCapacity;
    private int availableBeds;

    // Full Constructor
    public Department(String departmentId, String departmentName, String headDoctorId, int bedCapacity) {
        this.departmentId = Helper.isNotNull(departmentId) ? departmentId : Helper.generateId("DEPT");


        setDepartmentName(departmentName);
        setHeadDoctorId(headDoctorId);
        setBedCapacity(bedCapacity);

        this.doctors = new ArrayList<>();
        this.nurses = new ArrayList<>();
        this.availableBeds = this.bedCapacity;
    }

    public Department() {
        this.departmentId = Helper.generateId("DEPT");
        this.doctors = new ArrayList<>();
        this.nurses = new ArrayList<>();
    }



    @Override
    public void displayInfo() {
        System.out.println("\n--- Department Detailed Information ---");
        System.out.println("Department ID   : " + departmentId);
        System.out.println("Department Name : " + (Helper.isNotNull(departmentName) ? departmentName : "N/A"));
        System.out.println("Head of Dept ID : " + (Helper.isNotNull(headDoctorId) ? headDoctorId : "Not Assigned"));
        System.out.println("Staff Count     : Doctors (" + doctors.size() + "), Nurses (" + nurses.size() + ")");
        System.out.println("Bed Status      : " + availableBeds + " available out of " + bedCapacity);
    }

    @Override
    public void displaySummary() {
        System.out.println("Dept: " + (Helper.isNotNull(departmentName) ? departmentName : "Unknown") +
                " | Capacity: " + availableBeds + "/" + bedCapacity);
    }



    @Override
    public void edit(Object updatedData) {
        if (Helper.isNotNull(updatedData) && updatedData instanceof Department) {
            Department dept = (Department) updatedData;
            setDepartmentName(dept.departmentName);
            setHeadDoctorId(dept.headDoctorId);
            setBedCapacity(dept.bedCapacity);
            System.out.println("Department information updated successfully.");
        }
    }

    @Override
    public boolean validate() {

        return Helper.isValidString(departmentName) && bedCapacity >= 0;
    }



    public void assignDoctor(Doctor doctor) {
        if (Helper.isNotNull(doctor) && !doctors.contains(doctor)) {
            doctors.add(doctor);
            System.out.println("Doctor " + doctor.getLastName() + " assigned to " + departmentName);
        } else {
            System.out.println("Assignment failed: Doctor already in department or data is invalid.");
        }
    }

    public void assignNurse(Nurse nurse) {
        if (Helper.isNotNull(nurse) && !nurses.contains(nurse)) {
            nurses.add(nurse);
            System.out.println("Nurse assigned successfully to " + departmentName);
        } else {
            System.out.println("Assignment failed: Nurse already in department or data is invalid.");
        }
    }



    public void updateBedAvailability(int beds) {

        if (beds >= 0 && beds <= bedCapacity) {
            availableBeds = beds;
            System.out.println("Available beds updated to: " + availableBeds);
        } else {
            System.out.println("Error: Invalid bed count provided.");
        }
    }



    public void setDepartmentId(String departmentId) {
        if (Helper.isValidString(departmentId)) {
            this.departmentId = departmentId;
        }
    }

    public void setDepartmentName(String departmentName) {
        if (Helper.isValidString(departmentName)) {
            this.departmentName = departmentName;
        }
    }

    public void setHeadDoctorId(String headDoctorId) {
        if (Helper.isValidString(headDoctorId)) {
            this.headDoctorId = headDoctorId;
        }
    }

    public void setBedCapacity(int bedCapacity) {
        if (bedCapacity >= 0) {
            this.bedCapacity = bedCapacity;

            if (this.availableBeds > bedCapacity) {
                this.availableBeds = bedCapacity;
            }
        }
    }

    public void setDoctors(List<Doctor> doctors) {
        if (Helper.isNotNull(doctors)) {
            this.doctors = doctors;
        }
    }

    public void setNurses(List<Nurse> nurses) {
        if (Helper.isNotNull(nurses)) {
            this.nurses = nurses;
        }
    }

    public void setAvailableBeds(int availableBeds) {
        if (availableBeds >= 0 && availableBeds <= this.bedCapacity) {
            this.availableBeds = availableBeds;
        }
    }

    // --- Getters ---

    public String getDepartmentId() { return departmentId; }
    public String getDepartmentName() { return departmentName; }
    public String getHeadDoctorId() { return headDoctorId; }
    public List<Doctor> getDoctors() { return doctors; }
    public List<Nurse> getNurses() { return nurses; }
    public int getBedCapacity() { return bedCapacity; }
    public int getAvailableBeds() { return availableBeds; }
}