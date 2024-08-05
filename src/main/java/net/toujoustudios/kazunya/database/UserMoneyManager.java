package net.toujoustudios.kazunya.database;

import net.toujoustudios.kazunya.database.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMoneyManager {

    public static void setBankMoney(String userId, double amount) {
        amount = (double) Math.round(amount * 100) / 100;
        DatabaseManager.executeUpdate("INSERT INTO user_money (id, bank) VALUES ('" + userId + "', '" + amount + "') ON DUPLICATE KEY UPDATE bank='" + amount + "';");
    }

    public static double getBankMoney(String userId) {
        try {
            ResultSet resultSet = DatabaseManager.executeQuery("SELECT bank FROM user_money WHERE id='" + userId + "';");
            if(resultSet != null && resultSet.next()) {
                double money = resultSet.getDouble("bank");
                return (double) Math.round(money * 100) / 100;
            }
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static void setWalletMoney(String userId, double amount) {
        amount = (double) Math.round(amount * 100) / 100;
        DatabaseManager.executeUpdate("INSERT INTO user_money (id, wallet) VALUES ('" + userId + "', '" + amount + "') ON DUPLICATE KEY UPDATE wallet='" + amount + "';");
    }

    public static double getWalletMoney(String userId) {
        try {
            ResultSet resultSet = DatabaseManager.executeQuery("SELECT wallet FROM user_money WHERE id='" + userId + "';");
            if(resultSet != null && resultSet.next()) {
                double money = resultSet.getDouble("wallet");
                return (double) Math.round(money * 100) / 100;
            }
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
        return 0;
    }

}
