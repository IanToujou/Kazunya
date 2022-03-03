package net.toujoustudios.kazunya.log;

import net.toujoustudios.kazunya.database.DatabaseManager;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 03/03/2022
 * Time: 19:36
 */
public class DatabaseLogger {

    public static void logCommand(CommandLog log) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("de", "DE"));
        String timeFormat = simpleDateFormat.format(log.getExecutionTime());
        DatabaseManager.executeUpdate("INSERT INTO command_logs (command_id, execution_successful, execution_time, user_id, server_id, full_command) VALUES ('" + log.getCommandId() + "', " + log.isExecutionSuccessful() + ", '" + timeFormat + "', '" + log.getUserId() + "', '" + log.getServerId() + "', '" + log.getFullCommand() + "');");
    }

}
