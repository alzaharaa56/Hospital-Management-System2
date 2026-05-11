package Entity;

import java.util.List;

public class Department {

    // Fields
    private String departmentId;
    private String departmentName;
    private String headDoctorId;
    private List<Doctor> doctors;
    private List<Nurse> nurses;
    private int bedCapacity;
    private int availableBeds;


    // full constructor
    public Department(String departmentId, String departmentName, String headDoctorId, List<Doctor> doctors, List<Nurse> nurses, int bedCapacity, int availableBeds) {

        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.headDoctorId = headDoctorId;
        this.doctors = doctors;
        this.nurses = nurses;
        this.bedCapacity = bedCapacity;
        this.availableBeds = availableBeds;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getHeadDoctorId() {
        return headDoctorId;
    }

    public void setHeadDoctorId(String headDoctorId) {
        this.headDoctorId = headDoctorId;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public void setNurses(List<Nurse> nurses) {
        this.nurses = nurses;
    }

    public int getBedCapacity() {
        return bedCapacity;
    }

    public void setBedCapacity(int bedCapacity) {
        this.bedCapacity = bedCapacity;
    }

    public int getAvailableBeds() {
        return availableBeds;
    }

    public void setAvailableBeds(int availableBeds) {
        this.availableBeds = availableBeds;
    }

    public void displayInfo() {

        System.out.println("Department ID: " + departmentId);
        System.out.println("Department Name: " + departmentName);
        System.out.println("Head Doctor ID: " + headDoctorId);
        System.out.println("Doctors: " + doctors);
        System.out.println("Nurses: " + nurses);
        System.out.println("Bed Capacity: " + bedCapacity);
        System.out.println("Available Beds: " + availableBeds);
    }

    public void assignDoctor(Doctor doctor) {

        if (!doctors.contains(doctor)) {
            doctors.add(doctor);
            System.out.println("Doctor assigned successfully.");
        } else {
            System.out.println("Doctor already assigned.");
        }
    }


    // Assign nurse
    public void assignNurse(Nurse nurse) {

        if (!nurses.contains(nurse)) {
            nurses.add(nurse);
            System.out.println("Nurse assigned successfully.");
        } else {
            System.out.println("Nurse already assigned.");
        }
    }

    public String getDepartmentId() {
        return null;
    }

    public <E> List<E> getDoctors() {
        return null;
    }

    public <E> List<E> getNurses() {
        return null;
    }

}

