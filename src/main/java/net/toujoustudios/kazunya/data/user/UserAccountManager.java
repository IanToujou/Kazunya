package net.toujoustudios.kazunya.data.user;

import net.toujoustudios.kazunya.database.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserAccountManager {

    public static void setAccount(String id, UserAccount account) {
        DatabaseManager.executeUpdate("INSERT INTO user_accounts (id, name, discriminator, avatar) VALUES ('" + id + "', '" + account.getName() + "', '" + account.getDiscriminator() + "', '" + account.getAvatar() + "') ON DUPLICATE KEY UPDATE name='" + account.getName() + "', discriminator='" + account.getDiscriminator() + "', avatar='" + account.getAvatar() + "';");
    }

    public static UserAccount getAccount(String id) {
        try {
            ResultSet resultSet = DatabaseManager.executeQuery("SELECT * FROM user_accounts WHERE id='" + id + "';");
            if(resultSet != null && resultSet.next())
                return new UserAccount(resultSet.getString("name"), resultSet.getString("discriminator"), resultSet.getString("avatar"));
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static ArrayList<UserAccount> getAllAccounts() {
        ArrayList<UserAccount> accounts = new ArrayList<>();
        try {
            ResultSet resultSet = DatabaseManager.executeQuery("SELECT * FROM user_accounts;");
            if(resultSet != null && resultSet.next())
                accounts.add(new UserAccount(resultSet.getString("name"), resultSet.getString("discriminator"), resultSet.getString("avatar")));
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
        return accounts;
    }

}
