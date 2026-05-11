package Entity;


import java.util.List;
public class Consultant extends Doctor {
    private List<String> consultationTypes;
    private boolean onlineConsultationAvailable;
    private int consultationDuration;

    public Consultant(String id, String firstName) {
        super(id, firstName);
    }

    public void scheduleConsultation() {
        System.out.println("Consultation scheduled.");
    }
    public void provideSecondOpinion() {
        System.out.println("Providing a second medical opinion.");
    }


    public Consultant(String id, String firstName, List<String> types, boolean isOnline, int duration) {
        super(id, firstName);
        this.consultationTypes = types;
        this.onlineConsultationAvailable = isOnline;
        this.consultationDuration = duration;
    }


    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Online availability: " + onlineConsultationAvailable);
    }
}
