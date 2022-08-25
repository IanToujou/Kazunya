package net.toujoustudios.kazunya.log;

import net.toujoustudios.kazunya.config.Config;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A logger to print logs to the console and to a file.
 *
 * @author Ian Toujou
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class Logger {

    public static final Config config = Config.getDefault();

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BRIGHT_RED = "\u001b[31;1m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void log(LogLevel level, String message) {

        if(level == LogLevel.DEBUG && !config.getBoolean("general.debug")) return;

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", new Locale("de", "DE"));
        SimpleDateFormat fileDate = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();

        System.out.println("[" + format.format(date) + " - " + level + "] " + level.getColor() + message + ANSI_RESET);

        String filename = "logs/" + (new SimpleDateFormat("dd-MM-yyyy").format(new Date())) + ".log";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
            writer.write("[" + format.format(date) + " - " + level + "] " + message + "\n");
            writer.close();
        } catch(IOException exception) {
            exception.printStackTrace();
        }

    }

}
