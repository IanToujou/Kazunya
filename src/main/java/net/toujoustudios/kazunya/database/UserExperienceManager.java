package net.toujoustudios.kazunya.database;

import net.toujoustudios.kazunya.database.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class UserExperienceManager {

    public static void set(String userId, String guildId, int amount) {
        DatabaseManager.executeUpdate("DELETE FROM guild_level_storage WHERE user_id='" + userId + "' AND guild_id='" + guildId + "';");
        DatabaseManager.executeUpdate("INSERT INTO guild_level_storage (guild_id, user_id, experience) VALUES ('" + guildId + "', '" + userId + "', '" + amount + "');");
    }

    public static int get(String userId, String guildId) {
        try {
            ResultSet resultSet = DatabaseManager.executeQuery("SELECT experience FROM guild_level_storage WHERE user_id='" + userId + "' AND guild_id='" + guildId + "';");
            if(resultSet != null && resultSet.next()) {
                return resultSet.getInt("experience");
            }
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static HashMap<String, Integer> getAll(String userId) {
        HashMap<String, Integer> guildExperience = new HashMap<>();
        try {
            ResultSet resultSet = DatabaseManager.executeQuery("SELECT * FROM guild_level_storage WHERE user_id='" + userId + "';");
            if(resultSet != null) {
                while(resultSet.next()) {
                     guildExperience.put(resultSet.getString("guild_id"), resultSet.getInt("experience"));
                }
            }
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
        return guildExperience;
    }

}
