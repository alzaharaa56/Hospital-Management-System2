package Utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.UUID;
import java.util.Random;
import java.util.List;

public class HelperUtils {

    private static final Random random = new Random();



    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isNull(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    public static boolean isNotNull(String str) {
        return str != null && !str.trim().isEmpty();
    }



    public static boolean isValidString(String str) {
        return str != null && !str.trim().isEmpty();
    }

    public static boolean isValidString(String str, int minLength) {
        if (!isValidString(str)) return false;
        return str.trim().length() >= minLength;
    }

    public static boolean isValidString(String str, int minLength, int maxLength) {
        if (!isValidString(str)) return false;
        int length = str.trim().length();
        return length >= minLength && length <= maxLength;
    }

    public static boolean isValidString(String str, String regex) {
        if (!isValidString(str) || regex == null) return false;
        return str.matches(regex);
    }



    public static String generateId() {
        return UUID.randomUUID().toString();
    }

    public static String generateId(String prefix) {
        if (isNull(prefix)) prefix = "ID";
        int number = 10000 + random.nextInt(90000);
        return prefix + "-" + number;
    }

    public static String generateId(String prefix, int length) {
        if (isNull(prefix)) prefix = "ID";
        if (length <= 0) length = 5;
        int min = (int) Math.pow(10, length - 1);
        int max = (int) Math.pow(10, length) - 1;
        int number = min + random.nextInt(max - min + 1);
        return prefix + "-" + number;
    }

    public static String generateId(String prefix, String suffix) {
        if (isNull(prefix)) prefix = "ID";
        if (isNull(suffix)) suffix = "END";
        int number = 1000 + random.nextInt(9000);
        return prefix + "-" + number + "-" + suffix;
    }

    // ***** 4. Date Validation Methods (Overloaded) *****

    public static boolean isValidDate(LocalDate date) {
        return date != null;
    }

    public static boolean isValidDate(String dateStr) {
        if (isNull(dateStr)) return false;
        try {
            LocalDate.parse(dateStr);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isValidDate(LocalDate date, LocalDate minDate, LocalDate maxDate) {
        if (date == null || minDate == null || maxDate == null) return false;
        return (date.isEqual(minDate) || date.isAfter(minDate)) &&
                (date.isEqual(maxDate) || date.isBefore(maxDate));
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


    public static boolean isNotPastDate(LocalDate date) {
        return isToday(date) || isFutureDate(date);
    }



    public static boolean isValidNumber(int num, int min, int max) {
        return num >= min && num <= max;
    }

    public static boolean isValidNumber(double num, double min, double max) {
        return num >= min && num <= max;
    }

    public static boolean isPositive(int num) {
        return num > 0;
    }

    public static boolean isPositive(double num) {
        return num > 0.0;
    }

    public static boolean isNegative(int num) {
        return num < 0;
    }

    public static boolean isNegative(double num) {
        return num < 0.0;
    }



    public static boolean isValidAge(int age) {
        return age >= 0 && age <= 120;
    }

    public static boolean isValidAge(LocalDate dateOfBirth) {
        if (dateOfBirth == null || dateOfBirth.isAfter(LocalDate.now())) return false;
        int age = Period.between(dateOfBirth, LocalDate.now()).getYears();
        return isValidAge(age);
    }


    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return isValidString(email, emailRegex);
    }


    public static boolean isNumeric(String str) {
        return isValidString(str, "\\d+");
    }



    public static boolean isNotEmpty(List<?> list) {
        return list != null && !list.isEmpty();
    }

    public static boolean isListSize(List<?> list, int requiredSize) {
        return list != null && list.size() == requiredSize;
    }



    public static String capitalize(String str) {
        if (isNull(str)) return "";
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}