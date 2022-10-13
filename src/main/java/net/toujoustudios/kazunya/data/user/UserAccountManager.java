package net.toujoustudios.kazunya.data.user;

import net.toujoustudios.kazunya.database.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccountManager {

    public static void setAccount(String id, UserAccount account) {
        DatabaseManager.executeUpdate("INSERT INTO user_accounts (id, name, avatar) VALUES ('" + id + "', '" + account.getName() + "', '" + account.getAvatar() + "') ON DUPLICATE KEY UPDATE name='" + account.getName() + "', avatar='" + account.getAvatar() + "';");
    }

    public static UserAccount getAccount(String id) {
        try {
            ResultSet resultSet = DatabaseManager.executeQuery("SELECT * FROM user_accounts WHERE id='" + id + "';");
            if(resultSet != null && resultSet.next())
                return new UserAccount(resultSet.getString("name"), resultSet.getString("avatar"));
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

}
