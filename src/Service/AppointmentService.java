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
            System.out.print("Enter Time: ");
            String time = scanner.nextLine();

            createAppointment(patId, docId, date, time);

        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format. Use YYYY-MM-DD.");
        }
    }


    public void createAppointment(String patientId, String doctorId, LocalDate date) {
        String appId = "APP-Q-" + (appointmentList.size() + 1);
        Appointment app = new Appointment("Quick Booking", "General Checkup", "Scheduled", "09:00 AM", date, doctorId, patientId, appId);
        appointmentList.add(app);
        System.out.println("Quick appointment booked for " + date);
    }


    public void createAppointment(String patientId, String doctorId, LocalDate date, String time) {
        String appId = "APP-" + (appointmentList.size() + 1);
        Appointment app = new Appointment("Standard Booking", "Consultation", "Scheduled", time, date, doctorId, patientId, appId);
        appointmentList.add(app);
        System.out.println("Appointment scheduled at " + time + " on " + date);
    }


    public void createAppointment(Appointment appointment) {
        if (appointment != null) {
            appointmentList.add(appointment);
            System.out.println("Full appointment object synchronized.");
        }
    }


    public void rescheduleAppointment(String appointmentId, LocalDate newDate) {
        Appointment app = getAppointmentById(appointmentId);
        if (app != null) {
            app.setAppointmentDate(newDate);
            app.setStatus("Rescheduled");
            System.out.println("Date updated successfully.");
        }
    }


    public void rescheduleAppointment(String appointmentId, LocalDate newDate, String newTime) {
        Appointment app = getAppointmentById(appointmentId);
        if (app != null) {
            app.setAppointmentDate(newDate);
            app.setAppointmentTime(newTime);
            app.setStatus("Rescheduled");
            System.out.println("Appointment moved to " + newDate + " at " + newTime);
        } else {
            System.out.println("Error: Appointment not found.");
        }
    }

    public void rescheduleAppointment(Appointment appointment, LocalDate newDate, String newTime, String reason) {
        if (appointment != null) {
            appointment.setAppointmentDate(newDate);
            appointment.setAppointmentTime(newTime);
            appointment.setReason(reason);
            appointment.setStatus("Rescheduled");
            System.out.println("Detailed rescheduling completed.");
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

    public void displayAppointments(LocalDate date) {
        System.out.println("\n--- Appointments on " + date + " ---");
        for (Appointment app : appointmentList) {
            if (app.getAppointmentDate().equals(date)) {
                app.displayInfo();
            }
        }
    }


    public void displayAppointments(String doctorId, LocalDate startDate, LocalDate endDate) {
        System.out.println("\n--- Schedule for Dr. " + doctorId + " ---");
        for (Appointment app : appointmentList) {
            if (app.getDoctorId().equals(doctorId)) {
                LocalDate appDate = app.getAppointmentDate();
                if ((appDate.isEqual(startDate) || appDate.isAfter(startDate)) &&
                        (appDate.isEqual(endDate) || appDate.isBefore(endDate))) {
                    app.displayInfo();
                }
            }
        }
    }



    public void cancelAppointment(String appointmentId) {
        Appointment app = getAppointmentById(appointmentId);
        if (app != null) {
            app.setStatus("Cancelled");
            System.out.println("Appointment cancelled.");
        }
    }

    public Appointment getAppointmentById(String appointmentId) {
        for (Appointment app : appointmentList) {
            if (app.getAppointmentId().equals(appointmentId)) return app;
        }
        return null;
    }

    public void removeAppointment(String appointmentId) {
        if (appointmentList.removeIf(a -> a.getAppointmentId().equals(appointmentId))) {
            System.out.println("Record deleted.");
        }
    }
}