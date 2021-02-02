package net.toujoustudios.kazunya.data;

import net.toujoustudios.kazunya.database.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This file was created by IanToujou.
 * Date: 02.02.2021
 * Time: 08:36
 * Project: Kazunya
 */
public class DataManager {

    public static void setMoney(String userId, int amount) {

        DatabaseManager.executeUpdate("INSERT INTO user_information (user_id, money) VALUES ('" + userId + "', '" + amount + "') ON DUPLICATE KEY UPDATE money='" + amount + "';");

    }

    public static int getMoney(String userId) {

        try {

            ResultSet resultSet = DatabaseManager.executeQuery("SELECT * FROM user_information WHERE user_id='" + userId + "';");

            while(resultSet.next()) {

                return resultSet.getInt("money");

            }

        } catch(SQLException exception) {

            exception.printStackTrace();

        }

        return 0;

    }

    public static void setPartner(String userId, String partnerId) {

        DatabaseManager.executeUpdate("INSERT INTO user_information (user_id, partner_id) VALUES ('" + userId + "', '" + partnerId + "') ON DUPLICATE KEY UPDATE partner_id='" + partnerId + "';");

    }

    public static String getPartner(String userId) {

        try {

            ResultSet resultSet = DatabaseManager.executeQuery("SELECT * FROM user_information WHERE user_id='" + userId + "';");

            while(resultSet.next()) {

                return resultSet.getString("partner_id");

            }

        } catch(SQLException exception) {

            exception.printStackTrace();

        }

        return null;

    }

    public static void removePartner(String userId) {

        DatabaseManager.executeUpdate("UPDATE user_information SET partner_id=NULL WHERE user_id='" + userId + "';");

    }

    public static boolean hasPartner(String userId) {

        return getPartner(userId) != null;

    }

    public static boolean isPartnerWith(String userId, String partnerId) {

        return getPartner(userId).equals(partnerId);

    }

}
