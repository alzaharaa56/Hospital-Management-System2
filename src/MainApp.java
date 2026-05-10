import Service.PatientService;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        PatientService patientService = new PatientService();
        patientService.addPatients();
        System.out.println("enter name to search");
        patientService.searchPatientsByName(scanner.nextLine());
    }
}