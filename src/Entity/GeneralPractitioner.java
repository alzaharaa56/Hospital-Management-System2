package Entity;

public class GeneralPractitioner extends Doctor {
    private boolean walkinAvailable;
    private boolean homeVisitAvailable;
    private boolean vaccinationCertified;

    public GeneralPractitioner(String name, int id, boolean walkin, boolean homeVisit, boolean cert) {
        super(name, id);
        this.walkinAvailable = walkin;
        this.homeVisitAvailable = homeVisit;
        this.vaccinationCertified = cert;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Accepts Walk-ins: " + (walkinAvailable ? "Yes" : "No"));
        System.out.println("Home Visits: " + (homeVisitAvailable ? "Available" : "N/A"));
    }

    public void scheduleHomeVisit(String address) {
        if (homeVisitAvailable) {
            System.out.println("Home visit scheduled at: " + address);
        } else {
            System.out.println("Home visits are not offered by this practitioner.");
        }
    }

    public void administerVaccine(String vaccineName) {
        if (vaccinationCertified) {
            System.out.println("Administering " + vaccineName + " vaccine.");
        } else {
            System.out.println("Practitioner is not certified to administer vaccines.");
        }
    }
}