package Service;

import Entity.Nurse;
import Behaviour.Manageable;
import Behaviour.Searchable;
import Utils.Helper; // Correct import based on your file tree
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class NurseService implements Manageable<Nurse>, Searchable<Nurse> {
    private Scanner scanner = new Scanner(System.in);
    private static List<Nurse> nurseList = new ArrayList<>();

    @Override
    public void add(Nurse nurse) {
        if (Helper.isNotNull(nurse) && nurse.validate()) {
            nurseList.add(nurse);
            System.out.println("Success: Nurse record synchronized.");
        } else {
            System.out.println("Error: Validation failed. Nurse not added.");
        }
    }

    @Override
    public void remove(String nurseId) {
        if (Helper.isNull(nurseId)) return;
        boolean removed = nurseList.removeIf(n -> n.getNurseId().equals(nurseId));
        if (removed) System.out.println("Success: Nurse record deleted.");
        else System.out.println("Error: Nurse ID not found.");
    }

    @Override
    public List<Nurse> getAll() {
        return new ArrayList<>(nurseList);
    }

    @Override
    public Nurse searchById(String nurseId) {
        if (Helper.isNull(nurseId)) return null;
        return nurseList.stream()
                .filter(n -> n.getNurseId().equals(nurseId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Nurse> search(String keyword) {
        if (Helper.isNull(keyword)) return new ArrayList<>();
        String key = keyword.toLowerCase();
        return nurseList.stream()
                .filter(n -> n.getFirstName().toLowerCase().contains(key) ||
                        n.getLastName().toLowerCase().contains(key) ||
                        n.getShift().toLowerCase().contains(key))
                .collect(Collectors.toList());
    }

    public void addNurseFromConsole() {
        try {
            System.out.println("\n--- Register New Nurse ---");



            String perId = Helper.generateId("PER");
            String nurseId = Helper.generateId("NUR");

            System.out.print("Enter First Name: ");
            String fName = scanner.nextLine();
            System.out.print("Enter Last Name: ");
            String lName = scanner.nextLine();
            System.out.print("Enter DOB (YYYY-MM-DD): ");
            LocalDate dob = LocalDate.parse(scanner.nextLine());

            if (!Helper.isValidAge(dob)) {
                System.out.println("Error: Invalid Date of Birth.");
                return;
            }

            System.out.print("Enter Gender: ");
            String gender = scanner.nextLine();
            System.out.print("Enter Phone: ");
            String phone = scanner.nextLine();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            System.out.print("Enter Address: ");
            String address = scanner.nextLine();
            System.out.print("Enter Department ID: ");
            String depId = scanner.nextLine();
            System.out.print("Enter Shift (Morning/Evening/Night): ");
            String shift = scanner.nextLine();
            System.out.print("Enter Qualification: ");
            String qual = scanner.nextLine();

            // Match your Nurse.java constructor exactly
            Nurse nurse = new Nurse(perId, fName, dob, lName, gender, phone, email, address,
                    nurseId, depId, shift, qual);

            add(nurse);

        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    public void editNurse(String nurseId) {
        Nurse n = searchById(nurseId);
        if (Helper.isNotNull(n)) {
            System.out.print("Enter new Shift (Current: " + n.getShift() + "): ");
            n.setShift(scanner.nextLine());
            System.out.print("Enter new Department (Current: " + n.getDepartmentId() + "): ");
            n.setDepartmentId(scanner.nextLine());
            System.out.println("Update Success: Nurse information modified.");
        } else {
            System.out.println("Error: Nurse not found.");
        }
    }

    public void displayAllNurses() {
        if (nurseList.isEmpty()) {
            System.out.println("Registry Status: No nurses registered.");
        } else {
            System.out.println("\n--- Hospital Nursing Staff ---");
            for (Nurse n : nurseList) {
                n.displaySummary();
            }
        }
    }
}