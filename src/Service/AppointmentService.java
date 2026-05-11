package Service;
import Entity.Appointment;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class AppointmentService {
    Scanner scanner = new Scanner(System.in);


    static List<Appointment> appointments = new ArrayList<>();


    public void addAppointment() {
        System.out.println("Enter Appointment ID:");
        String appointmentId = scanner.nextLine();
        System.out.println("Enter Patient ID:");
        String patientId = scanner.nextLine();
        System.out.println("Enter Doctor ID:");
        String doctorId = scanner.nextLine();
        System.out.println("Enter Appointment Date (YYYY-MM-DD):");
        String dateInput = scanner.nextLine();
        LocalDate appointmentDate = LocalDate.parse(dateInput);
        System.out.println("Enter Appointment Time:");
        String appointmentTime = scanner.nextLine();
        System.out.println("Enter Status (Scheduled/Completed/Cancelled):");
        String status = scanner.nextLine();
        System.out.println("Enter Reason:");
        String reason = scanner.nextLine();
        System.out.println("Enter Notes:");
        String notes = scanner.nextLine();
        Appointment appointment = new Appointment(appointmentId, patientId, doctorId, appointmentDate, appointmentTime, status, reason, notes);
        appointments.add(appointment);
        System.out.println("Appointment scheduled successfully!");

    }
    public void editAppointment(String appointmentId) {
        for (Appointment app : appointments) {
            if (app.getAppointmentId().equals(appointmentId)) {
                System.out.println("Enter updated Date (YYYY-MM-DD):");
                app.setAppointmentDate(LocalDate.parse(scanner.nextLine()));
                System.out.println("Enter updated Time:");
                app.setAppointmentTime(scanner.nextLine());
                System.out.println("Enter updated Status:");
                app.setStatus(scanner.nextLine());
                System.out.println("Appointment updated successfully!");
                return;
            }
        }
        System.out.println("Appointment not found.");
    }
    public void removeAppointment(String appointmentId) {
        boolean removed = appointments.removeIf(app -> app.getAppointmentId().equals(appointmentId));
        if (removed) {
            System.out.println("Appointment removed successfully.");
        } else {
            System.out.println("Appointment not found.");
        }
    }
    public List<Appointment> getAppointmentsByPatient(String patientId) {
        List<Appointment> results = new ArrayList<>();
        for (Appointment app : appointments) {
            if (app.getPatientId().equals(patientId)) {
                results.add(app);
            }
        }
        return results;
    }
    public List<Appointment> getAppointmentsByDoctor(String doctorId) {
        List<Appointment> results = new ArrayList<>();
        for (Appointment app : appointments) {
            if (app.getDoctorId().equals(doctorId)) {
                results.add(app);
            }
        }
        return results;
    }
    public void displayAllAppointments() {
        for (Appointment app : appointments) {
            app.displayInfo();
        }
    }
    public void rescheduleAppointment(String appointmentId, LocalDate newDate, String newTime) {
        for (Appointment app : appointments) {
            if (app.getAppointmentId().equals(appointmentId)) {
                app.setAppointmentDate(newDate);
                app.setAppointmentTime(newTime);
                app.setStatus("Rescheduled");
                System.out.println("Appointment rescheduled successfully.");
                return;
            }
        }
        System.out.println("Appointment not found.");
    }
    public void cancelAppointment(String appointmentId) {
        for (Appointment app : appointments) {
            if (app.getAppointmentId().equals(appointmentId)) {
                app.setStatus("Cancelled");
                System.out.println("Appointment cancelled successfully.");
                return;
            }
        }
        System.out.println("Appointment not found.");
    }
}




