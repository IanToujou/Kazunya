package net.toujoustudios.kazunya.data.skill;

import net.toujoustudios.kazunya.database.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserSkillManager {

    public static void setSkill(String id, UserSkill skill) {
        DatabaseManager.executeUpdate("INSERT INTO user_skills (id, skill, xp) VALUES ('" + id + "', '" + skill.getSkillType().toString() + "', '" + skill.getExperience() + "') ON DUPLICATE KEY UPDATE skill='" + skill.getSkillType().toString() + "', xp='" + skill.getExperience() + "';");
    }

    public static void deleteSkill(String id, SkillType skill) {
        DatabaseManager.executeUpdate("DELETE FROM user_skills WHERE id='" + id + "' AND skill='" + skill.toString() + "';");
    }

    public static void deleteSkills(String id) {
        DatabaseManager.executeUpdate("DELETE FROM user_skills WHERE id='" + id + "';");
    }

    public static ArrayList<UserSkill> getSkills(String id) {
        ArrayList<UserSkill> skills = new ArrayList<>();
        try {
            ResultSet resultSet = DatabaseManager.executeQuery("SELECT * FROM user_skills WHERE id='" + id + "';");
            while(resultSet != null && resultSet.next())
                skills.add(new UserSkill(SkillType.valueOf(resultSet.getString("skill")), resultSet.getInt("experience")));
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
        return skills;
    }

    public static void setSkills(String id, ArrayList<UserSkill> skills) {
        skills.forEach(all -> {
            setSkill(id, all);
        });
    }

}
