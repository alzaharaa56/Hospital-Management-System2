package Utils;

import java.util.Scanner;
import java.time.LocalDate;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getStringInput(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine();

            if (!HelperUtils.isValidString(input)) {
                System.out.println("Error: Input cannot be empty.");
            }
        } while (!HelperUtils.isValidString(input));
        return input;
    }

    public static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid integer.");
            }
        }
    }

    public static int getIntInput(String prompt, int min, int max) {
        while (true) {
            int val = getIntInput(prompt);

            if (HelperUtils.isValidNumber(val, min, max)) return val;
            System.out.println("Error: Number must be between " + min + " and " + max);
        }
    }

    public static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid decimal number.");
            }
        }
    }

    public static LocalDate getDateInput(String prompt) {
        while (true) {
            String dateStr = getStringInput(prompt + " (YYYY-MM-DD): ");

            if (HelperUtils.isValidDate(dateStr)) {
                return LocalDate.parse(dateStr);
            }
            System.out.println("Error: Invalid date format.");
        }
    }

    public static boolean getConfirmation(String prompt) {
        String res = getStringInput(prompt + " (yes/no): ");
        return res.equalsIgnoreCase("yes") || res.equalsIgnoreCase("y");
    }
}