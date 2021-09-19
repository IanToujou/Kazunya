package net.toujoustudios.kazunya.data;

import net.toujoustudios.kazunya.database.DatabaseManager;
import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 26/08/2021
 * Time: 23:34
 */
public class UserDataManager {

    public static void setMoney(String userId, double amount) {

        amount = (double) Math.round(amount * 100) / 100;
        DatabaseManager.executeUpdate("INSERT INTO user_data (user_id, money) VALUES ('" + userId + "', '" + amount + "') ON DUPLICATE KEY UPDATE money='" + amount + "';");

    }

    public static double getMoney(String userId) {

        try {

            ResultSet resultSet = DatabaseManager.executeQuery("SELECT * FROM user_data WHERE user_id='" + userId + "';");

            while (resultSet.next()) {

                double money = resultSet.getDouble("money");
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

            ResultSet resultSet = DatabaseManager.executeQuery("SELECT * FROM user_data WHERE user_id='" + userId + "';");

            while (resultSet.next()) {

                String out = resultSet.getString("partner_id");
                return out;

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

    public static boolean isPartnerWith(String userId, String partnerId) {
        return getPartner(userId).equals(partnerId);
    }

}
