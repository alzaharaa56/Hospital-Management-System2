package Service;

import Behavior.Appointable;
import Behavior.Manageable;
import Behavior.Searchable;
import Entity.Appointment;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public abstract class AppointmentService implements Manageable, Searchable, Appointable {

    private static List<Appointment> appointmentList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);



    @Override
    public void add(Object entity) {
        if (entity instanceof Appointment appt) {

            if (appt.getAppointmentId() != null && searchById(appt.getAppointmentId()) == null) {
                appointmentList.add(appt);
                System.out.println("Success: Appointment [" + appt.getAppointmentId() + "] added.");
            } else {
                System.out.println("Error: Appointment ID already exists or is invalid.");
            }
        }
    }

    @Override
    public void remove(String id) {

        boolean removed = appointmentList.removeIf(a -> a.getAppointmentId().equalsIgnoreCase(id));
        if (removed) {
            System.out.println("Appointment removed successfully.");
        } else {
            System.out.println("Error: Appointment not found.");
        }
    }

    @Override
    public List<Object> getAll() {
        return new ArrayList<>(appointmentList);
    }



    @Override
    public Object searchById(String id) {
        if (id == null) return null;
        return appointmentList.stream()
                .filter(a -> a.getAppointmentId().equalsIgnoreCase(id))
                .findFirst().orElse(null);
    }

    @Override
    public void search(String keyword) {
        System.out.println("\n--- Search Results for: '" + keyword + "' ---");
        List<Appointment> results = appointmentList.stream()
                .filter(a -> a.getPatientId().equalsIgnoreCase(keyword) ||
                        a.getDoctorId().equalsIgnoreCase(keyword))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            System.out.println("No matching appointments found.");
        } else {
            results.forEach(Appointment::displaySummary);
        }
    }



    public void displayAll() {
        System.out.println("\n===== HOSPITAL APPOINTMENT LOG =====");
        if (appointmentList.isEmpty()) {
            System.out.println("No appointments scheduled.");
        } else {
            appointmentList.forEach(Appointment::displaySummary);
        }
    }


    public void displayAllAppointments() {
        displayAll();
    }



    public void createAppointment(String patientId, String doctorId, LocalDate date, String time) {
        if (HelperUtils.isValidString(patientId) && HelperUtils.isValidString(doctorId) && !HelperUtils.isPastDate(date)) {
            Appointment appt = new Appointment();

            appt.setAppointmentId(HelperUtils.generateId("APT"));
            appt.setPatientId(patientId);
            appt.setDoctorId(doctorId);
            appt.setAppointmentDate(date);
            appt.setAppointmentTime(time);
            appt.setStatus("Scheduled");
            add(appt);
        }
    }



    @Override
    public void scheduleAppointment(Appointment appointment) {
        if (appointment != null) {
            add(appointment);
        }
    }



    public void cancelAppointment(String appointmentId) {
        Appointment appt = (Appointment) searchById(appointmentId);
        if (appt != null) {
            appt.cancel();
        } else {
            System.out.println("Appointment not found.");
        }
    }

    private static final AppointmentService appointmentService = new AppointmentService() {
        @Override
        public void rescheduleAppointment(String appointmentId, LocalDate newDate) { }
    };
}