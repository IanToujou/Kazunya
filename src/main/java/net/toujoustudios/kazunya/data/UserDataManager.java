package net.toujoustudios.kazunya.data;

import net.toujoustudios.kazunya.database.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDataManager {

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
