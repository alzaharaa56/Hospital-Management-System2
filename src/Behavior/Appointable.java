package Behavior;

import Entity.Appointment;

import java.time.LocalDate;

public interface Appointable {

    // Schedule a new appointment
    void scheduleAppointment(Appointment appointment);

    // Cancel an appointment using ID
    void cancelAppointment(String appointmentId);

    // Reschedule an existing appointment
    void rescheduleAppointment(String appointmentId, LocalDate newDate);

}