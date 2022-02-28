package net.toujoustudios.kazunya.listener;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;
import net.toujoustudios.kazunya.main.Main;
import net.toujoustudios.kazunya.user.UserManager;
import org.jetbrains.annotations.NotNull;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 26/08/2021
 * Time: 23:06
 */
public class SlashCommandListener extends ListenerAdapter {

    @Override
    public void onSlashCommand(@NotNull SlashCommandEvent event) {

        if (event.getGuild() == null) return;
        if (event.getUser().isBot()) return;

        UserManager userManager = UserManager.getUser(event.getUser().getId());
        if (userManager.isUsageBanned()) {
            ErrorEmbed.sendError(event, ErrorType.GENERAL_BANNED);
            return;
        }

        Main.getBot().getCommandManager().handle(event);

    }

}
