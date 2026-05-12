package Service;

import Entity.Department;
import Entity.Doctor;
import Entity.Nurse;
import Utils.Helper;
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

            String id = Helper.generateId("DEP");

            System.out.print("Enter Department Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Head Doctor ID: ");
            String headId = scanner.nextLine();
            System.out.print("Enter Bed Capacity: ");
            int capacity = scanner.nextInt();
            scanner.nextLine();

            if (Helper.isValidString(name)) {
                Department dept = new Department(id, name, headId, capacity);
                departmentList.add(dept);
                System.out.println("Department '" + name + "' created successfully with ID: " + id);
            } else {
                System.out.println("Error: Department name is required.");
            }
        } catch (Exception e) {
            System.out.println("Error: Invalid input.");
            scanner.nextLine();
        }
    }

    public Department getDepartmentById(String departmentId) {
        if (Helper.isNull(departmentId)) return null;
        for (Department dept : departmentList) {
            if (dept.getDepartmentId().equals(departmentId)) return dept;
        }
        return null;
    }

    public void assignDoctorToDepartment(String doctorId, String departmentId) {
        Department dept = getDepartmentById(departmentId);
        Doctor doctor = doctorService.searchById(doctorId);

        if (Helper.isNotNull(dept) && Helper.isNotNull(doctor)) {
            dept.assignDoctor(doctor);
            System.out.println("Doctor " + doctor.getFirstName() + " assigned to " + dept.getDepartmentName());
        } else {
            System.out.println("Error: Doctor or Department not found.");
        }
    }

    public void assignNurseToDepartment(String nurseId, String departmentId) {
        Department dept = getDepartmentById(departmentId);
        Nurse nurse = nurseService.searchById(nurseId);

        if (Helper.isNotNull(dept) && Helper.isNotNull(nurse)) {
            dept.assignNurse(nurse);
            System.out.println("Nurse " + nurse.getFirstName() + " assigned to " + dept.getDepartmentName());
        } else {
            System.out.println("Error: Nurse or Department not found.");
        }
    }

    public void editDepartment(String departmentId) {
        Department dept = getDepartmentById(departmentId);
        if (Helper.isNotNull(dept)) {
            System.out.print("Enter new Name (Current: " + dept.getDepartmentName() + "): ");
            dept.setDepartmentName(scanner.nextLine());
            System.out.print("Enter new Bed Capacity: ");
            int cap = scanner.nextInt();
            scanner.nextLine();
            dept.setBedCapacity(cap);
            System.out.println("Department updated.");
        } else {
            System.out.println("Error: Department not found.");
        }
    }

    public void removeDepartment(String departmentId) {
        if (Helper.isValidString(departmentId)) {
            if (departmentList.removeIf(d -> d.getDepartmentId().equals(departmentId))) {
                System.out.println("Department removed.");
            } else {
                System.out.println("Error: Department not found.");
            }
        }
    }

    public void displayAllDepartments() {
        if (departmentList.isEmpty()) {
            System.out.println("No departments registered.");
        } else {
            for (Department d : departmentList) {
                d.displayInfo();
                System.out.println("--------------------");
            }
        }
    }
}