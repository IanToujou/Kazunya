package net.toujoustudios.kazunya.command.list.unlisted;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;

import java.util.List;

/**
 * This file was created by IanToujou.
 * Date: 06.02.2021
 * Time: 00:20
 * Project: Kazunya
 */
public class ExecuteCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {

        List<String> args = context.getArgs();
        TextChannel channel = context.getChannel();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        if(context.getMember().getId().equals("369381378703228928")) {

            //Executable Code
            embedBuilder.setTitle("**__NOTIFICATION SETTINGS__**");
            embedBuilder.setDescription("*Note: Please react with the roles listed below.*\n\n:youtube: `YouTube Uploads`");
            embedBuilder.setColor(Config.DEFAULT_EMBED_COLOR);

            context.getChannel().sendMessage(embedBuilder.build()).queue();

        }

    }

    @Override
    public String getName() {
        return "execute";
    }

    @Override
    public String getHelp() { return "Execute a code."; }

    @Override
    public String getUsage() { return "ship [user] [user]"; }

    @Override
    public CommandCategory getCategory() { return CommandCategory.GENERAL; }

}
