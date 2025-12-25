package net.toujoustudios.kazunya.database;

import lombok.Getter;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.loader.Loader;
import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
public class DatabaseManager {

    private static Connection connection;
    private static final Config config = Config.getFile("database.yml");

    private DatabaseManager() {}

    public static void connect() {
        if(connected()) disconnect();
        try {
            String url = "jdbc:mysql://" + config.getString("database.host") + ":" + config.getString("database.port") + "/" + config.getString("database.name") + "?useUnicode=true&characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
            connection = DriverManager.getConnection(url, config.getString("database.user"), config.getString("database.password"));
        } catch(SQLException exception) {
            Logger.log(LogLevel.FATAL, "Could not establish a connection to the database. Please review the error below:");
            Logger.log(LogLevel.FATAL, exception.getMessage());
            Loader.cancel();
        }
    }

    public static void disconnect() {
        if (!connected()) return;
        try {
            connection.close();
        } catch(Exception exception) {
            Logger.log(LogLevel.ERROR, "Could disconnect the database. Please review the error below:");
            Logger.log(LogLevel.ERROR, exception.getMessage());
        }
    }

    public static boolean connected() {
        return connection != null;
    }

    public static ResultSet executeQuery(String query) {
        try {
            return connection.prepareStatement(query).executeQuery();
        } catch(SQLException exception) {
            Logger.log(LogLevel.ERROR, "Failed to execute SQL: " + query);
            Logger.log(LogLevel.ERROR, exception.getMessage());
        }
        return null;
    }

    public static void executeUpdate(String update) {
        try {
            connection.prepareStatement(update).executeUpdate();
        } catch(SQLException exception) {
            Logger.log(LogLevel.ERROR, "Failed to execute SQL: " + update);
            Logger.log(LogLevel.ERROR, exception.getMessage());
        }
    }

    public static void setup() {
        Loader.ensureLoad();
    }

}
