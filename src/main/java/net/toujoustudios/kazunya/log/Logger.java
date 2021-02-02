package net.toujoustudios.kazunya.log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * This file was created by IanToujou.
 * Date: 02.01.2021
 * Time: 20:00
 * Project: Kazunya
 */
public class Logger {

    public static void log(LogLevel level, String message) {

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", new Locale("de", "DE"));
        SimpleDateFormat fileDate = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("[" + format.format(new Date()) + " - " + level + "] " + message);

    }

}
