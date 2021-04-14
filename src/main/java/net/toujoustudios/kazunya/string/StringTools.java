package net.toujoustudios.kazunya.string;

import java.util.concurrent.TimeUnit;

/**
 * This file was created by IanToujou.
 * Date: 24.10.2020
 * Time: 12:57
 * Project: Kazunya
 */
public class StringTools {

    public static String formatTime(long millis) {

        long hours = millis / TimeUnit.HOURS.toMillis(1);
        long minutes = millis / TimeUnit.MINUTES.toMillis(1);
        long seconds = millis % TimeUnit.MINUTES.toMillis(1) / TimeUnit.SECONDS.toMillis(1);

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);

    }

}
