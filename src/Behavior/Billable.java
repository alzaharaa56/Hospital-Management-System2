package Behavior;

public interface Billable {

    // Calculate total charges for services/treatment
    double calculateCharges();

    // Generate bill/invoice
    void generateBill();

    // Process payment
    boolean processPayment(double amount);

}
