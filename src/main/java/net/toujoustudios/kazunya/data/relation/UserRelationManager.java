package net.toujoustudios.kazunya.data.relation;

import net.toujoustudios.kazunya.data.ban.UserBan;
import net.toujoustudios.kazunya.database.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class UserRelationManager {

    public static void setRelation(String id, String target, UserRelationType type, Date date) {
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        DatabaseManager.executeUpdate("INSERT INTO user_relations (id, target, type, date) VALUES ('" + id + "', '" + target + "', '" + type + "', '" + sqlDate + "') ON DUPLICATE KEY UPDATE target='" + target + "', type='" + type + "', date='" + sqlDate + "';");
    }

    public static void deleteRelation(String id, String target) {
        DatabaseManager.executeUpdate("DELETE FROM user_relations WHERE id='" + id + "' AND target='" + target + "';");
    }

    public static void deleteRelations(String id) {
        DatabaseManager.executeUpdate("DELETE FROM user_relations WHERE id='" + id + "';");
    }

    public static UserRelation getRelation(String id, String target) {
        try {
            ResultSet resultSet = DatabaseManager.executeQuery("SELECT * FROM user_relations WHERE id='" + id + "' AND target='" + target + "';");
            if(resultSet != null && resultSet.next())
                return new UserRelation(resultSet.getString("target"), UserRelationType.valueOf(resultSet.getString("type").toLowerCase()), resultSet.getDate("date"));
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static ArrayList<UserRelation> getRelations(String id) {
        ArrayList<UserRelation> relations = new ArrayList<>();
        try {
            ResultSet resultSet = DatabaseManager.executeQuery("SELECT * FROM user_relations WHERE id='" + id + "';");
            while(resultSet != null && resultSet.next())
                relations.add(new UserRelation(resultSet.getString("target"), UserRelationType.valueOf(resultSet.getString("type")), resultSet.getDate("date")));
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
        return relations;
    }

}
