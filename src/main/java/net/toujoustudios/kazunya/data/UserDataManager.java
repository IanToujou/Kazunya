package net.toujoustudios.kazunya.data;

import net.toujoustudios.kazunya.database.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 26/08/2021
 * Time: 23:34
 */
public class UserDataManager {

    public static void setAccountMoney(String userId, double amount) {

        amount = (double) Math.round(amount * 100) / 100;
        DatabaseManager.executeUpdate("INSERT INTO user_data (user_id, account_money) VALUES ('" + userId + "', '" + amount + "') ON DUPLICATE KEY UPDATE account_money='" + amount + "';");

    }

    public static double getAccountMoney(String userId) {

        try {

            ResultSet resultSet = DatabaseManager.executeQuery("SELECT account_money FROM user_data WHERE user_id='" + userId + "';");

            if (resultSet != null && resultSet.next()) {
                double money = resultSet.getDouble("account_money");
                return (double) Math.round(money * 100) / 100;
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return 0;

    }

    public static void setPartner(String userId, String partnerId) {
        DatabaseManager.executeUpdate("UPDATE user_data SET partner_id='" + partnerId + "' WHERE user_id='" + userId + "';");
    }

    public static String getPartner(String userId) {

        try {

            ResultSet resultSet = DatabaseManager.executeQuery("SELECT partner_id FROM user_data WHERE user_id='" + userId + "';");

            if (resultSet != null && resultSet.next()) {
                return resultSet.getString("partner_id");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;

    }

    public static void removePartner(String userId) {
        DatabaseManager.executeUpdate("UPDATE user_data SET partner_id=NULL WHERE user_id='" + userId + "';");
    }

    public static boolean hasPartner(String userId) {
        return getPartner(userId) != null;
    }

    @SuppressWarnings("all")
    public static boolean isPartnerWith(String userId, String partnerId) {
        return getPartner(userId).equals(partnerId);
    }

}
