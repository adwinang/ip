package helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A collection of DateTime related functions
 */
public class StandardDateTime {
    public static DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern("dd MMM yyyy");
    }

    /**
     * Parses a date string into a LocalDate using the defined date format.
     *
     * @param dateString the date string to parse
     * @return the LocalDate corresponding to the date string
     */
    public static LocalDate parseDateString(String dateString) {
        return LocalDate.parse(dateString, getFormatter());
    }

    /**
     * Converts a LocalDate to a String formatted according to the defined date format.
     *
     * @param date the LocalDate to be converted to a String
     * @return the formatted date string
     */
    public static String dateToString(LocalDate date) {
        return date.format(getFormatter());
    }
}
