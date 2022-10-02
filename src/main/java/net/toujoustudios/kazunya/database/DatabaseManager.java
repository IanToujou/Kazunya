package net.toujoustudios.kazunya.database;

import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.loader.Loader;
import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 26/08/2021
 * Time: 23:23
 */
public class DatabaseManager {

    private static Connection connection;
    private static final Config config = Config.getFile("database.yml");

    public static void connect() {
        if(isConnected()) {
            disconnect();
        }
        try {
            String url = "jdbc:mysql://" + config.getString("database.host") + ":" + config.getString("database.port") + "/" + config.getString("database.name") + "?useUnicode=true&characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
            connection = DriverManager.getConnection(url, config.getString("database.user"), config.getString("database.password"));
            Logger.log(LogLevel.INFORMATION, "The connection to the database has been established.");
        } catch(SQLException exception) {
            Logger.log(LogLevel.FATAL, "Could not establish a connection to the database. Please review the error below:");
            Logger.log(LogLevel.FATAL, exception.getMessage());
            Loader.cancel();
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
        Loader.ensureLoad();
        executeUpdate("CREATE TABLE IF NOT EXISTS users (`id` VARCHAR(256) NOT NULL, `banned` BOOLEAN NOT NULL DEFAULT FALSE, PRIMARY KEY (`id`)) ENGINE = InnoDB;");
        executeUpdate("CREATE TABLE IF NOT EXISTS user_relations (`id` VARCHAR(256) NOT NULL, `partner_id` VARCHAR(256) NULL DEFAULT NULL, PRIMARY KEY (`id`)) ENGINE = InnoDB;");
        executeUpdate("CREATE TABLE IF NOT EXISTS user_money (`id` VARCHAR(256) NOT NULL, `account_money` INT NOT NULL DEFAULT '0', `wallet_money` INT NOT NULL DEFAULT '0', PRIMARY KEY (`id`)) ENGINE = InnoDB;");
        executeUpdate("CREATE TABLE IF NOT EXISTS user_jobs (`id` VARCHAR(256) NOT NULL, `job` VARCHAR(256) NULL DEFAULT NULL, `position` VARCHAR(256) NULL DEFAULT NULL, PRIMARY KEY (`id`)) ENGINE = InnoDB;");
        executeUpdate("CREATE TABLE IF NOT EXISTS jobs (`id` VARCHAR(256) NOT NULL, `name` VARCHAR(256) NULL DEFAULT NULL, `description` VARCHAR(256) NULL DEFAULT NULL, `positions` VARCHAR(256) NULL DEFAULT NULL, PRIMARY KEY (`id`)) ENGINE = InnoDB;");
        executeUpdate("CREATE TABLE IF NOT EXISTS job_positions (`id` VARCHAR(256) NOT NULL, `job` VARCHAR(256) NULL DEFAULT NULL, `grade` INT NOT NULL DEFAULT '0', `name` VARCHAR(256) NULL DEFAULT NULL, `salary` INT NOT NULL DEFAULT '0', PRIMARY KEY (`id`)) ENGINE = InnoDB;");
    }

}
