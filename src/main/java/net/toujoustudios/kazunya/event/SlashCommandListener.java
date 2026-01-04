package net.toujoustudios.kazunya.event;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;
import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;
import net.toujoustudios.kazunya.main.Main;
import org.jetbrains.annotations.NotNull;

public class SlashCommandListener extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        if(event.getGuild() == null || event.getUser().isBot() || event.getMember() == null) {
            ErrorEmbed.sendError(event, ErrorType.GENERAL_UNKNOWN);
            return;
        }

        Main.getBot().commandManager().handle(event);
        Logger.log(LogLevel.SILENT, event.getUser().getName() + " executed the following command: " + event.getCommandString());

    }

}
