package Service;

import Entity.Doctor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DoctorService {
    private Scanner scanner = new Scanner(System.in);

    private static List<Doctor> doctors = new ArrayList<>();


    public Doctor addDoctor() {
        try {
            System.out.println("--- Enter Doctor Information ---");
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
            System.out.print("Enter Phone Number: ");
            String phone = scanner.nextLine();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            System.out.print("Enter Address: ");
            String address = scanner.nextLine();

            System.out.print("Enter Hospital Doctor ID: ");
            String docId = scanner.nextLine();
            System.out.print("Enter Specialization: ");
            String spec = scanner.nextLine();
            System.out.print("Enter Qualification: ");
            String qual = scanner.nextLine();
            System.out.print("Enter Experience Years: ");
            int exp = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Department ID: ");
            String depId = scanner.nextLine();
            System.out.print("Enter Consultation Fee: ");
            double fee = scanner.nextDouble();
            scanner.nextLine();


            Doctor doctor = new Doctor(id, fName, dob, lName, gender, phone, email, address,
                    docId, spec, qual, exp, depId, fee,
                    new ArrayList<>(), new ArrayList<>());

            doctors.add(doctor);
            System.out.println("Doctor added successfully!");
            return doctor;

        } catch (Exception e) {
            System.out.println("Error: Invalid input format. Please try again.");
            return null;
        }
    }


    public void addDoctor(String firstName, String specialization, double fee) {
        Doctor doctor = new Doctor(null, 0);
        doctor.setFirstName(firstName);
        doctor.setSpecialization(specialization);
        doctor.setConsultationFee(fee);
        doctors.add(doctor);
        System.out.println("Minimal Doctor profile created.");
    }


    public Doctor getDoctorById(String doctorId) {
        for (Doctor doc : doctors) {
            if (doc.getDoctorId() != null && doc.getDoctorId().equals(doctorId)) {
                return doc;
            }
        }
        return null;
    }


    public void editDoctor(String doctorId) {
        Doctor doc = getDoctorById(doctorId);
        if (doc != null) {
            System.out.print("Enter new Specialization (Current: " + doc.getSpecialization() + "): ");
            doc.setSpecialization(scanner.nextLine());
            System.out.print("Enter new Consultation Fee: ");
            doc.setConsultationFee(scanner.nextDouble());
            scanner.nextLine();
            System.out.println("Doctor information updated.");
        } else {
            System.out.println("Doctor not found.");
        }
    }


    public void removeDoctor(String doctorId) {
        boolean removed = doctors.removeIf(d -> d.getDoctorId() != null && d.getDoctorId().equals(doctorId));
        if (removed) {
            System.out.println("Doctor removed successfully.");
        } else {
            System.out.println("Doctor ID not found.");
        }
    }


    public void displayAllDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors registered in the system.");
        } else {
            for (Doctor doc : doctors) {
                doc.displayInfo();
                System.out.println("-----------------------");
            }
        }
    }


    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        List<Doctor> result = new ArrayList<>();
        for (Doctor doc : doctors) {
            if (doc.getSpecialization().equalsIgnoreCase(specialization)) {
                result.add(doc);
            }
        }
        return result;
    }
}