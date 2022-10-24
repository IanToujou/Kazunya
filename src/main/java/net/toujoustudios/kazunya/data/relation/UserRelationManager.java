package net.toujoustudios.kazunya.data.relation;

import net.toujoustudios.kazunya.database.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class UserRelationManager {

    public static void setRelation(String id, String member, String target, UserRelationType type, Date date) {
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        DatabaseManager.executeUpdate("INSERT INTO user_relations (id, member, target, type, date) VALUES ('" + id + "', '" + member + "', '" + target + "', '" + type + "', '" + sqlDate + "') ON DUPLICATE KEY UPDATE id='" + id + "', member='" + member + "', target='" + target + "', type='" + type + "', date='" + sqlDate + "';");
    }

    public static void addRelation(String member, String target, UserRelationType type, Date date) {
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        DatabaseManager.executeUpdate("INSERT INTO user_relations (id, member, target, type, date) VALUES ('" + UUID.randomUUID() + "', '" + member + "', '" + target + "', '" + type + "', '" + sqlDate + "');");
    }

    public static void deleteRelation(String member, String target) {
        DatabaseManager.executeUpdate("DELETE FROM user_relations WHERE member='" + member + "' AND target='" + target + "';");
    }

    public static void deleteRelations(String member) {
        DatabaseManager.executeUpdate("DELETE FROM user_relations WHERE member='" + member + "';");
    }

    public static UserRelation getRelation(String member, String target) {
        try {
            ResultSet resultSet = DatabaseManager.executeQuery("SELECT * FROM user_relations WHERE member='" + member + "' AND target='" + target + "';");
            if(resultSet != null && resultSet.next())
                return new UserRelation(resultSet.getString("id"), resultSet.getString("target"), UserRelationType.valueOf(resultSet.getString("type").toLowerCase()), resultSet.getDate("date"));
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static ArrayList<UserRelation> getRelations(String member) {
        ArrayList<UserRelation> relations = new ArrayList<>();
        try {
            ResultSet resultSet = DatabaseManager.executeQuery("SELECT * FROM user_relations WHERE member='" + member + "';");
            while(resultSet != null && resultSet.next())
                relations.add(new UserRelation(resultSet.getString("id"), resultSet.getString("target"), UserRelationType.valueOf(resultSet.getString("type")), resultSet.getDate("date")));
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
        return relations;
    }

}
