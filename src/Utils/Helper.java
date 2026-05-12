package Utils;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;
import java.util.Random;

/**
 * Task 3.3: Comprehensive HelperUtils.
 * Final Modules: ID Generation, Date Validation, Numeric Validation, and Input Logic.
 */
public class Helper {

    // --- ID Generation Methods (Overloaded) ---

    /** Generates a standard random UUID. */
    public static String generateId() {
        return UUID.randomUUID().toString();
    }

    /** Generates an ID with a prefix and 5 random digits (e.g., PAT-12345). */
    public static String generateId(String prefix) {
        int randomNumber = new Random().nextInt(90000) + 10000;
        return prefix.toUpperCase() + "-" + randomNumber;
    }

    /** Generates an ID with a prefix and a specific length of random numbers. */
    public static String generateId(String prefix, int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return prefix.toUpperCase() + "-" + sb.toString();
    }

    /** Generates an ID with both a prefix and a suffix (e.g., ER-5521-VIP). */
    public static String generateId(String prefix, String suffix) {
        return prefix.toUpperCase() + "-" + (new Random().nextInt(900) + 100) + "-" + suffix.toUpperCase();
    }

    // --- Date Validation Methods (Overloaded) ---

    public static boolean isValidDate(LocalDate date) {
        return date != null;
    }

    public static boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidDate(LocalDate date, LocalDate minDate, LocalDate maxDate) {
        if (date == null) return false;
        return !date.isBefore(minDate) && !date.isAfter(maxDate);
    }

    public static boolean isFutureDate(LocalDate date) {
        return date != null && date.isAfter(LocalDate.now());
    }

    public static boolean isPastDate(LocalDate date) {
        return date != null && date.isBefore(LocalDate.now());
    }

    public static boolean isToday(LocalDate date) {
        return date != null && date.isEqual(LocalDate.now());
    }

    // --- Numeric Validation Methods (Overloaded) ---

    public static boolean isValidNumber(int num, int min, int max) {
        return num >= min && num <= max;
    }

    public static boolean isValidNumber(double num, double min, double max) {
        return num >= min && num <= max;
    }

    public static boolean isPositive(int num) { return num > 0; }
    public static boolean isPositive(double num) { return num > 0; }
    public static boolean isNegative(int num) { return num < 0; }
    public static boolean isNegative(double num) { return num < 0; }

    // --- Input Validation Methods (Overloaded) ---

    /** Standard human age check (0-120). */
    public static boolean isValidAge(int age) {
        return age >= 0 && age <= 120;
    }

    /** Calculates age from DOB and validates it. */
    public static boolean isValidAge(LocalDate dateOfBirth) {
        if (dateOfBirth == null) return false;
        int age = Period.between(dateOfBirth, LocalDate.now()).getYears();
        return isValidAge(age);
    }
}