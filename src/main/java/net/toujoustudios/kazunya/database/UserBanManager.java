package net.toujoustudios.kazunya.database;

import net.toujoustudios.kazunya.model.UserBan;
import net.toujoustudios.kazunya.database.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class UserBanManager {

    public static void ban(String id, String reason, Date until, Date date) {
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        DatabaseManager.executeUpdate("INSERT INTO user_ban (id, reason, until, date) VALUES ('" + id + "', '" + reason + "', '" + sqlDate + "', '" + new java.sql.Date(date.getTime()) + "') ON DUPLICATE KEY UPDATE reason='" + reason + "', until='" + until + "', date='" + sqlDate + "';");
    }

    public static void ban(String id, String reason, Date until) {
        Date date = new Date();
        ban(id, reason, until, date);
    }

    public static void ban(String id, String reason) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        ban(id, reason, calendar.getTime(), date);
    }

    public static void ban(String id) {
        ban(id, "No Reason");
    }

    public static void unban(String id) {
        DatabaseManager.executeUpdate("DELETE FROM user_ban WHERE id='" + id + "';");
    }

    public static UserBan getBan(String id) {
        try {
            ResultSet resultSet = DatabaseManager.executeQuery("SELECT * FROM user_ban WHERE id='" + id + "';");
            if(resultSet != null && resultSet.next())
                return new UserBan(resultSet.getString("reason"), resultSet.getDate("until"), resultSet.getDate("date"));
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static boolean isBanned(String id) {
        return (getBan(id) != null);
    }

}
