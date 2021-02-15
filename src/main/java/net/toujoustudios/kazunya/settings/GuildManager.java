package net.toujoustudios.kazunya.settings;

import net.dv8tion.jda.api.entities.Guild;

import java.util.HashMap;
import java.util.Map;

/**
 * This file was created by IanToujou.
 * Date: 08.02.2021
 * Time: 01:42
 * Project: Kazunya
 */
@SuppressWarnings("all")
public class GuildManager {

    private static final HashMap<Guild, GuildManager> guilds = new HashMap<Guild, GuildManager>();

    private Guild guild;
    private String id = guild.getId();
    private boolean restorePlayerRoles;

    public GuildManager(Guild guild) {

        this.guild = guild;
        restorePlayerRoles = false;

    }

    public static void unloadAll() {

        for(Map.Entry<Guild, GuildManager> entry : guilds.entrySet()) {
            guilds.get(entry.getKey()).unload();
        }

    }

    public void unload() {

        save();
        destroy();

    }

    public void save() {

        //GuildDataManager.setRestorePlayerRoles(id, restorePlayerRoles);

    }

    public void destroy() {

        guilds.remove(guild);

    }

    public static HashMap<Guild, GuildManager> getGuilds() {
        return guilds;
    }

}
