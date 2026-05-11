package Service;

import Entity.Appointment;
import Entity.Department;
import Entity.Doctor;
import Entity.Nurse;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DepartmentService {

    Scanner scanner = new Scanner(System.in);

    static List<Department> departmentList = new ArrayList<>();
    List<Doctor> doctors = new ArrayList<>();
    List<Nurse> nurses = new ArrayList<>();
    DoctorService doctorService = new DoctorService();

    public Department addDepartment(){

        System.out.println("Enter Department Id :");
        String departmentId = scanner.nextLine();

        System.out.println("Enter department Name :");
        String departmentName = scanner.nextLine();

        System.out.println("Enter department head DoctorId :");
        String headDoctorId = scanner.nextLine();

        System.out.println("Enter department  bed Capacity :");
        int bedCapacity = scanner.nextInt();

        System.out.println("Enter department available Beds :");
        int availableBeds = scanner.nextInt();

        Department department = new Department(departmentId,departmentName,headDoctorId,doctors,nurses,bedCapacity,availableBeds);

        return department;

    }

    public List<Department> addDepartments(){

        Boolean continueFlag = true;
        while (continueFlag) {

            departmentList.add(addDepartment());
            System.out.println("Department add successfully");

            System.out.println("Enter c to add more , and q to exit");

            if (scanner.nextLine().equalsIgnoreCase("q")) {
                continueFlag = false;
            }
        }
        return departmentList;

    }

    // edit department

    public void editDepartment(String departmentId){

        for(Department department : departmentList){
            if(department.getDepartmentId().equals(departmentId)){

                System.out.println("Enter updated department Name :");
                department.setDepartmentName(scanner.nextLine());

                System.out.println("Enter updated department head DoctorId :");
                department.setHeadDoctorId(scanner.nextLine());

                System.out.println("Enter updated department  bed Capacity :");
                department.setBedCapacity(scanner.nextInt());

                System.out.println("Enter updated department available Beds :");
                department.setAvailableBeds(scanner.nextInt());

                System.out.println("department updated successfully");

            }

        }

    }

    // remove department by ID
    public void removeDepartment(String departmentId){

        departmentList.removeIf(D -> D.getDepartmentId() == departmentId);
        System.out.println("Department removed successfully");

        System.out.println("Department record not found");
    }

    //retrieve department
    public Department getDepartment(String departmentId){

        for(Department department: departmentList){

            if(department.getDepartmentId().equals(departmentId)){
                return department;
            }

        }
        System.out.println("department not found");
        return null;
    }

    // display All Departments
    public void displayAllDepartments(){

        for(Department department : departmentList){
            department.displayInfo();
        }
    }

    // assign Doctor To Department(String doctorId, String departmentId)

    public void assignDoctorToDepartment(String doctorId, String departmentId){

        Doctor doctor = doctorService.getDoctorById(doctorId);

        for(Department department : departmentList){

            if(department.getDepartmentId().equals(departmentId)){

                department.getDoctors().add(doctor);
            }
        }


    }

}