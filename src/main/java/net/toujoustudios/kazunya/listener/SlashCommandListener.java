package net.toujoustudios.kazunya.listener;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.toujoustudios.kazunya.data.user.UserManager;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;
import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;
import net.toujoustudios.kazunya.main.Main;
import org.jetbrains.annotations.NotNull;

public class SlashCommandListener extends ListenerAdapter {

    @Override
    public void onSlashCommand(@NotNull SlashCommandEvent event) {

        if(event.getGuild() == null) {
            ErrorEmbed.sendError(event, ErrorType.GENERAL_UNKNOWN);
            return;
        }
        if(event.getUser().isBot()) {
            ErrorEmbed.sendError(event, ErrorType.GENERAL_UNKNOWN);
            return;
        }
        if(event.getMember() == null) {
            ErrorEmbed.sendError(event, ErrorType.GENERAL_UNKNOWN);
            return;
        }

        UserManager userManager = UserManager.getUser(event.getMember());

        if(userManager.isBanned()) {
            ErrorEmbed.sendError(event, ErrorType.GENERAL_BANNED);
            return;
        }

        Main.getBot().getCommandManager().handle(event);
        Logger.log(LogLevel.SILENT, event.getUser().getName() + "#" + event.getUser().getDiscriminator() + " executed the following command: " + event.getCommandString());

    }

}
