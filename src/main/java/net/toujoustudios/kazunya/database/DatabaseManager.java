package net.toujoustudios.kazunya.database;

import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This file was created by IanToujou.
 * Date: 21.01.2021
 * Time: 09:36
 * Project: Kazunya
 */
public class DatabaseManager {

    private static Connection connection;

    public static void connect() {

        if(isConnected()) {

            disconnect();

        }

        try {

            String url = "jdbc:mysql://" + Config.DATABASE_HOST + ":" + Config.DATABASE_PORT + "/" + Config.DATABASE_NAME + "?useUnicode=true&characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
            connection = DriverManager.getConnection(url, Config.DATABASE_USER, Config.DATABASE_PASSWORD);
            Logger.log(LogLevel.INFORMATION, "The connection to the database has been established.");

        } catch(SQLException exception) {

            exception.printStackTrace();

        }

    }

    public static void disconnect() {

        if(isConnected()) {

            try {

                connection.close();
                Logger.log(LogLevel.INFORMATION, "The database has been disconnected.");

            } catch(Exception exception) {

                exception.printStackTrace();

            }

        }

    }

    public static boolean isConnected() {

        return connection != null;

    }

    public static ResultSet executeQuery(String query) {

        try {

            return connection.prepareStatement(query).executeQuery();

        } catch(SQLException exception) {

            exception.printStackTrace();

        }

        return null;

    }

    public static void executeUpdate(String update) {

        try {

            connection.prepareStatement(update).executeUpdate();

        } catch(SQLException exception) {

            exception.printStackTrace();

        }

    }

    public static Connection getConnection() {

        return connection;

    }

    public static void setup() {

        executeUpdate("CREATE TABLE IF NOT EXISTS guild_settings (guild_id varchar(256), restore_roles boolean DEFAULT false, excluded_restore_roles varchar(256));");

    }

}
