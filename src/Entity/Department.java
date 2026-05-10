package Entity;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String departmentId;
    private String departmentName;
    private String headDoctorId;
    private List<Doctor> doctors;
    private List<Nurse> nurses;
    private int bedCapacity;
    private int availableBeds;

    public Department(String departmentId, String departmentName, String headDoctorId,
                      int bedCapacity, int availableBeds) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.headDoctorId = headDoctorId;
        this.bedCapacity = bedCapacity;
        this.availableBeds = availableBeds;
        this.doctors = new ArrayList<>();
        this.nurses = new ArrayList<>();
    }



    public void displayInfo() {
        System.out.println("Department ID: " + departmentId);
        System.out.println("Name: " + departmentName);
        System.out.println("Head Doctor ID: " + headDoctorId);
        System.out.println("Bed Capacity: " + bedCapacity);
        System.out.println("Available Beds: " + availableBeds);
    }

    public void assignDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void assignNurse(Nurse nurse) {
        nurses.add(nurse);
    }

    public void updateBedAvailability(int availableBeds) {
        this.availableBeds = availableBeds;
    }
}

