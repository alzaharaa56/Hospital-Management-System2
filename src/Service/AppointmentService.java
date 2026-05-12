package Service;

import Entity.Appointment;
import Behaviour.Appointable;
import Behaviour.Manageable;
import Behaviour.Searchable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Task 3.2: AppointmentService implements Manageable, Searchable, and Appointable.
 * This class handles the core logic for hospital scheduling.
 */
public class AppointmentService implements Manageable<Appointment>, Searchable<Appointment>, Appointable {

    private Scanner scanner = new Scanner(System.in);
    private static List<Appointment> appointmentList = new ArrayList<>();

    // --- Implementation of Manageable Interface ---

    @Override
    public void add(Appointment appointment) {
        if (appointment != null && appointment.validate()) {
            appointmentList.add(appointment);
            System.out.println("Success: Appointment synchronized and saved.");
        } else {
            System.out.println("Error: Appointment validation failed.");
        }
    }

    @Override
    public void remove(String appointmentId) {
        boolean removed = appointmentList.removeIf(a -> a.getAppointmentId().equals(appointmentId));
        if (removed) {
            System.out.println("Success: Appointment record deleted.");
        } else {
            System.out.println("Error: Appointment ID not found.");
        }
    }

    @Override
    public List<Appointment> getAll() {
        return new ArrayList<>(appointmentList);
    }

    // --- Implementation of Searchable Interface ---

    @Override
    public Appointment searchById(String appointmentId) {
        return appointmentList.stream()
                .filter(a -> a.getAppointmentId().equals(appointmentId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Appointment> search(String keyword) {
        String key = keyword.toLowerCase();
        return appointmentList.stream()
                .filter(a -> a.getReason().toLowerCase().contains(key) ||
                        a.getStatus().toLowerCase().contains(key) ||
                        a.getPatientId().toLowerCase().contains(key))
                .collect(Collectors.toList());
    }

    // --- Implementation of Appointable Interface ---

    @Override
    public void scheduleAppointment(Appointment appointment) {
        // We use the add logic but specifically for scheduling
        add(appointment);
    }

    @Override
    public void cancelAppointment(String appointmentId) {
        Appointment app = searchById(appointmentId);
        if (app != null) {
            app.setStatus("Cancelled");
            System.out.println("Success: Appointment " + appointmentId + " has been cancelled.");
        } else {
            System.out.println("Error: Cannot cancel. Appointment not found.");
        }
    }

    @Override
    public void rescheduleAppointment(String appointmentId, LocalDate newDate) {
        Appointment app = searchById(appointmentId);
        if (app != null) {
            app.setAppointmentDate(newDate);
            app.setStatus("Rescheduled");
            System.out.println("Success: Appointment moved to " + newDate);
        } else {
            System.out.println("Error: Rescheduling failed. ID not found.");
        }
    }

    // --- Overloaded Methods (Refined for better logic) ---

    public void addAppointmentFromConsole() {
        try {
            System.out.println("\n--- Schedule New Appointment ---");
            System.out.print("Enter Appointment ID: ");
            String appId = scanner.nextLine();
            System.out.print("Enter Patient ID: ");
            String patId = scanner.nextLine();
            System.out.print("Enter Doctor ID: ");
            String docId = scanner.nextLine();
            System.out.print("Enter Date (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());
            System.out.print("Enter Time: ");
            String time = scanner.nextLine();

            createAppointment(patId, docId, date, time);

        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format. Use YYYY-MM-DD.");
        }
    }

    public void createAppointment(String patientId, String doctorId, LocalDate date, String time) {
        String appId = "APP-" + (appointmentList.size() + 1);
        Appointment app = new Appointment(appId, patientId, doctorId, date, time, "Consultation", "Scheduled");
        add(app);
    }

    public void displayAllAppointments() {
        if (appointmentList.isEmpty()) {
            System.out.println("No appointments found.");
        } else {
            for (Appointment app : appointmentList) {
                app.displaySummary();
            }
        }
    }
}