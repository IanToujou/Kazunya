package net.toujoustudios.kazunya.log;

import net.toujoustudios.kazunya.config.Config;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 26/08/2021
 * Time: 23:05
 */
public class Logger {

    public static final Config config = Config.getDefault();

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BRIGHT_RED = "\u001b[31;1m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void log(LogLevel level, String message) {

        if (level == LogLevel.DEBUG && !config.getBoolean("general.debug")) return;

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", new Locale("de", "DE"));
        SimpleDateFormat fileDate = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("[" + Config.getDefault().getString("general.name") + " - " + level + "] " + level.getColor() + message + ANSI_RESET);

    }

}
