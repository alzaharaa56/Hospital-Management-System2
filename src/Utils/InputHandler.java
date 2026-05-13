package Utils;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static int getIntInput(String prompt) {
        try {
            System.out.print(prompt);
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Try again.");
            return getIntInput(prompt);
        }
    }

    // Task 3.6: Overloaded method with min and max
    public static int getIntInput(String prompt, int min, int max) {
        int val = getIntInput(prompt);
        if (val >= min && val <= max) return val;
        System.out.println("Please enter a number between " + min + " and " + max);
        return getIntInput(prompt, min, max);
    }

    public static double getDoubleInput(String prompt) {
        try {
            System.out.print(prompt);
            return Double.parseDouble(scanner.nextLine());
        } catch (Exception e) { return getDoubleInput(prompt); }
    }

    public static LocalDate getDateInput(String prompt) {
        try {
            System.out.print(prompt + " (YYYY-MM-DD): ");
            return LocalDate.parse(scanner.nextLine());
        } catch (DateTimeParseException e) { return getDateInput(prompt); }
    }

    public static boolean getConfirmation(String prompt) {
        String res = getStringInput(prompt + " (yes/no): ");
        return res.equalsIgnoreCase("yes") || res.equalsIgnoreCase("y");
    }
}