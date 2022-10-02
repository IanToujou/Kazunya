package net.toujoustudios.kazunya.data;

import net.toujoustudios.kazunya.database.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDataManager {

    public static void setBanned(String userId, boolean usageBanned) {
        DatabaseManager.executeUpdate("INSERT INTO users (id, banned) VALUES ('" + userId + "', '" + (usageBanned ? 1 : 0) + "') ON DUPLICATE KEY UPDATE banned='" + (usageBanned ? 1 : 0) + "';");
    }

    public static boolean isBanned(String userId) {
        try {
            ResultSet resultSet = DatabaseManager.executeQuery("SELECT banned FROM users WHERE id='" + userId + "';");
            if(resultSet != null && resultSet.next()) {
                return resultSet.getBoolean("banned");
            }
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public static void setAccountMoney(String userId, double amount) {
        amount = (double) Math.round(amount * 100) / 100;
        DatabaseManager.executeUpdate("INSERT INTO user_money (id, account_money) VALUES ('" + userId + "', '" + amount + "') ON DUPLICATE KEY UPDATE account_money='" + amount + "';");
    }

    public static double getAccountMoney(String userId) {
        try {
            ResultSet resultSet = DatabaseManager.executeQuery("SELECT account_money FROM user_money WHERE id='" + userId + "';");
            if(resultSet != null && resultSet.next()) {
                double money = resultSet.getDouble("account_money");
                return (double) Math.round(money * 100) / 100;
            }
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static void setWalletMoney(String userId, double amount) {
        amount = (double) Math.round(amount * 100) / 100;
        DatabaseManager.executeUpdate("INSERT INTO user_money (id, wallet_money) VALUES ('" + userId + "', '" + amount + "') ON DUPLICATE KEY UPDATE wallet_money='" + amount + "';");
    }

    public static double getWalletMoney(String userId) {
        try {
            ResultSet resultSet = DatabaseManager.executeQuery("SELECT wallet_money FROM user_money WHERE id='" + userId + "';");
            if(resultSet != null && resultSet.next()) {
                double money = resultSet.getDouble("wallet_money");
                return (double) Math.round(money * 100) / 100;
            }
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static void setPartner(String userId, String partnerId) {
        DatabaseManager.executeUpdate("UPDATE user_relations SET partner_id='" + partnerId + "' WHERE id='" + userId + "';");
    }

    public static String getPartner(String userId) {
        try {
            ResultSet resultSet = DatabaseManager.executeQuery("SELECT partner_id FROM user_relations WHERE id='" + userId + "';");
            if(resultSet != null && resultSet.next()) {
                return resultSet.getString("partner_id");
            }
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static void removePartner(String userId) {
        DatabaseManager.executeUpdate("UPDATE user_relations SET partner_id=NULL WHERE id='" + userId + "';");
    }

    public static boolean hasPartner(String userId) {
        return getPartner(userId) != null;
    }

    @SuppressWarnings("all")
    public static boolean isPartnerWith(String userId, String partnerId) {
        return getPartner(userId).equals(partnerId);
    }

}
