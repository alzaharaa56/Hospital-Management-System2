package Service;

import Entity.Appointment;
import Behaviour.Appointable;
import Behaviour.Manageable;
import Behaviour.Searchable;
import Utils.Helper;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class AppointmentService implements Manageable<Appointment>, Searchable<Appointment>, Appointable {

    private Scanner scanner = new Scanner(System.in);
    private static List<Appointment> appointmentList = new ArrayList<>();

    // --- Implementation of Manageable Interface ---

    @Override
    public void add(Appointment appointment) {

        if (Helper.isNotNull(appointment) && appointment.validate()) {
            appointmentList.add(appointment);
            System.out.println("Success: Appointment record saved to the system.");
        } else {
            System.out.println("Error: Appointment validation failed.");
        }
    }

    @Override
    public void remove(String appointmentId) {

        if (Helper.isNull(appointmentId)) return;

        boolean removed = appointmentList.removeIf(a -> a.getAppointmentId().equals(appointmentId));
        if (removed) {
            System.out.println("Success: Appointment " + appointmentId + " removed.");
        } else {
            System.out.println("Error: Record not found.");
        }
    }

    @Override
    public List<Appointment> getAll() {
        return new ArrayList<>(appointmentList);
    }

    // --- Implementation of Searchable Interface ---

    @Override
    public Appointment searchById(String appointmentId) {
        if (Helper.isNull(appointmentId)) return null;
        return appointmentList.stream()
                .filter(a -> a.getAppointmentId().equals(appointmentId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Appointment> search(String keyword) {

        if (Helper.isNull(keyword)) return new ArrayList<>();

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
        add(appointment);
    }

    @Override
    public void cancelAppointment(String appointmentId) {
        Appointment app = searchById(appointmentId);
        if (Helper.isNotNull(app)) {
            app.setStatus("Cancelled");
            System.out.println("Status Update: Appointment " + appointmentId + " is now Cancelled.");
        } else {
            System.out.println("Error: Appointment not found.");
        }
    }

    @Override
    public void rescheduleAppointment(String appointmentId, LocalDate newDate) {
        Appointment app = searchById(appointmentId);
        // Task 3.4: Using Helper to ensure date logic is sound
        if (Helper.isNotNull(app) && (Helper.isFutureDate(newDate) || Helper.isToday(newDate))) {
            app.setAppointmentDate(newDate);
            app.setStatus("Rescheduled");
            System.out.println("Update Success: New date set to " + newDate);
        } else {
            System.out.println("Error: Invalid date or Appointment ID.");
        }
    }

    // --- Creation Methods ---

    public void addAppointmentFromConsole() {
        try {
            System.out.println("\n--- Appointment Booking ---");
            System.out.print("Patient ID: ");
            String patId = scanner.nextLine();
            System.out.print("Doctor ID: ");
            String docId = scanner.nextLine();
            System.out.print("Date (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());
            System.out.print("Time: ");
            String time = scanner.nextLine();


            if (Helper.isNotNull(patId) && Helper.isNotNull(docId)) {
                createAppointment(patId, docId, date, time);
            }

        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    public void createAppointment(String patientId, String doctorId, LocalDate date, String time) {

        String appId = Helper.generateId("APP", 5);

        Appointment app = new Appointment(appId, patientId, doctorId, date, time, "Consultation", "Scheduled");
        add(app);
    }

    public void displayAllAppointments() {
        if (appointmentList.isEmpty()) {
            System.out.println("Registry is empty.");
        } else {
            System.out.println("\n--- Scheduled Appointments ---");
            for (Appointment app : appointmentList) {
                app.displaySummary();
            }
        }
    }
}