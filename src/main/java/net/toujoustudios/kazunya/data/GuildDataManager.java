package net.toujoustudios.kazunya.data;

import net.toujoustudios.kazunya.database.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This file was created by IanToujou.
 * Date: 27.02.2021
 * Time: 15:43
 * Project: Kazunya
 */
public class GuildDataManager {

    public static boolean getRestoreRoles(String id) {

        ResultSet resultSet = DatabaseManager.executeQuery("SELECT * FROM guild_settings WHERE guild_id='" + id + "';");

        try {

            while(resultSet.next()) {

                return resultSet.getBoolean("restore_roles");

            }

        } catch(SQLException exception) {
            exception.printStackTrace();
        }

        return false;

    }

    public static void setRestoreRoles(String id, boolean restoreRoles) {

        int boolInt = restoreRoles ? 1 : 0;
        DatabaseManager.executeUpdate("UPDATE guild_settings SET restore_roles='" + boolInt + "' WHERE guild_id='" + id + "';");

    }

    public static ArrayList<String> getExcludedRestoreRoles(String id) {

        ResultSet resultSet = DatabaseManager.executeQuery("SELECT * FROM guild_settings WHERE guild_id='" + id + "';");

        ArrayList<String> list = new ArrayList<>();

        try {

            while(resultSet.next()) {

                String result = resultSet.getString("excluded_restore_roles");
                String[] strings = result.split(",");

                list.addAll(Arrays.asList(strings));

            }

        } catch(SQLException exception) {
            exception.printStackTrace();
        }

        return list;

    }

    public static void setExcludedRestoreRoles(String id, ArrayList<String> excludedRestoreRoles) {

        StringBuilder builder = new StringBuilder();

        for(String role : excludedRestoreRoles) {

            builder.append(role).append(",");

        }

        String roleString = builder.toString();

        DatabaseManager.executeUpdate("UPDATE guild_settings SET excluded_restore_roles='" + roleString + "' WHERE guild_id='" + id + "';");

    }

}