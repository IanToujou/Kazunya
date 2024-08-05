package net.toujoustudios.kazunya.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class GuildManager {

    @Getter
    private static final HashMap<String, GuildManager> guilds = new HashMap<>();

    private String id;

    public GuildManager(String id) {
        this.id = id;
    }

    /**
     * Returns an existing or a new guild manager for any guild, if the ID is specified.
     *
     * @param id The discord ID of the guild you want to get the guild manager from.
     * @return The user manager of the given user.
     * @since 1.0.0
     */
    public static GuildManager getGuild(String id) {
        if(guilds.containsKey(id)) return guilds.get(id);
        GuildManager guildManager = new GuildManager(id);
        guilds.put(id, guildManager);
        return guildManager;
    }

    public static void saveAll() {
        for(Map.Entry<String, GuildManager> entry : guilds.entrySet()) {
            guilds.get(entry.getKey()).save();
        }
    }

    public static void unloadAll() {
        saveAll();
        guilds.clear();
    }

    public void save() {}

}
