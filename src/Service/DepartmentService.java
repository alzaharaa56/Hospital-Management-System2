package Service;
import Entities.Department;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class DepartmentService {
    Scanner scanner = new Scanner(System.in);
    static List<Department> departments = new ArrayList<>();
    public void addDepartment() {
        System.out.println("Enter Department ID:");
        String departmentId = scanner.nextLine();
        System.out.println("Enter Department Name:");
        String departmentName = scanner.nextLine();
        System.out.println("Enter Head Doctor ID:");
        String headDoctorId = scanner.nextLine();
        System.out.println("Enter Bed Capacity:");
        int bedCapacity = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Available Beds:");
        int availableBeds = Integer.parseInt(scanner.nextLine());
        Department department = new Department(departmentId, departmentName, headDoctorId, new ArrayList<>(), new ArrayList<>(), bedCapacity, availableBeds);
        departments.add(department);
        System.out.println("Department added successfully!");
    }
    public void editDepartment(String departmentId) {
        for (Department dept : departments) {
            if (dept.getDepartmentId().equals(departmentId)) {
                System.out.println("Enter updated Department Name:");
                dept.setDepartmentName(scanner.nextLine());
                System.out.println("Enter updated Head Doctor ID:");
                dept.setHeadDoctorId(scanner.nextLine());
                System.out.println("Enter updated Bed Capacity:");
                dept.setBedCapacity(Integer.parseInt(scanner.nextLine()));
                System.out.println("Enter updated Available Beds:");
                dept.setAvailableBeds(Integer.parseInt(scanner.nextLine()));
                System.out.println("Department updated successfully!");
                return;
            }
        }
        System.out.println("Department not found.");
    }
    public void removeDepartment(String departmentId) {
        boolean removed = departments.removeIf(dept -> dept.getDepartmentId().equals(departmentId));
        if (removed) {
            System.out.println("Department removed successfully.");
        } else {
            System.out.println("Department not found.");
        }
    }
    public Department getDepartmentById(String departmentId) {
        for (Department dept : departments) {
            if (dept.getDepartmentId().equals(departmentId)) {
                return dept;
            }
        }
        return null;
    }
    public void displayAllDepartments() {
        for (Department dept : departments) {
            dept.displayInfo();
        }
    }
    public void assignDoctorToDepartment(String doctorId, String departmentId) {
        Department dept = getDepartmentById(departmentId);
        if (dept != null) {
            dept.getDoctors().add(doctorId);
            System.out.println("Doctor assigned to department successfully.");
        } else {
            System.out.println("Department not found.");
        }
    }
    public void assignNurseToDepartment(String nurseId, String departmentId) {
        Department dept = getDepartmentById(departmentId);
        if (dept != null) {
            dept.getNurses().add(nurseId);
            System.out.println("Nurse assigned to department successfully.");
        } else {
            System.out.println("Department not found.");
        }
    }
    public void updateBedAvailability(String departmentId, int newAvailableBeds) {
        Department dept = getDepartmentById(departmentId);
        if (dept != null) {
            dept.setAvailableBeds(newAvailableBeds);
            System.out.println("Bed availability updated.");
        } else {
            System.out.println("Department not found.");
        }
    }
}