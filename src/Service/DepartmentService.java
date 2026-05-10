package Service;

import Entity.Department;
import Entity.Doctor;
import Entity.Nurse;

import java.util.ArrayList;
import java.util.List;

public class DepartmentService {

    private static List<Department> departments = new ArrayList<>();


    public void addDepartment(Department department) {
        departments.add(department);
        System.out.println("Department added successfully: " + department.getDepartmentId());
    }


    public void editDepartment(String departmentId, Department updatedDepartment) {
        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).getDepartmentId().equals(departmentId)) {
                departments.set(i, updatedDepartment);
                System.out.println("Department updated successfully: " + departmentId);
                return;
            }
        }
        System.out.println("Department not found with ID: " + departmentId);
    }


    public void removeDepartment(String departmentId) {
        boolean removed = departments.removeIf(d -> d.getDepartmentId().equals(departmentId));
        if (removed) {
            System.out.println("Department removed successfully: " + departmentId);
        } else {
            System.out.println("Department not found with ID: " + departmentId);
        }
    }


    public Department getDepartmentById(String departmentId) {
        for (Department d : departments) {
            if (d.getDepartmentId().equals(departmentId)) {
                return d;
            }
        }
        System.out.println("Department not found with ID: " + departmentId);
        return null;
    }


    public void displayAllDepartments() {
        if (departments.isEmpty()) {
            System.out.println("No departments available.");
        } else {
            for (Department d : departments) {
                d.displayInfo();
                System.out.println("----------------------");
            }
        }
    }


    public void assignDoctorToDepartment(String doctorId, String departmentId) {
        Department department = getDepartmentById(departmentId);
        if (department != null) {
            // Assuming DoctorService is available to fetch doctor by ID
            Doctor doctor = new DoctorService().getDoctorById(doctorId);
            if (doctor != null) {
                department.getDoctors().add(doctor);
                System.out.println("Doctor assigned successfully to department: " + departmentId);
            } else {
                System.out.println("Doctor not found with ID: " + doctorId);
            }
        }
    }


    public void assignNurseToDepartment(String nurseId, String departmentId) {
        Department department = getDepartmentById(departmentId);
        if (department != null) {
            // Assuming NurseService is available to fetch nurse by ID
            Nurse nurse = new NurseService().getNurseById(nurseId);
            if (nurse != null) {
                department.getNurses().add(nurse);
                System.out.println("Nurse assigned successfully to department: " + departmentId);
            } else {
                System.out.println("Nurse not found with ID: " + nurseId);
            }
        }
    }


    public void updateBedAvailability(String departmentId, int availableBeds) {
        Department department = getDepartmentById(departmentId);
        if (department != null) {
            department.setAvailableBeds(availableBeds);
            System.out.println("Bed availability updated for department: " + departmentId);
        }
    }
}
