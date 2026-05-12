package Utils;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;
import java.util.Random;

/**
 * Task 3.3 & 3.4: Comprehensive Helper Class.
 * Includes Null Checks, String Validation, and ID Generation.
 */
public class Helper {

    // --- Null Check Methods (CRITICAL TO FIX ERRORS) ---

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    /** Checks if a string is null or empty/whitespace only. */
    public static boolean isNull(String str) {
        return str == null || str.trim().isEmpty();
    }

    /** Checks if a string has actual text content. */
    public static boolean isNotNull(String str) {
        return !isNull(str);
    }

    // --- String Validation Methods (CRITICAL TO FIX ERRORS) ---

    /** Validates that a string is not null and not empty. */
    public static boolean isValidString(String str) {
        return isNotNull(str);
    }

    /** Validates string against a regex pattern. */
    public static boolean isValidString(String str, String regex) {
        if (isNull(str)) return false;
        return str.matches(regex);
    }

    // --- ID Generation Methods ---

    public static String generateId() {
        return UUID.randomUUID().toString();
    }

    public static String generateId(String prefix) {
        int randomNumber = new Random().nextInt(90000) + 10000;
        return prefix.toUpperCase() + "-" + randomNumber;
    }

    public static String generateId(String prefix, int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return prefix.toUpperCase() + "-" + sb.toString();
    }

    // --- Date Validation Methods ---

    public static boolean isFutureDate(LocalDate date) {
        return date != null && date.isAfter(LocalDate.now());
    }

    public static boolean isPastDate(LocalDate date) {
        return date != null && date.isBefore(LocalDate.now());
    }

    public static boolean isToday(LocalDate date) {
        return date != null && date.isEqual(LocalDate.now());
    }

    // --- Numeric & Input Validation ---

    public static boolean isPositive(double num) {
        return num > 0;
    }

    public static boolean isValidAge(LocalDate dateOfBirth) {
        if (dateOfBirth == null) return false;
        int age = Period.between(dateOfBirth, LocalDate.now()).getYears();
        return age >= 0 && age <= 120;
    }

    // Helper to print headers for a cleaner console UI
    public static void printHeader(String title) {
        System.out.println("\n=== " + title.toUpperCase() + " ===");
    }
}