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
public class Logger {

    public static final Config config = Config.getDefault();
    public static final String ANSI_RESET = "\u001B[0m";

    public static void log(LogLevel level, String message) {

        if(level == LogLevel.SILENT && !config.getBoolean("general.silent")) return;
        if(level == LogLevel.DEBUG && !config.getBoolean("general.debug")) return;

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", new Locale("de", "DE"));
        SimpleDateFormat fileDate = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();

        System.out.println("[" + format.format(date) + " - " + level.getColor() + level + ANSI_RESET + "] " + message + ANSI_RESET);

        String filename = "logs/" + fileDate.format(new Date()) + ".log";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
            writer.write("[" + format.format(date) + " - " + level + "] " + message + "\n");
            writer.close();
        } catch(IOException ignored) {}

    }

}
