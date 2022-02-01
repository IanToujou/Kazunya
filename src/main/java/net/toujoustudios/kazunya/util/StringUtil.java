package net.toujoustudios.kazunya.util;

import java.util.concurrent.TimeUnit;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 26/08/2021
 * Time: 23:40
 */
public class StringUtil {

    public static String formatTime(long millis) {

        long hours = millis / TimeUnit.HOURS.toMillis(1);
        long minutes = millis / TimeUnit.MINUTES.toMillis(1);
        long seconds = millis % TimeUnit.MINUTES.toMillis(1) / TimeUnit.SECONDS.toMillis(1);

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);

    }

}
