package net.toujoustudios.kazunya.data.ban;

import net.toujoustudios.kazunya.database.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class DataUserBanManager {

    public static void ban(String id, String reason, Date until, Date date) {
        DatabaseManager.executeUpdate("INSERT INTO user_bans (id, reason, until, date) VALUES ('" + id + "', '" + reason + "', '" + until + "', '" + date + "') ON DUPLICATE KEY UPDATE reason='" + reason + "', until='" + until + "', date='" + date + "';");
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
        DatabaseManager.executeUpdate("DELETE FROM user_bans WHERE id='" + id + "';");
    }

    public static UserBan getBan(String id) {
        try {
            ResultSet resultSet = DatabaseManager.executeQuery("SELECT * FROM user_bans WHERE id='" + id + "';");
            if(resultSet != null && resultSet.next())
                return new UserBan(resultSet.getString("reason"), resultSet.getDate("until"), resultSet.getDate("date"));
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

}
