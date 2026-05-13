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

public class AppointmentService implements Manageable,Searchable, Appointable {

    static List<Appointment> appointmentList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    DoctorService doctorService = new DoctorService();

    //add new appointment
    public Appointment addAppointment(){

        String appointmentID = HelperUtils.generateId();

        System.out.println("Enter patient Id :");
        String patientId = scanner.nextLine();

        System.out.println("Enter doctor Id :");
        String doctorId = scanner.nextLine();

        System.out.println("Enter appointment Date (yyyy-MM-dd):");
        String appointmentDate = scanner.nextLine();

        LocalDate date;

        try {
            date = LocalDate.parse(appointmentDate);
        } catch (Exception e) {
            System.out.println("Invalid date format.");
            return null;
        }

        System.out.println("Enter appointment Time :");
        String appointmentTime = scanner.nextLine();

        System.out.println("Enter reason :");
        String reason = scanner.nextLine();

        System.out.println("Enter notes :");
        String notes = scanner.nextLine();

        // Validate required fields
        if (!HelperUtils.isValidString(patientId) ||
                !HelperUtils.isValidString(doctorId) ||
                !HelperUtils.isValidString(appointmentTime)) {

            System.out.println("Invalid input data.");
            return null;
        }

        if (HelperUtils.isPastDate(date)) {
            System.out.println("Appointment date cannot be in the past.");
            return null;
        }

        String status = "Scheduled";



        Appointment appointment = new Appointment(notes,reason,status,appointmentTime,date,doctorId,patientId,appointmentID);


        return appointment;
    }

    public List<Appointment> addAppointments(){

        Boolean continueFlag = true;
        while (continueFlag) {

            appointmentList.add(addAppointment());
            System.out.println("Appointment add successfully");

            System.out.println("Enter c to add more , and q to exit");
            if (scanner.nextLine().equalsIgnoreCase("q")) {
                continueFlag = false;
            }
        }
        return appointmentList;

    }

    // edit appointment by ID

    public void editAppointment(String appointmentId){

        if(!HelperUtils.isValidString(appointmentId)){
            System.out.println(" Invalid appointmentId");
            return;
        }

        for(Appointment appointment : appointmentList){

            if(appointment.getAppointmentId().equals(appointmentId)){

                System.out.println("Enter updated patient Id :");
                appointment.setPatientId(scanner.nextLine());

                System.out.println("Enter updated doctor Id :");
                appointment.setDoctorId(scanner.nextLine());

                System.out.println("Enter updated appointment Date :");
                String appointmentDate = scanner.nextLine();
                LocalDate date = LocalDate.parse(appointmentDate);
                appointment.setAppointmentDate(date);

                System.out.println("Enter updated appointment Time :");
                appointment.setAppointmentTime(scanner.nextLine());

                System.out.println("Enter updated status :");
                appointment.setStatus(scanner.nextLine());

                System.out.println("Enter updated reason :");
                appointment.setReason(scanner.nextLine());

                System.out.println("Enter updated notes :");
                appointment.setNotes(scanner.nextLine());

                System.out.println("Appointment updated successfully");


            }

        }

    }

    // Delete appointment by ID
    public void removeAppointment(String appointmentId ){

        if(!HelperUtils.isValidString(appointmentId)){
            System.out.println("Invalid appointmentId");
            return;
        }

        appointmentList.removeIf(A -> A.getAppointmentId() == appointmentId);
        System.out.println("Appointment removed successfully");

        System.out.println("Appointment record not found");
    }

    //retrieve appointment
    public Appointment getAppointment(String appointmentId){

        if(!HelperUtils.isValidString(appointmentId)){
            System.out.println("Invalid appointmentId");
            return null;
        }

        for(Appointment appointment: appointmentList){
            if(appointment.getAppointmentId().equals(appointmentId)){
                return appointment;
            }

        }
        System.out.println("appointment Record not found");
        return null;
    }

    //get Appointments By Patient

    public List<Appointment> getAppointmentsByPatient(String patientId){

        if(!HelperUtils.isValidString(patientId)){
            System.out.println("Invalid patientId");
            return null;
        }

        List<Appointment> patientAppointments = new ArrayList<>();

        for(Appointment appointment : appointmentList){

            if(appointment.getPatientId().equals(patientId)){
                patientAppointments.add(appointment);

            }
        }
        return patientAppointments;
    }

    // get Appointments By Doctor

    public List<Appointment> getAppointmentsByDoctor(String doctorId) {

        if(!HelperUtils.isValidString(doctorId)){
            System.out.println("Invalid doctorId");
            return null;
        }

        List<Appointment> doctorAppointments = new ArrayList<>();

        for(Appointment appointment : appointmentList){
            if(appointment.getDoctorId().equals(doctorId)){

                doctorAppointments.add(appointment);
            }
        }
        return doctorAppointments;
    }

    // get Appointments By Date

    public List<Appointment> getAppointmentsByDate(LocalDate date){

        if(!HelperUtils.isValidDate(date)){
            System.out.println("Invalid date");
            return null;
        }

        List<Appointment> dateAppointments = new ArrayList<>();

        for(Appointment appointment : appointmentList){
            if(appointment.getAppointmentDate().equals(date)){
                dateAppointments.add(appointment);
            }

        }
        return dateAppointments;
    }

    // reschedule Appointment
    public void rescheduleAppointment(String appointmentId, LocalDate newDate, String newTime){

        // Validate inputs
        if (!HelperUtils.isValidString(appointmentId)) {
            System.out.println("Invalid appointment ID.");
            return;
        }

        if (!HelperUtils.isValidDate(newDate) || HelperUtils.isPastDate(newDate)) {
            System.out.println("Invalid appointment date.");
            return;
        }

        if (!HelperUtils.isValidString(newTime)) {
            System.out.println("Invalid appointment time.");
            return;
        }
        for(Appointment appointment : appointmentList){

            if(appointment.getAppointmentId().equals(appointmentId)){

                appointment.setAppointmentDate(newDate);
                appointment.setAppointmentTime(newTime);
                appointment.setStatus("Rescheduled");

                System.out.println("Appointment rescheduled successfully.");
                return;
            }
        }

        System.out.println("Appointment not found.");

    }

    // cancel Appointment

    @Override
    public void scheduleAppointment(Appointment appointment) {

        // Validate appointment object
        if (HelperUtils.isNull(appointment)) {
            System.out.println("Invalid appointment.");
            return;
        }

        // Validate appointment date
        if (!HelperUtils.isValidDate(appointment.getAppointmentDate()) ||
                HelperUtils.isPastDate(appointment.getAppointmentDate())) {

            System.out.println("Invalid appointment date.");
            return;
        }

        // Validate appointment time
        if (!HelperUtils.isValidString(appointment.getAppointmentTime())) {
            System.out.println("Invalid appointment time.");
            return;
        }

        // Schedule appointment
        appointment.setStatus("Scheduled");
        appointmentList.add(appointment);

        System.out.println("Appointment scheduled successfully.");


    }

    public void cancelAppointment(String appointmentId){

        // Validate input
        if (!HelperUtils.isValidString(appointmentId)) {
            System.out.println("Invalid appointment ID.");
            return;
        }

        // Search and cancel
        for(Appointment appointment : appointmentList){

            if(appointment.getAppointmentId().equals(appointmentId)){
                appointment.setStatus("Cancelled");

                System.out.println("Appointment cancelled successfully.");
                return;
            }
        }
        System.out.println("Appointment not found.");

    }

    public void createAppointment(String patientId, String doctorId, LocalDate date){

        // Validate patient ID
        if (!HelperUtils.isValidString(patientId)) {
            System.out.println("Invalid patient ID.");
            return;
        }

        // Validate doctor ID
        if (!HelperUtils.isValidString(doctorId)) {
            System.out.println("Invalid doctor ID.");
            return;
        }

        // Validate date
        if (!HelperUtils.isValidDate(date) || HelperUtils.isPastDate(date)) {
            System.out.println("Invalid appointment date.");
            return;
        }

        // Create appointment
        Appointment appointment = new Appointment();

        appointment.setPatientId(patientId);
        appointment.setDoctorId(doctorId);
        appointment.setAppointmentDate(date);
        appointment.setStatus("Scheduled");

        appointmentList.add(appointment);

        System.out.println("Appointment created successfully.");

    }

    //Overloaded createAppointment(String patientId, String doctorId, LocalDate date, String time)

    public void createAppointment(String patientId, String doctorId, LocalDate date ,String time){

        // Validate patient ID
        if (!HelperUtils.isValidString(patientId)) {
            System.out.println("Invalid patient ID.");
            return;
        }

        // Validate doctor ID
        if (!HelperUtils.isValidString(doctorId)) {
            System.out.println("Invalid doctor ID.");
            return;
        }

        // Validate date
        if (!HelperUtils.isValidDate(date) || HelperUtils.isPastDate(date)) {
            System.out.println("Invalid appointment date.");
            return;
        }

        // Validate time
        if (!HelperUtils.isValidString(time)) {
            System.out.println("Invalid appointment time.");
            return;
        }

        // Create appointment
        Appointment appointment = new Appointment();

        appointment.setPatientId(patientId);
        appointment.setDoctorId(doctorId);
        appointment.setAppointmentDate(date);
        appointment.setAppointmentTime(time);
        appointment.setStatus("Scheduled");

        appointmentList.add(appointment);

        System.out.println("Appointment created successfully.");

    }

    // Overloaded createAppointment(Appointment appointment)
    public void createAppointment(Appointment appointment){

        // Validate appointment object
        if (HelperUtils.isNull(appointment)) {
            System.out.println("Invalid appointment.");
            return;
        }

        // Validate required fields (basic safety checks)
        if (!HelperUtils.isValidString(appointment.getPatientId())) {
            System.out.println("Invalid patient ID.");
            return;
        }

        if (!HelperUtils.isValidString(appointment.getDoctorId())) {
            System.out.println("Invalid doctor ID.");
            return;
        }

        if (!HelperUtils.isValidDate(appointment.getAppointmentDate()) ||
                HelperUtils.isPastDate(appointment.getAppointmentDate())) {

            System.out.println("Invalid appointment date.");
            return;
        }

        if (!HelperUtils.isValidString(appointment.getAppointmentTime())) {
            System.out.println("Invalid appointment time.");
            return;
        }

        // Set status
        appointment.setStatus("Scheduled");

        // Add appointment
        appointmentList.add(appointment);

        System.out.println("Appointment created successfully.");

    }

    // Overloaded rescheduleAppointment(String appointmentId, LocalDate newDate)

    public void rescheduleAppointment(String appointmentId, LocalDate newDate){

        Appointment appointment = getAppointment(appointmentId);
        appointment.setAppointmentDate(newDate);

        appointment.setStatus("Rescheduled");
        System.out.println("Appointment rescheduled successfully.");

    }

    // Overloaded rescheduleAppointment(Appointment appointment, LocalDate newDate, String newTime, String reason)

    public void rescheduleAppointment(Appointment appointment, LocalDate newDate, String newTime, String reason){

        appointment.setAppointmentDate(newDate);
        appointment.setAppointmentTime(newTime);
        appointment.setReason(reason);
        appointment.setStatus("Rescheduled");

        System.out.println("Appointment rescheduled successfully.");

    }

    // Overloaded displayAppointments(LocalDate date)
    public void displayAppointments(LocalDate date){

        for(Appointment appointment: appointmentList){

            if(appointment.getAppointmentDate().equals(date)){

                appointment.displayInfo();
            }
        }
    }

    // Overloaded displayAppointments(String doctorId, LocalDate startDate, LocalDate endDate)

    public void displayAppointments(String doctorId, LocalDate startDate, LocalDate endDate){



        for(Appointment appointment: appointmentList){

            if(appointment.getDoctorId().equals(doctorId)){

                LocalDate appointmentDate = appointment.getAppointmentDate();

                if ((appointmentDate.isEqual(startDate) || appointmentDate.isAfter(startDate)) &&
                        (appointmentDate.isEqual(endDate) || appointmentDate.isBefore(endDate))) {

                    appointment.displayInfo();

                }


            }




        }

    }

    @Override
    public void add(Object entity) {

    }

    @Override
    public void remove(String id) {

    }

    @Override
    public List<Object> getAll() {
        return List.of();
    }

    @Override
    public void search(String keyword) {

    }

    @Override
    public Object searchById(String id) {
        return null;
    }
}