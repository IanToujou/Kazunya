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

public class DatabaseManager {

    @Getter
    private static Connection connection;
    private static final Config config = Config.getFile("database.yml");

    public static void connect() {
        if(isConnected()) {
            disconnect();
        }
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
        if(isConnected()) {
            try {
                connection.close();
            } catch(Exception exception) {
                Logger.log(LogLevel.ERROR, "Could disconnect the database. Please review the error below:");
                Logger.log(LogLevel.ERROR, exception.getMessage());
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

    public static void setup() {
        //Ensure that the bot has been loaded.
        Loader.ensureLoad();
        //Create user related databases.
        executeUpdate("CREATE TABLE IF NOT EXISTS user_account (`id` VARCHAR(256) NOT NULL, `name` VARCHAR(256) NOT NULL, `avatar` VARCHAR(512) NOT NULL, PRIMARY KEY (`id`)) ENGINE = InnoDB;");
        executeUpdate("CREATE TABLE IF NOT EXISTS user_ban (`id` VARCHAR(256) NOT NULL, `reason` VARCHAR(256) NOT NULL, `until` DATETIME NULL DEFAULT NULL, `date` DATETIME NOT NULL, PRIMARY KEY (`id`)) ENGINE = InnoDB;");
        executeUpdate("CREATE TABLE IF NOT EXISTS user_money (`id` VARCHAR(256) NOT NULL, `bank` INT NOT NULL DEFAULT '0', `wallet` INT NOT NULL DEFAULT '0', PRIMARY KEY (`id`)) ENGINE = InnoDB;");
        executeUpdate("CREATE TABLE IF NOT EXISTS user_job (`id` VARCHAR(256) NOT NULL, `job` VARCHAR(256) NULL DEFAULT NULL, `position` VARCHAR(256) NULL DEFAULT NULL, PRIMARY KEY (`id`)) ENGINE = InnoDB;");
        executeUpdate("CREATE TABLE IF NOT EXISTS user_skill (`id` VARCHAR(256) NOT NULL, `skill` VARCHAR(256) NULL DEFAULT NULL, `experience` INT NOT NULL DEFAULT '0') ENGINE = InnoDB;");
        executeUpdate("CREATE TABLE IF NOT EXISTS user_config (`id` VARCHAR(256) NOT NULL, `nsfw_enabled` BOOLEAN NOT NULL DEFAULT FALSE, `gif_gender` VARCHAR(256) NULL DEFAULT NULL, PRIMARY KEY (`id`)) ENGINE = InnoDB;");
        //executeUpdate("CREATE TABLE IF NOT EXISTS guild_level_config (`id` VARCHAR(256) NOT NULL, PRIMARY KEY (`id`)) ENGINE = InnoDB");
        executeUpdate("CREATE TABLE IF NOT EXISTS guild_level_storage (`id` INT NOT NULL AUTO_INCREMENT, `guild_id` VARCHAR(256) NOT NULL, `user_id` VARCHAR(256) NOT NULL, `experience` INT NOT NULL, PRIMARY KEY (`id`)) ENGINE = InnoDB;");
        executeUpdate("CREATE TABLE IF NOT EXISTS user_settings (`id` VARCHAR(256) NOT NULL, `gif_gender` VARCHAR(256) NOT NULL, PRIMARY KEY (`id`)) ENGINE = InnoDB;");
    }

}
