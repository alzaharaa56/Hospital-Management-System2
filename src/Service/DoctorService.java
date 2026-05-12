package Service;

import Entity.Doctor;
import Entity.Patient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DoctorService {
    private Scanner scanner = new Scanner(System.in);
    private static List<Doctor> doctors = new ArrayList<>();


    public Doctor addDoctor() {
        try {
            System.out.println("\n--- Enter Doctor Information ---");
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
            int exp = scanner.nextInt(); scanner.nextLine();
            System.out.print("Enter Department ID: ");
            String depId = scanner.nextLine();
            System.out.print("Enter Consultation Fee: ");
            double fee = scanner.nextDouble(); scanner.nextLine();

            Doctor doctor = new Doctor(id, fName, dob, lName, gender, phone, email, address,
                    docId, spec, qual, exp, depId, fee, new ArrayList<>(), new ArrayList<>());

            doctors.add(doctor);
            System.out.println("Doctor added successfully!");
            return doctor;
        } catch (Exception e) {
            System.out.println("Error: Invalid input format. Please try again.");
            if(scanner.hasNextLine()) scanner.nextLine();
            return null;
        }
    }


    public void addDoctor(String name, String specialization, String phone) {
        Doctor doc = new Doctor();
        doc.setFirstName(name);
        doc.setSpecialization(specialization);
        doc.setPhoneNumber(phone);
        doctors.add(doc);
        System.out.println("Minimal Doctor profile created for Dr. " + name);
    }


    public void addDoctor(String name, String specialization, String phone, double consultationFee) {
        Doctor doc = new Doctor();
        doc.setFirstName(name);
        doc.setSpecialization(specialization);
        doc.setPhoneNumber(phone);
        doc.setConsultationFee(consultationFee);
        doctors.add(doc);
        System.out.println("Detailed Doctor profile created with fee: " + consultationFee);
    }


    public void addDoctor(Doctor doctor) {
        if (doctor != null) {
            doctors.add(doctor);
            System.out.println("Full Doctor object added to system.");
        }
    }

    public void assignPatient(String doctorId, String patientId) {
        Doctor doc = getDoctorById(doctorId);
        if (doc != null) {
            doc.getAssignedPatients().add(patientId);
            System.out.println("Patient " + patientId + " assigned to Dr. " + doc.getLastName());
        } else {
            System.out.println("Doctor not found.");
        }
    }


    public void assignPatient(Doctor doctor, Patient patient) {
        if (doctor != null && patient != null) {
            doctor.getAssignedPatients().add(patient.getPatientId());
            System.out.println("Assignment complete via object reference.");
        }
    }


    public void assignPatient(String doctorId, List<String> patientIds) {
        Doctor doc = getDoctorById(doctorId);
        if (doc != null && patientIds != null) {
            doc.getAssignedPatients().addAll(patientIds);
            System.out.println("Successfully assigned " + patientIds.size() + " patients.");
        }
    }

    public void displayDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors registered.");
        } else {
            System.out.println("\n--- All Doctors ---");
            for (Doctor doc : doctors) {
                doc.displayInfo();
                System.out.println("-----------------------");
            }
        }
    }


    public void displayDoctors(String specialization) {
        System.out.println("\n--- Specialization: " + specialization + " ---");
        for (Doctor doc : doctors) {
            if (doc.getSpecialization().equalsIgnoreCase(specialization)) {
                doc.displayInfo();
            }
        }
    }


    public void displayDoctors(String departmentId, boolean showAvailableOnly) {
        System.out.println("\n--- Department: " + departmentId + " (Available Only: " + showAvailableOnly + ") ---");
        for (Doctor doc : doctors) {
            if (doc.getDepartmentId().equalsIgnoreCase(departmentId)) {
                if (!showAvailableOnly || (doc.getAvailableSlots() != null && !doc.getAvailableSlots().isEmpty())) {
                    doc.displayInfo();
                }
            }
        }
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
            System.out.print("Enter new Specialization: ");
            doc.setSpecialization(scanner.nextLine());
            System.out.print("Enter new Fee: ");
            doc.setConsultationFee(scanner.nextDouble());
            scanner.nextLine();
            System.out.println("Updated.");
        }
    }

    public void removeDoctor(String doctorId) {
        if (doctors.removeIf(d -> d.getDoctorId().equals(doctorId))) {
            System.out.println("Removed.");
        }
    }
}