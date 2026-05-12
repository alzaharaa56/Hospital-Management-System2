package Service;

import Entity.Department;
import Entity.Doctor;
import Entity.Nurse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DepartmentService {
    private Scanner scanner = new Scanner(System.in);
    private static List<Department> departmentList = new ArrayList<>();


    private DoctorService doctorService = new DoctorService();
    private NurseService nurseService = new NurseService();


    public void addDepartmentFromConsole() {
        try {
            System.out.println("\n--- Create New Department ---");
            System.out.print("Enter Department ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter Department Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Head Doctor ID: ");
            String headId = scanner.nextLine();
            System.out.print("Enter Bed Capacity: ");
            int capacity = scanner.nextInt();
            scanner.nextLine(); // تصحيح Scanner بعد int


            Department dept = new Department(id, name, headId, new ArrayList<>(), new ArrayList<>(), capacity, capacity);
            departmentList.add(dept);
            System.out.println("Department '" + name + "' created successfully.");
        } catch (Exception e) {
            System.out.println("Error: Invalid input. Department not created.");
            scanner.nextLine();
        }
    }


    public Department getDepartmentById(String departmentId) {
        for (Department dept : departmentList) {
            if (dept.getDepartmentId().equals(departmentId)) return dept;
        }
        return null;
    }


    public void assignDoctorToDepartment(String doctorId, String departmentId) {
        Department dept = getDepartmentById(departmentId);
        Doctor doctor = doctorService.getDoctorById(doctorId);

        if (dept != null && doctor != null) {
            dept.assignDoctor(doctor);
            System.out.println("Doctor " + doctor.getFirstName() + " assigned to " + dept.getDepartmentName());
        } else {
            System.out.println("Error: Doctor or Department not found.");
        }
    }


    public void assignNurseToDepartment(String nurseId, String departmentId) {
        Department dept = getDepartmentById(departmentId);
        Nurse nurse = nurseService.getNurseById(nurseId);

        if (dept != null && nurse != null) {
            dept.assignNurse(nurse);
            System.out.println("Nurse assigned to department.");
        }
    }


    public void editDepartment(String departmentId) {
        Department dept = getDepartmentById(departmentId);
        if (dept != null) {
            System.out.print("Enter new Name: ");
            dept.setDepartmentName(scanner.nextLine());
            System.out.print("Enter new Bed Capacity: ");
            int cap = scanner.nextInt();
            scanner.nextLine();
            dept.setBedCapacity(cap);
            System.out.println("Department updated.");
        }
    }


    public void removeDepartment(String departmentId) {
        if (departmentList.removeIf(d -> d.getDepartmentId().equals(departmentId))) {
            System.out.println("Department removed.");
        } else {
            System.out.println("Not found.");
        }
    }


    public void displayAllDepartments() {
        for (Department d : departmentList) {
            d.displayInfo();
            System.out.println("--------------------");
        }
    }
}