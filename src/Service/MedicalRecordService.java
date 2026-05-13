package Service;

import Behavior.Manageable;
import Behavior.Searchable;
import Entity.MedicalRecord;
import Entity.Nurse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MedicalRecordService implements Manageable, Searchable {

    Scanner scanner = new Scanner(System.in);
    static List<MedicalRecord> medicalRecordList = new ArrayList<>();

    public MedicalRecord addMedicalRecord() {

        System.out.println("Enter record Id :");
        String recordId = scanner.nextLine();

        System.out.println("Enter patient Id :");
        String patientId = scanner.nextLine();

        System.out.println("Enter doctor Id :");
        String doctorId = scanner.nextLine();

        System.out.println("Enter visit Date:");
        String visitDate = scanner.nextLine();
        LocalDate date = LocalDate.parse(visitDate);

        System.out.println("Enter diagnosis :");
        String diagnosis = scanner.nextLine();

        System.out.println("Enter prescription :");
        String prescription = scanner.nextLine();

        System.out.println("Enter test Results :");
        String testResults = scanner.nextLine();

        System.out.println("Enter notes :");
        String notes = scanner.nextLine();

        MedicalRecord medicalRecord = new MedicalRecord(recordId,patientId,doctorId,date,diagnosis,testResults,prescription,notes);

        return medicalRecord;
    }

    public List<MedicalRecord> addMedicalRecords() {

        Boolean continueFlag = true;
        while (continueFlag) {

            medicalRecordList.add(addMedicalRecord());
            System.out.println("Medical record add successfully");

            System.out.println("Enter c to add more , and q to exit");
            if (scanner.nextLine().equalsIgnoreCase("q")) {
                continueFlag = false;
            }
        }
        return medicalRecordList;

    }

    public void editMedicalRecord(String recordId) {

        for(MedicalRecord medicalRecord : medicalRecordList){

            if(medicalRecord.getRecordId().equals(recordId)){

                System.out.println("Enter updated patient Id :");
                medicalRecord.setPatientId(scanner.nextLine());

                System.out.println("Enter updated doctor Id :");
                medicalRecord.setDoctorId(scanner.nextLine());

                System.out.println("Enter updated visit Date:");
                String visitDate = scanner.nextLine();
                LocalDate date = LocalDate.parse(visitDate);
                medicalRecord.setVisitDate(date);

                System.out.println("Enter updated diagnosis :");
                medicalRecord.setDiagnosis(scanner.nextLine());

                System.out.println("Enter updated prescription :");
                medicalRecord.setPrescription(scanner.nextLine());

                System.out.println("Enter updated test Results :");
                medicalRecord.setTestResults(scanner.nextLine());

                System.out.println("Enter updated notes :");
                medicalRecord.setNotes(scanner.nextLine());

                System.out.println("Medical record updated successfully");


            }

        }

    }

    // remove medical record by ID
    public void removeMedicalRecord(String recordId){

        medicalRecordList.removeIf(M -> M.getRecordId() == recordId);
        System.out.println("medical record removed successfully");

        System.out.println("medical record not found");

    }

    //retrieve medical record
    public MedicalRecord getMedicalRecord(String recordId){

        for(MedicalRecord medicalRecord: medicalRecordList){
            if(medicalRecord.getRecordId().equals(recordId)){
                return medicalRecord;
            }

        }
        System.out.println("medical Record not found");
        return null;
    }

    //get Records By PatientId
    public List<MedicalRecord> getRecordsByPatientId(String patientId){

        List<MedicalRecord> patientMedicalRecords = new ArrayList<>();

        for (MedicalRecord medicalRecord : medicalRecordList){

            if(medicalRecord.getPatientId().equals(patientId)){
                patientMedicalRecords.add(medicalRecord);
            }
        }

        return patientMedicalRecords;
    }

    // get Records By DoctorId

    public List<MedicalRecord> getRecordsByDoctorId(String doctorId){

        List<MedicalRecord> doctorMedicalRecords = new ArrayList<>();

        for (MedicalRecord medicalRecord : medicalRecordList){

            if(medicalRecord.getDoctorId().equals(doctorId)){
                doctorMedicalRecords.add(medicalRecord);
            }
        }

        return doctorMedicalRecords;
    }

    //display PatientHistory

    public void displayPatientHistory(String patientId) {

        for(MedicalRecord medicalRecord : medicalRecordList){
            if(medicalRecord.getPatientId().equals(patientId)){
                medicalRecord.displayInfo();
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