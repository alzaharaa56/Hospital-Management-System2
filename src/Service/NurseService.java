package Service;

import Behavior.Manageable;
import Behavior.Searchable;
import Entity.Nurse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class NurseService implements Manageable, Searchable {
    private Scanner scanner = new Scanner(System.in);
    private static List<Nurse> nurseList = new ArrayList<>();



    @Override
    public void add(Object entity) {
        if (entity instanceof Nurse nurse) {
            if (searchById(nurse.getNurseId()) == null) {
                nurseList.add(nurse);
                System.out.println("Success: Nurse [" + nurse.getFirstName() + "] added.");
            } else {
                System.out.println("Error: Nurse ID already exists.");
            }
        }
    }


    public void addNurse(String nurseId, String name, String deptId, String shift) {
        Nurse n = new Nurse(nurseId, name, deptId, shift);
        add(n);
    }

    @Override
    public void remove(String id) {

        boolean removed = nurseList.removeIf(n -> n.getNurseId().equalsIgnoreCase(id));
        if (removed) {
            System.out.println("Nurse removed successfully.");
        } else {
            System.out.println("Nurse not found.");
        }
    }

    @Override
    public List<Object> getAll() {
        return new ArrayList<>(nurseList);
    }



    @Override
    public Object searchById(String id) {
        return PatientService.patients.stream()


                .filter(n -> n.getNurseId() != null && n.getNurseId().equalsIgnoreCase(id))
                .findFirst().orElse(null);
    }

    @Override
    public void search(String keyword) {
        System.out.println("\n--- Searching Nurses for: '" + keyword + "' ---");
        List<Nurse> results = nurseList.stream()
                .filter(n -> n.getFirstName().toLowerCase().contains(keyword.toLowerCase()) ||
                        n.getShift().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            System.out.println("No matching nurses found.");
        } else {
            results.forEach(Nurse::displaySummary);
        }
    }



    public void displayAll() {
        System.out.println("\n===== NURSE DIRECTORY =====");
        if (nurseList.isEmpty()) {
            System.out.println("No nurses registered.");
        } else {

            nurseList.forEach(Nurse::displaySummary);
        }
    }


    public Nurse getNurseById(String nurseId) {
        return (Nurse) searchById(nurseId);
    }



    public void interactiveAddNurse() {
        System.out.print("Enter Nurse ID: ");
        String nurseId = scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Shift (Morning/Night): ");
        String shift = scanner.nextLine();

        Nurse n = new Nurse();
        n.setNurseId(nurseId);
        n.setFirstName(name);
        n.setShift(shift);
        add(n);
    }
}