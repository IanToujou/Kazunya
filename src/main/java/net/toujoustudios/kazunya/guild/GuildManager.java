package net.toujoustudios.kazunya.guild;

import net.toujoustudios.kazunya.data.GuildDataManager;
import net.toujoustudios.kazunya.database.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This file was created by IanToujou.
 * Date: 27.02.2021
 * Time: 15:43
 * Project: Kazunya
 */
public class GuildManager {

    private static final HashMap<String, GuildManager> guilds = new HashMap<String, GuildManager>();

    private String guildId;
    private boolean restoreRoles;
    private ArrayList<String> excludedRestoreRoles;

    public GuildManager(String guildId) {

        this.guildId = guildId;
        restoreRoles = GuildDataManager.getRestoreRoles(guildId);
        excludedRestoreRoles = GuildDataManager.getExcludedRestoreRoles(guildId);

    }

    public boolean isRestoreRoles() {
        return restoreRoles;
    }

    public void setRestoreRoles(boolean restoreRoles) {

        this.restoreRoles = restoreRoles;

    }

    public ArrayList<String> getExcludedRestoreRoles() {
        return excludedRestoreRoles;
    }

    public void setExcludedRestoreRoles(ArrayList<String> excludedRestoreRoles) {
        this.excludedRestoreRoles = excludedRestoreRoles;
    }

    public static GuildManager getGuild(String guildId) {

        if(guilds.containsKey(guildId)) return guilds.get(guildId);
        GuildManager guildManager = new GuildManager(guildId);
        guilds.put(guildId, guildManager);
        return guildManager;

    }

    public static void unloadAll() {

        for(Map.Entry<String, GuildManager> entry : guilds.entrySet()) {
            guilds.get(entry.getKey()).unload();
        }

    }

    public void unload() {

        save();
        destroy();

    }

    public void save() {

        try {

            ResultSet resultSet = DatabaseManager.executeQuery("SELECT * FROM guild_settings WHERE guild_id='" + guildId + "';");

            if(!resultSet.next()) {

                DatabaseManager.executeUpdate("INSERT INTO guild_settings (guild_id) VALUES ('" + guildId + "');");

            }

        } catch(SQLException exception) {

            exception.printStackTrace();

        }

        GuildDataManager.setRestoreRoles(guildId, restoreRoles);
        GuildDataManager.setExcludedRestoreRoles(guildId, excludedRestoreRoles);

    }

    public void destroy() {

        guilds.remove(guildId);

    }

    public static HashMap<String, GuildManager> getGuilds() {
        return guilds;
    }

}
