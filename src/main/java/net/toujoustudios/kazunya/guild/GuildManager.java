package net.toujoustudios.kazunya.guild;

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

    private final String guildId;

    public GuildManager(String guildId) {

        this.guildId = guildId;

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


    }

    public void destroy() {

        guilds.remove(guildId);

    }

    public static HashMap<String, GuildManager> getGuilds() {
        return guilds;
    }

}
