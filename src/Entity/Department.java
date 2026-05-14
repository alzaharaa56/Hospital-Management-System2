package Entity;

import Behavior.Displayable;
import Behavior.Manageable;
import Utils.HelperUtils;

import java.util.ArrayList;
import java.util.List;

public class Department implements Displayable {

    private String departmentId;
    private String departmentName;
    private String headDoctorId;
    private List<Doctor> doctors;
    private List<Nurse> nurses;
    private int bedCapacity;
    private int availableBeds;

    public Department(String departmentId, String departmentName, String headDoctorId, int bedCapacity, int availableBeds) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.headDoctorId = headDoctorId;
        this.doctors = (doctors != null) ? doctors : new ArrayList<>();
        this.nurses = (nurses != null) ? nurses : new ArrayList<>();
        this.bedCapacity = bedCapacity;
        this.availableBeds = availableBeds;
    }

    public Department(String departmentId, String departmentName, int bedCapacity) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.bedCapacity = bedCapacity;
        this.availableBeds = bedCapacity;
        this.doctors = new ArrayList<>();
        this.nurses = new ArrayList<>();
    }


    @Override
    public void displayInfo() {
        System.out.println("\n--- Department Details ---");
        System.out.println("ID: " + departmentId + " | Name: " + departmentName);
        System.out.println("Head Doctor ID: " + (headDoctorId != null ? headDoctorId : "Not Assigned"));
        System.out.println("Staff Count: Doctors (" + doctors.size() + "), Nurses (" + nurses.size() + ")");
        System.out.println("Beds: " + availableBeds + "/" + bedCapacity + " available");
    }

    @Override
    public void displaySummary() {
        System.out.printf("Dept ID: %-10s | Name: %-15s | Available Beds: %-3d\n",
                departmentId, departmentName, availableBeds);
    }


    public void assignDoctor(Doctor doctor) {
        if (HelperUtils.isNull(doctor)) {
            System.out.println("Error: Invalid doctor.");
            return;
        }

        if (!doctors.contains(doctor)) {
            doctors.add(doctor);
            System.out.println("Success: Doctor " + doctor.getFirstName() + " assigned to " + departmentName);
        } else {
            System.out.println("Notice: Doctor already in this department.");
        }
    }

    public void assignNurse(Nurse nurse) {
        if (HelperUtils.isNull(nurse)) {
            System.out.println("Error: Invalid nurse.");
            return;
        }

        if (!nurses.contains(nurse)) {
            nurses.add(nurse);
            System.out.println("Success: Nurse assigned to " + departmentName);
        } else {
            System.out.println("Notice: Nurse already in this department.");
        }
    }

    public void updateBedAvailability(int beds) {
        if (beds >= 0 && beds <= bedCapacity) {
            this.availableBeds = beds;
            System.out.println("Success: Bed availability updated for " + departmentName);
        } else {
            System.out.println("Error: Invalid bed count (Must be between 0 and " + bedCapacity + ").");
        }
    }

    public Object getDepartmentId() {
        return this.departmentId;
    }

    public Manageable getDoctors() {
        return (Manageable) this.doctors.get(0);
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setHeadDoctorId(String headDoctorId) {
        this.headDoctorId = headDoctorId;
    }

    public String getHeadDoctorId() {
        return headDoctorId;
    }

    public void setBedCapacity(int bedCapacity) {
        this.bedCapacity = bedCapacity;
    }

    public int getBedCapacity() {
        return bedCapacity;
    }

    public void setAvailableBeds(int availableBeds) {
        this.availableBeds = availableBeds;
    }

    public int getAvailableBeds() {
        return availableBeds;
    }

}