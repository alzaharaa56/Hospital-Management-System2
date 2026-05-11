package Service;

import Entity.Doctor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DoctorService {
    Scanner scanner = new Scanner(System.in);

    static List<Doctor> doctors = new ArrayList<>();
    private List<String> availableSlots;
    private List<String> assignedPatients;


    public Doctor addDoctor(){

        System.out.println("Enter Doctor id :");
        String id = scanner.nextLine();

        System.out.println("Enter Doctor first name :");
        String doctorFName = scanner.nextLine();

        System.out.println("Enter Doctor last name :");
        String doctorLName = scanner.nextLine();

        System.out.println("Enter Doctor DOB: ");
        String dateOfBirth = scanner.nextLine();
        LocalDate DOB = LocalDate.parse(dateOfBirth);

        System.out.println("Enter Doctor gender :");
        String gender = scanner.nextLine();

        System.out.println("Enter Doctor phone number :");
        String phone = scanner.nextLine();

        System.out.println("Enter Doctor email :");
        String email = scanner.nextLine();

        System.out.println("Enter Doctor address :");
        String address = scanner.nextLine();

        System.out.println("Enter Doctor ID :");
        String doctorId = scanner.nextLine();

        System.out.println("Enter Doctor specialization :");
        String specialization = scanner.nextLine();

        System.out.println("Enter Doctor qualification :");
        String qualification = scanner.nextLine();

        System.out.println("Enter Doctor experienceYears :");
        int experienceYears = scanner.nextInt();

        System.out.println("Enter Doctor departmentId :");
        String departmentId = scanner.nextLine();

        System.out.println("Enter Doctor consultationFee :");
        double consultationFee = scanner.nextDouble();

        String lastName = "";
        LocalDate dob = null;
        List<String> slots = List.of();
        Doctor doctor = new Doctor(id, experienceYears);
        return doctor;
    }

    public List<Doctor> addDoctors(){

        Boolean continueFlag = true;
        while (continueFlag) {

            doctors.add(addDoctor());
            System.out.println("Doctor add successfully");

            System.out.println("Enter c to add more , and q to exit");
            if (scanner.nextLine().equalsIgnoreCase("q")) {
                continueFlag = false;
            }
        }
        return doctors;

    }

    public void editDoctor(String doctorId){

        for(Doctor doctor : doctors){

            if(doctor.getDoctorId().equals(doctorId)){
                System.out.println("Enter updated Doctor id :");
                doctor.setId(scanner.nextLine());

                System.out.println("Enter updated Doctor first name :");
                doctor.setFirstName(scanner.nextLine());

                System.out.println("Enter updated Doctor last name :");
                doctor.setLastName(scanner.nextLine());

                System.out.println("Enter updated Doctor DOB: ");
                String dateOfBirth = scanner.nextLine();
                LocalDate DOB = LocalDate.parse(dateOfBirth);
                doctor.setDateOfBirth(DOB);

                System.out.println("Enter updated Doctor gender :");
                doctor.setGender(scanner.nextLine());

                System.out.println("Enter updated Doctor phone number :");
                doctor.setPhoneNumber(scanner.nextLine());

                System.out.println("Enter updated Doctor email :");
                doctor.setEmail(scanner.nextLine());

                System.out.println("Enter updated Doctor address :");
                String address = scanner.nextLine();

                System.out.println("Enter updated Doctor ID :");
                doctor.setDoctorId(scanner.nextLine());

                System.out.println("Enter updated Doctor specialization :");
                doctor.setSpecialization(scanner.nextLine());

                System.out.println("Enter updated Doctor qualification :");
                doctor.setQualification(scanner.nextLine());

                System.out.println("Enter updated Doctor experienceYears :");
                doctor.setExperienceYears(scanner.nextInt());

                System.out.println("Enter updated Doctor departmentId :");
                doctor.setDepartmentId(scanner.nextLine());

                System.out.println("Enter updated Doctor consultationFee :");
                doctor.setConsultationFee(scanner.nextDouble());

                System.out.println("patient updated successfully");

            }
        }

    }

    // remove doctor by ID
    public void removeDoctor(String doctorId){

        doctors.removeIf(b -> b.getDoctorId() == doctorId);
        System.out.println("patient removed successfully");

        System.out.println("patient not found");

    }

    //retrieve doctor
    public Doctor getDoctorById(String doctorId){

        for(Doctor doctor: doctors){
            if(doctor.getDoctorId().equals(doctorId)){
                return doctor;
            }

        }
        System.out.println("doctor not found");
        return null;
    }


    //display all doctors with formatted output
    public void displayAllDoctors(){

        for(Doctor doctor: doctors){
            doctor.displayInfo();
        }

    }

    //get Doctors By Specialization
    public List<Doctor> getDoctorsBySpecialization(String specialization) {

        List<Doctor> specializationDoctors = new ArrayList<>();

        for(Doctor doctor : doctors){

            if(doctor.getSpecialization().equals(specialization)){
                specializationDoctors.add(doctor);
            }
        }
        return specializationDoctors;
    }

    //get Available Doctors()
    public List<Doctor> getAvailableDoctors(){

        List<Doctor> availableDoctors = new ArrayList<>();

        for(Doctor doctor : doctors){

            if (!doctor.getAvailableSlots().isEmpty()) {
                availableDoctors.add(doctor);
            }

        }

        return availableDoctors;
    }


}