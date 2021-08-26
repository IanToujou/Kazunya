package net.toujoustudios.kazunya.guild;

import java.util.HashMap;
import java.util.Map;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 26/08/2021
 * Time: 23:39
 */
public class GuildManager {

    private static final HashMap<String, GuildManager> guilds = new HashMap<>();

    private final String guildId;

    public GuildManager(String guildId) {

        this.guildId = guildId;

    }

    public static GuildManager getGuild(String guildId) {

        if (guilds.containsKey(guildId)) return guilds.get(guildId);
        GuildManager guildManager = new GuildManager(guildId);
        guilds.put(guildId, guildManager);
        return guildManager;

    }

    public static void unloadAll() {
        for (Map.Entry<String, GuildManager> entry : guilds.entrySet()) {
            guilds.get(entry.getKey()).unload();
        }
    }

    public static HashMap<String, GuildManager> getGuilds() {
        return guilds;
    }

    public void unload() {
        save();
        destroy();
    }

    public void save() {}

    public void destroy() {
        guilds.remove(guildId);
    }

}
