package Service;

import Entity.Nurse;

import java.util.ArrayList;
import java.util.List;

public class NurseService {

    private static List<Nurse> nurses = new ArrayList<>();


    public void addNurse(Nurse nurse) {
        nurses.add(nurse);
        System.out.println("Nurse added successfully: " + nurse.getNurseId());
    }


    public void editNurse(String nurseId, Nurse updatedNurse) {
        for (int i = 0; i < nurses.size(); i++) {
            if (nurses.get(i).getNurseId().equals(nurseId)) {
                nurses.set(i, updatedNurse);
                System.out.println("Nurse updated successfully: " + nurseId);
                return;
            }
        }
        System.out.println("Nurse not found with ID: " + nurseId);
    }


    public void removeNurse(String nurseId) {
        boolean removed = nurses.removeIf(n -> n.getNurseId().equals(nurseId));
        if (removed) {
            System.out.println("Nurse removed successfully: " + nurseId);
        } else {
            System.out.println("Nurse not found with ID: " + nurseId);
        }
    }


    public Nurse getNurseById(String nurseId) {
        for (Nurse n : nurses) {
            if (n.getNurseId().equals(nurseId)) {
                return n;
            }
        }
        System.out.println("Nurse not found with ID: " + nurseId);
        return null;
    }


    public void displayAllNurses() {
        if (nurses.isEmpty()) {
            System.out.println("No nurses available.");
        } else {
            for (Nurse n : nurses) {
                n.displayInfo();
                System.out.println("----------------------");
            }
        }
    }


    public List<Nurse> getNursesByDepartment(String departmentId) {
        List<Nurse> result = new ArrayList<>();
        for (Nurse n : nurses) {
            if (n.getDepartmentId().equalsIgnoreCase(departmentId)) {
                result.add(n);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No nurses found in department: " + departmentId);
        }
        return result;
    }


    public List<Nurse> getNursesByShift(String shift) {
        List<Nurse> result = new ArrayList<>();
        for (Nurse n : nurses) {
            if (n.getShift().equalsIgnoreCase(shift)) {
                result.add(n);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No nurses found in shift: " + shift);
        }
        return result;
    }
}

