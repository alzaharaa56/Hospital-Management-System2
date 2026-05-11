package Entity;


    import java.util.List;

    public class Surgeon extends Doctor {
        private int surgeriesPerformed;
        private List<String> surgeryTypes;
        private boolean operationTheatreAccess;
        public Surgeon(String id, String name, int surgeriesPerformed, List<String> surgeryTypes, boolean access) {
            super(id, name);
            this.surgeriesPerformed = surgeriesPerformed;
            this.surgeryTypes = surgeryTypes;
            this.operationTheatreAccess = access;
        }
        public void performSurgery() {
            System.out.println("Performing surgery...");
        }
        public void updateSurgeryCount() {
            this.surgeriesPerformed++;
        }
        @Override
        public void displayInfo() {
            super.displayInfo();
            System.out.println("Surgeries: " + surgeriesPerformed);
        }
    }

