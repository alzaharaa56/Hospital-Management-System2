package Service;

import Behavior.Manageable;
import Behavior.Searchable;
import Entity.Doctor;
import Entity.Nurse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NurseService implements Manageable, Searchable {
    Scanner scanner = new Scanner(System.in);

    static List<Nurse> nurseList = new ArrayList<>();
    List<String> assignedPatients = new ArrayList<>();

    public Nurse addNurse() {

        System.out.println("Enter Nurse id :");
        String id = scanner.nextLine();

        System.out.println("Enter Nurse first name :");
        String nurseFName = scanner.nextLine();

        System.out.println("Enter Nurse last name :");
        String nurseLName = scanner.nextLine();

        System.out.println("Enter Nurse DOB: ");
        String dateOfBirth = scanner.nextLine();
        LocalDate DOB = LocalDate.parse(dateOfBirth);

        System.out.println("Enter Nurse gender :");
        String gender = scanner.nextLine();

        System.out.println("Enter Nurse phone number :");
        String phone = scanner.nextLine();

        System.out.println("Enter Nurse email :");
        String email = scanner.nextLine();

        System.out.println("Enter Nurse address :");
        String address = scanner.nextLine();

        System.out.println("Enter Nurse nurse Id :");
        String nurseId = scanner.nextLine();

        System.out.println("Enter Nurse department Id :");
        String departmentId = scanner.nextLine();

        System.out.println("Enter Nurse shift :");
        String shift = scanner.nextLine();

        System.out.println("Enter Nurse qualification :");
        String qualification = scanner.nextLine();

        Nurse nurse = new Nurse(id, nurseFName, DOB, nurseLName, gender, phone, email, address, nurseId, departmentId, shift, qualification, assignedPatients);

        return nurse;
    }

    public List<Nurse> addNurses() {

        Boolean continueFlag = true;
        while (continueFlag) {

            nurseList.add(addNurse());
            System.out.println("Nurse add successfully");

            System.out.println("Enter c to add more , and q to exit");
            if (scanner.nextLine().equalsIgnoreCase("q")) {
                continueFlag = false;
            }
        }
        return nurseList;

    }

    public void editNurse(String nurseId) {

        for (Nurse nurse : nurseList){

            if(nurse.getNurseId().equals(nurseId)){

                System.out.println("Enter updated Nurse first name :");
                nurse.setFirstName(scanner.nextLine());

                System.out.println("Enter updated Nurse last name :");
                nurse.setLastName(scanner.nextLine());

                System.out.println("Enter updated Nurse DOB: ");
                String dateOfBirth = scanner.nextLine();
                LocalDate DOB = LocalDate.parse(dateOfBirth);
                nurse.setDateOfBirth(DOB);

                System.out.println("Enter updated Nurse gender :");
                nurse.setGender(scanner.nextLine());

                System.out.println("Enter updated Nurse phone number :");
                nurse.setPhoneNumber(scanner.nextLine());

                System.out.println("Enter updated Nurse email :");
                nurse.setEmail(scanner.nextLine());

                System.out.println("Enter updated Nurse address :");
                nurse.setAddress(scanner.nextLine());

                System.out.println("Enter updated Nurse department Id :");
                nurse.setDepartmentId(scanner.nextLine());

                System.out.println("Enter updated Nurse shift :");
                nurse.setShift(scanner.nextLine());

                System.out.println("Enter updated Nurse qualification :");
                nurse.setQualification(scanner.nextLine());

                System.out.println("Nurse updated successfully");


            }

        }

    }

    // remove nurse by ID
    public void removeNurse(String nurseId){

        nurseList.removeIf(N -> N.getNurseId() == nurseId);
        System.out.println("Nurse removed successfully");

        System.out.println("Nurse not found");

    }

    //retrieve nurse
    public Nurse getNurseById(String nurseId){

        for(Nurse nurse: nurseList){
            if(nurse.getNurseId().equals(nurseId)){
                return nurse;
            }

        }
        System.out.println("Nurse not found");
        return null;
    }

    //display all nurses with formatted output
    public void displayAllNurses(){

        for(Nurse nurse: nurseList){
            nurse.displayInfo();
        }

    }

    // get Nurse By Department
    public List<Nurse> getNursesByDepartment(String department){

        List<Nurse> departmentNurse = new ArrayList<>();
        for(Nurse nurse : nurseList){
            if(nurse.getDepartmentId().equals(department)){
                departmentNurse.add(nurse);
            }
        }
        return departmentNurse;
    }

    // get Nurse By Shift
    public List<Nurse> getNursesByShift(String shift){

        List<Nurse> shiftNurse = new ArrayList<>();

        for(Nurse nurse : nurseList){
            if(nurse.getShift().equals(shift)){
                shiftNurse.add(nurse);
            }
        }
        return shiftNurse;
    }


    @Override
    public void add(Object entity) {

    }

    @Override
    public void remove(String id) {

    }

    @Override
    public List<Object> getAll() {
        return List.of();
    }

    @Override
    public void search(String keyword) {

    }

    @Override
    public Object searchById(String id) {
        return null;
    }
}