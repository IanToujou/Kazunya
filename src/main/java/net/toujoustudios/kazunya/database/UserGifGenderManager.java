package net.toujoustudios.kazunya.database;

import net.toujoustudios.kazunya.model.Gender;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserGifGenderManager {

    public static void set(String userId, Gender gender) {
        DatabaseManager.executeUpdate("INSERT INTO user_settings (id, gif_gender) VALUES ('" + userId + "', '" + gender + "') ON DUPLICATE KEY UPDATE gif_gender='" + gender + "';");
    }

    public static Gender get(String userId) {
        try {
            ResultSet resultSet = DatabaseManager.executeQuery("SELECT gif_gender FROM user_settings WHERE id='" + userId + "';");
            if(resultSet != null && resultSet.next()) {
                return Gender.valueOf(resultSet.getString("gif_gender").toUpperCase());
            }
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
        return Gender.NONE;
    }

}
