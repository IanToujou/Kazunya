package net.toujoustudios.kazunya.listener;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.toujoustudios.kazunya.config.Config;

import javax.annotation.Nonnull;

/**
 * This file was created by IanToujou.
 * Date: 05.02.2021
 * Time: 12:40
 * Project: Kazunya
 */
public class GuildMemberJoinListener extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {

        User user = event.getMember().getUser();

    }

}
