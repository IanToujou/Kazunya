package net.toujoustudios.kazunya.util;

import java.util.concurrent.TimeUnit;

/**
 * The StringUtil class is used to format and process strings.
 * @since 1.0.0
 * @author Ian Toujou
 */
public class StringUtil {

    /**
     * Formats a specific time, given in milliseconds.
     * @param millis The amount of milliseconds.
     * @return A formatted time string.
     * @since 1.0.0
     */
    public static String formatTime(long millis) {
        long hours = millis / TimeUnit.HOURS.toMillis(1);
        long minutes = millis / TimeUnit.MINUTES.toMillis(1);
        long seconds = millis % TimeUnit.MINUTES.toMillis(1) / TimeUnit.SECONDS.toMillis(1);
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    /**
     * Formats a specific time, given in milliseconds, with a custom format.
     * @param millis The amount of milliseconds.
     * @param format The resulting time format as a string.
     * @return A formatted time string.
     * @since 1.0.1
     */
    public static String formatTime(long millis, String format) {
        long hours = millis / TimeUnit.HOURS.toMillis(1);
        long minutes = millis / TimeUnit.MINUTES.toMillis(1);
        long seconds = millis % TimeUnit.MINUTES.toMillis(1) / TimeUnit.SECONDS.toMillis(1);
        return String.format(format, hours, minutes, seconds);
    }

}
