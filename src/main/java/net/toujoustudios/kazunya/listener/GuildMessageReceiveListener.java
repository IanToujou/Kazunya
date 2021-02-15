package net.toujoustudios.kazunya.listener;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.toujoustudios.kazunya.command.CommandManager;
import net.toujoustudios.kazunya.config.Config;

import javax.annotation.Nonnull;

/**
 * This file was created by IanToujou.
 * Date: 02.02.2021
 * Time: 08:59
 * Project: Kazunya
 */
public class GuildMessageReceiveListener extends ListenerAdapter {

    private final CommandManager manager = new CommandManager();

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {

        User user = event.getAuthor();

        if(user.isBot() || event.isWebhookMessage()) return;

        String prefix = Config.DEFAULT_PREFIX;
        String raw = event.getMessage().getContentRaw();

        if(raw.toLowerCase().startsWith(prefix)) {

            manager.handle(event);

        }

    }

}
