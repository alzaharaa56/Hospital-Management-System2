package Service;

import Entity.Appointment;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppointmentService {
    private Scanner scanner = new Scanner(System.in);

    private static List<Appointment> appointmentList = new ArrayList<>();


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
            System.out.print("Enter Time (e.g., 10:00 AM): ");
            String time = scanner.nextLine();
            System.out.print("Enter Reason: ");
            String reason = scanner.nextLine();


            Appointment appointment = new Appointment("", reason, "Scheduled", time, date, docId, patId, appId);
            appointmentList.add(appointment);
            System.out.println("Appointment scheduled successfully!");

        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format. Use YYYY-MM-DD.");
        }
    }


    public void addAppointment(String patientId, String doctorId, LocalDate date) {
        String appId = "APP-" + (appointmentList.size() + 1);
        Appointment appointment = new Appointment("Quick Booking", "General Checkup", "Scheduled", "09:00 AM", date, doctorId, patientId, appId);
        appointmentList.add(appointment);
        System.out.println("Quick appointment booked for " + date);
    }


    public void rescheduleAppointment(String appointmentId, LocalDate newDate, String newTime) {
        Appointment app = getAppointmentById(appointmentId);
        if (app != null) {
            app.setAppointmentDate(newDate);
            app.setAppointmentTime(newTime);
            app.setStatus("Rescheduled");
            System.out.println("Appointment updated to " + newDate + " at " + newTime);
        } else {
            System.out.println("Appointment not found.");
        }
    }


    public void cancelAppointment(String appointmentId) {
        Appointment app = getAppointmentById(appointmentId);
        if (app != null) {
            app.setStatus("Cancelled");
            System.out.println("Appointment " + appointmentId + " has been cancelled.");
        } else {
            System.out.println("Appointment not found.");
        }
    }


    public Appointment getAppointmentById(String appointmentId) {
        for (Appointment app : appointmentList) {
            if (app.getAppointmentId().equals(appointmentId)) return app;
        }
        return null;
    }


    public List<Appointment> getAppointmentsByPatient(String patientId) {
        List<Appointment> results = new ArrayList<>();
        for (Appointment app : appointmentList) {
            if (app.getPatientId().equals(patientId)) results.add(app);
        }
        return results;
    }


    public void removeAppointment(String appointmentId) {
        boolean removed = appointmentList.removeIf(a -> a.getAppointmentId().equals(appointmentId));
        if (removed) {
            System.out.println("Appointment record deleted.");
        } else {
            System.out.println("Record not found.");
        }
    }


    public void displayAllAppointments() {
        if (appointmentList.isEmpty()) {
            System.out.println("No appointments found.");
        } else {
            for (Appointment app : appointmentList) {
                app.displayInfo();
                System.out.println("--------------------");
            }
        }
    }
}