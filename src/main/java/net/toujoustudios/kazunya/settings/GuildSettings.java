package net.toujoustudios.kazunya.settings;

import net.dv8tion.jda.api.entities.Guild;

import java.util.HashMap;

/**
 * This file was created by IanToujou.
 * Date: 08.02.2021
 * Time: 01:42
 * Project: Kazunya
 */
public class GuildSettings {

    private static final HashMap<Guild, GuildSettings> guilds = new HashMap<Guild, GuildSettings>();

    private Guild guild;
    private boolean restorePlayerRoles;

    public GuildSettings(Guild guild) {

        this.guild = guild;
        restorePlayerRoles = false;

    }

    public static HashMap<Guild, GuildSettings> getGuilds() {
        return guilds;
    }

}
