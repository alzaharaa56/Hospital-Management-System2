package Main;

import Entity.Appointment;
import Entity.Department;
import Entity.MedicalRecord;
import Entity.Nurse;
import Service.*;
import Utils.InputHandler;

import java.time.LocalDate;

class HospitalManagementApp {


    private static final PatientService patientService = new PatientService();
    private static final DoctorService doctorService = new DoctorService();
    private static final NurseService nurseService = new NurseService();
    private static final MedicalRecordService recordService = new MedicalRecordService();
    private static final DepartmentService departmentService = new DepartmentService();
    private static final AppointmentService appointmentService = new AppointmentService() {
        @Override
        public void rescheduleAppointment(String appointmentId, LocalDate newDate) {

        }
    };

    public static void main(String[] args) {
        populateInitialData();

        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = InputHandler.getIntInput("Select an option (1-8): ", 1, 8);

            switch (choice) {
                case 1 -> patientManagementMenu();
                case 2 -> doctorManagementMenu();
                case 3 -> nurseManagementMenu();
                case 4 -> appointmentManagementMenu();
                case 5 -> medicalRecordsMenu();
                case 6 -> departmentManagementMenu();
                case 7 -> reportsMenu();
                case 8 -> {
                    System.out.println("Exiting System... Goodbye!");
                    running = false;
                }
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n========================================");
        System.out.println("      HOSPITAL MANAGEMENT SYSTEM      ");
        System.out.println("========================================");
        System.out.println("1. Patient Management\t2. Doctor Management");
        System.out.println("3. Nurse Management\t4. Appointment Management");
        System.out.println("5. Medical Records\t6. Department Management");
        System.out.println("7. Reports & Stats\t8. Exit");
    }



    private static void patientManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- 1. Patient Management ---");
            System.out.println("1. Register\t2. search\t5. View All\t8. Remove\t0. Back");
            int choice = InputHandler.getIntInput("Choice: ", 0, 8);
            switch (choice) {
                case 1 -> patientService.addPatient(
                        InputHandler.getStringInput("First Name: "),
                        InputHandler.getStringInput("Last Name: "),
                        InputHandler.getStringInput("Phone: ")
                );
                case 2 ->{patientService.search();}

                case 5 -> patientService.displayAll();
                case 8 -> patientService.remove(InputHandler.getStringInput("Enter ID: "));
                case 0 -> back = true;
            }
        }
    }

    private static void doctorManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- 2. Doctor Management ---");
            System.out.println("4. View All Doctors\t0. Back");
            int choice = InputHandler.getIntInput("Choice: ");
            if (choice == 4) doctorService.displayDoctors();
            else if (choice == 0) back = true;
        }
    }

    private static void nurseManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- 3. Nurse Management ---");
            System.out.println("2. View All Nurses\t0. Back");
            int choice = InputHandler.getIntInput("Choice: ");
            if (choice == 2) nurseService.displayAll();
            else if (choice == 0) back = true;
        }
    }

    private static void appointmentManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- 4. Appointment Management ---");
            System.out.println("2. View All Appointments\t0. Back");
            int choice = InputHandler.getIntInput("Choice: ");
            if (choice == 2) appointmentService.displayAll();
            else if (choice == 0) back = true;
        }
    }

    private static void medicalRecordsMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- 5. Medical Records ---");
            System.out.println("1. Create New\t2. View All\t3. View by Patient ID\t0. Back");
            int choice = InputHandler.getIntInput("Choice: ", 0, 3);
            switch (choice) {
                case 2 -> recordService.displayPatientHistory();
                case 3 -> recordService.displayPatientHistory(InputHandler.getStringInput("Patient ID: "));
                case 0 -> back = true;
            }
        }
    }

    private static void departmentManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- 6. Department Management ---");
            System.out.println("1. Add\t2. View All\t3. View Details\t4. Assign Doctor\t0. Back");
            int choice = InputHandler.getIntInput("Choice: ", 0, 6);
            switch (choice) {
                case 2 -> departmentService.displayAllDepartments();
                case 3 -> {
                    String id = InputHandler.getStringInput("Dept ID: ");
                    Department dept = departmentService.getDepartment(id);
                    if (dept != null) dept.displayInfo();
                    else System.out.println("Department not found!");
                }
                case 4 -> departmentService.assignDoctorToDepartment(
                        InputHandler.getStringInput("Doctor ID: "),
                        InputHandler.getStringInput("Dept ID: ")
                );
                case 0 -> back = true;
            }
        }
    }

    private static void reportsMenu() {
        System.out.println("\n--- 7. Reports & Statistics ---");
        System.out.println("Functionality coming soon...");
    }

    private static void populateInitialData() {
        System.out.println("Initializing System Data...");


        departmentService.add(new Department("DEPT-01", "Cardiology", "DOC-01", 50, 20));
        departmentService.add(new Department("DEPT-02", "Neurology", "DOC-02", 30, 15));
        departmentService.add(new Department("DEPT-03", "Emergency", "DOC-05", 20, 5));

        departmentService.add(new Department("DEPT-04", "Pediatrics", "DOC-04", 40, 25));
        departmentService.add(new Department("DEPT-05", "General Medicine", "DOC-03", 60, 30));
        departmentService.add(new Department("DEPT-06", "Surgery", "DOC-07", 15, 10));

        doctorService.addDoctor("Salim", "General Medicine", "94445555", 40.0);
        doctorService.addDoctor("Zahra", "Pediatrics", "95556666", 55.0);
        doctorService.addDoctor("Oman", "Emergency", "96667777", 65.0);
        doctorService.addDoctor("Laila", "Cardiology", "97778888", 70.0);
        doctorService.addDoctor("Fahad", "Neurology", "98889999", 60.0);
        doctorService.addDoctor("Muna", "General Medicine", "99990000", 45.0);
        doctorService.addDoctor("Ali", "Surgery", "92221111", 80.0); // طبيب إضافي لضمان التنوع

        patientService.addPatient("Asma", "Al-Balushi", "91234567");
        patientService.addPatient("Khalid", "Al-Omani", "98765432");
        patientService.addPatient("Fatma", "Al-Said", "92223333");
        patientService.addPatient("Hamed", "Al-Rashdi", "94441111");
        patientService.addPatient("Noora", "Al-Housni", "95550000");
        patientService.addPatient("Sami", "Al-Zadjali", "96662222");
        patientService.addPatient("Raya", "Al-Maskari", "97773333");
        patientService.addPatient("Yaser", "Al-Rawahi", "98884444");
        patientService.addPatient("Zaki", "Al-Fazari", "99995555");


        recordService.add(new MedicalRecord("REC-02", "PAT-02", "DOC-02", LocalDate.now().minusDays(8), "Migraine", "Sumatriptan", "Normal CT Scan", "Avoid bright lights"));
        recordService.add(new MedicalRecord("REC-03", "PAT-03", "DOC-03", LocalDate.now().minusDays(7), "Common Cold", "Paracetamol", "No fever", "Rest for 3 days"));
        recordService.add(new MedicalRecord("REC-04", "PAT-04", "DOC-01", LocalDate.now().minusDays(5), "Diabetes Type 2", "Metformin 500mg", "HbA1c: 7.2", "Low sugar diet"));
        recordService.add(new MedicalRecord("REC-05", "PAT-05", "DOC-04", LocalDate.now().minusDays(4), "Asthma", "Salbutamol Inhaler", "Lung capacity normal", "Avoid dust"));
        recordService.add(new MedicalRecord("REC-06", "PAT-06", "DOC-02", LocalDate.now().minusDays(3), "Anemia", "Iron supplements", "Low Hemoglobin", "Increase leafy greens"));
        recordService.add(new MedicalRecord("REC-07", "PAT-07", "DOC-03", LocalDate.now().minusDays(2), "Back Pain", "Ibuprofen", "Muscle strain", "Physical therapy recommended"));
        recordService.add(new MedicalRecord("REC-08", "PAT-08", "DOC-06", LocalDate.now().minusDays(1), "Gastritis", "Antacids", "H. Pylori negative", "Avoid spicy food"));
        recordService.add(new MedicalRecord("REC-09", "PAT-09", "DOC-07", LocalDate.now(), "Sprained Ankle", "Elastic bandage", "No fracture", "Ice packs 3 times daily"));
        recordService.add(new MedicalRecord("REC-10", "PAT-10", "DOC-08", LocalDate.now(), "Gingivitis", "Antibacterial mouthwash", "Plaque buildup", "Daily flossing"));
        recordService.add(new MedicalRecord("REC-11", "PAT-01", "DOC-02", LocalDate.now(), "Skin Allergy", "Antihistamines", "Allergic reaction", "Identify trigger factors"));
        recordService.add(new MedicalRecord("REC-12", "PAT-02", "DOC-01", LocalDate.now(), "Mild Flu", "Rest and fluids", "Temp: 37.8C", "Monitor temperature"));

        appointmentService.add(new Appointment("APP-02", "PAT-02", "DOC-02", LocalDate.now(), "10:00 AM", "Scheduled", "Follow-up", "Patient stable"));
        appointmentService.add(new Appointment("APP-03", "PAT-03", "DOC-03", LocalDate.now(), "11:30 AM", "Scheduled", "Emergency", "High fever"));
        appointmentService.add(new Appointment("APP-04", "PAT-04", "DOC-01", LocalDate.now().plusDays(1), "08:30 AM", "Scheduled", "Consultation", "First visit"));
        appointmentService.add(new Appointment("APP-05", "PAT-05", "DOC-04", LocalDate.now().plusDays(1), "01:00 PM", "Scheduled", "Vaccination", "Childhood immuniz."));
        appointmentService.add(new Appointment("APP-06", "PAT-06", "DOC-02", LocalDate.now().plusDays(2), "09:30 AM", "Scheduled", "Blood Test", "Fasting required"));
        appointmentService.add(new Appointment("APP-07", "PAT-07", "DOC-05", LocalDate.now().plusDays(2), "11:00 AM", "Scheduled", "X-Ray", "Injury check"));
        appointmentService.add(new Appointment("APP-08", "PAT-08", "DOC-03", LocalDate.now().plusDays(3), "10:30 AM", "Scheduled", "Surgery Consult", "Knee issue"));
        appointmentService.add(new Appointment("APP-09", "PAT-09", "DOC-06", LocalDate.now().plusDays(3), "02:00 PM", "Scheduled", "Heart Scan", "Regular monitoring"));
        appointmentService.add(new Appointment("APP-10", "PAT-10", "DOC-07", LocalDate.now().plusDays(4), "09:00 AM", "Scheduled", "Eye Exam", "Vision blur"));
        appointmentService.add(new Appointment("APP-11", "PAT-01", "DOC-08", LocalDate.now().plusDays(4), "11:15 AM", "Scheduled", "Physiotherapy", "Session 1"));
        appointmentService.add(new Appointment("APP-12", "PAT-02", "DOC-01", LocalDate.now().plusDays(5), "08:00 AM", "Scheduled", "Dental Check", "Routine cleaning"));
        appointmentService.add(new Appointment("APP-13", "PAT-03", "DOC-02", LocalDate.now().plusDays(5), "03:30 PM", "Scheduled", "Skin Allergy", "Rash on arm"));
        appointmentService.add(new Appointment("APP-14", "PAT-04", "DOC-04", LocalDate.now().plusDays(6), "12:00 PM", "Scheduled", "Prescription", "Refill request"));
        appointmentService.add(new Appointment("APP-15", "PAT-05", "DOC-01", LocalDate.now().plusDays(7), "10:00 AM", "Scheduled", "Final Review", "Discharge prep"));

        nurseService.add(new Nurse("NUR-02", "Huda", "DEPT-01", "Evening"));
        nurseService.add(new Nurse("NUR-03", "Fatma", "DEPT-02", "Night"));
        nurseService.add(new Nurse("NUR-04", "Maryam", "DEPT-03", "Morning"));
        nurseService.add(new Nurse("NUR-05", "Zakiya", "DEPT-02", "Evening"));
        nurseService.add(new Nurse("NUR-06", "Laila", "DEPT-03", "Night")); // ممرضة إضافية للتنوع
        System.out.println("Data Loaded Successfully.\n");
    }
}