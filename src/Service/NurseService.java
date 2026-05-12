package Service;

import Entity.Nurse;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NurseService {
    private Scanner scanner = new Scanner(System.in);

    private static List<Nurse> nurseList = new ArrayList<>();


    public void addNurseFromConsole() {
        try {
            System.out.println("\n--- Register New Nurse ---");
            System.out.print("Enter Person ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter First Name: ");
            String fName = scanner.nextLine();
            System.out.print("Enter Last Name: ");
            String lName = scanner.nextLine();
            System.out.print("Enter DOB (YYYY-MM-DD): ");
            LocalDate dob = LocalDate.parse(scanner.nextLine());
            System.out.print("Enter Gender: ");
            String gender = scanner.nextLine();
            System.out.print("Enter Phone: ");
            String phone = scanner.nextLine();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            System.out.print("Enter Address: ");
            String address = scanner.nextLine();

            System.out.print("Enter Nurse ID: ");
            String nurseId = scanner.nextLine();
            System.out.print("Enter Department ID: ");
            String depId = scanner.nextLine();
            System.out.print("Enter Shift (Morning/Evening/Night): ");
            String shift = scanner.nextLine();
            System.out.print("Enter Qualification: ");
            String qual = scanner.nextLine();


            Nurse nurse = new Nurse(id, fName, dob, lName, gender, phone, email, address,
                    nurseId, depId, shift, qual, new ArrayList<>());

            nurseList.add(nurse);
            System.out.println("Nurse added successfully!");

        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format. Please use YYYY-MM-DD.");
        }
    }


    public void addNurse(String firstName, String shift, String departmentId) {

        Nurse nurse = new Nurse(null, firstName, null, null, null, null, null, null,
                "N-" + (nurseList.size() + 1), departmentId, shift, null, new ArrayList<>());
        nurseList.add(nurse);
        System.out.println("Basic nurse profile created for: " + firstName);
    }


    public Nurse getNurseById(String nurseId) {
        for (Nurse n : nurseList) {
            if (n.getNurseId() != null && n.getNurseId().equals(nurseId)) {
                return n;
            }
        }
        return null;
    }


    public void editNurse(String nurseId) {
        Nurse n = getNurseById(nurseId);
        if (n != null) {
            System.out.print("Enter new Shift (Current: " + n.getShift() + "): ");
            n.setShift(scanner.nextLine());
            System.out.print("Enter new Department (Current: " + n.getDepartmentId() + "): ");
            n.setDepartmentId(scanner.nextLine());
            System.out.println("Nurse information updated.");
        } else {
            System.out.println("Nurse not found.");
        }
    }


    public void removeNurse(String nurseId) {
        boolean removed = nurseList.removeIf(n -> n.getNurseId() != null && n.getNurseId().equals(nurseId));
        if (removed) {
            System.out.println("Nurse removed successfully.");
        } else {
            System.out.println("Nurse not found.");
        }
    }


    public List<Nurse> getNursesByDepartment(String departmentId) {
        List<Nurse> result = new ArrayList<>();
        for (Nurse n : nurseList) {
            if (n.getDepartmentId().equalsIgnoreCase(departmentId)) {
                result.add(n);
            }
        }
        return result;
    }


    public void displayAllNurses() {
        if (nurseList.isEmpty()) {
            System.out.println("No nurses registered.");
        } else {
            for (Nurse n : nurseList) {
                n.displayInfo();
                System.out.println("--------------------");
            }
        }
    }
}