package Service;

import Entity.Appointment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService {

    private static List<Appointment> appointments = new ArrayList<>();


    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        System.out.println("Appointment added successfully: " + appointment.getAppointmentId());
    }


    public void editAppointment(String appointmentId, Appointment updatedAppointment) {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getAppointmentId().equals(appointmentId)) {
                appointments.set(i, updatedAppointment);
                System.out.println("Appointment updated successfully: " + appointmentId);
                return;
            }
        }
        System.out.println("Appointment not found with ID: " + appointmentId);
    }


    public void removeAppointment(String appointmentId) {
        boolean removed = appointments.removeIf(a -> a.getAppointmentId().equals(appointmentId));
        if (removed) {
            System.out.println("Appointment removed successfully: " + appointmentId);
        } else {
            System.out.println("Appointment not found with ID: " + appointmentId);
        }
    }


    public Appointment getAppointmentById(String appointmentId) {
        for (Appointment a : appointments) {
            if (a.getAppointmentId().equals(appointmentId)) {
                return a;
            }
        }
        System.out.println("Appointment not found with ID: " + appointmentId);
        return null;
    }


    public void displayAllAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments available.");
        } else {
            for (Appointment a : appointments) {
                a.displayInfo();
                System.out.println("----------------------");
            }
        }
    }


    public List<Appointment> getAppointmentsByPatient(String patientId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : appointments) {
            if (a.getPatientId().equalsIgnoreCase(patientId)) {
                result.add(a);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No appointments found for patient ID: " + patientId);
        }
        return result;
    }


    public List<Appointment> getAppointmentsByDoctor(String doctorId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : appointments) {
            if (a.getDoctorId().equalsIgnoreCase(doctorId)) {
                result.add(a);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No appointments found for doctor ID: " + doctorId);
        }
        return result;
    }


    public List<Appointment> getAppointmentsByDate(LocalDate date) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : appointments) {
            if (a.getAppointmentDate().equals(date)) {
                result.add(a);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No appointments found on date: " + date);
        }
        return result;
    }


    public void rescheduleAppointment(String appointmentId, LocalDate newDate, String newTime) {
        Appointment appointment = getAppointmentById(appointmentId);
        if (appointment != null) {
            appointment.setAppointmentDate(newDate);
            appointment.setAppointmentTime(newTime);
            appointment.setStatus("Rescheduled");
            System.out.println("Appointment rescheduled successfully: " + appointmentId);
        }
    }


    public void cancelAppointment(String appointmentId) {
        Appointment appointment = getAppointmentById(appointmentId);
        if (appointment != null) {
            appointment.setStatus("Cancelled");
            System.out.println("Appointment cancelled successfully: " + appointmentId);
        }
    }
}

