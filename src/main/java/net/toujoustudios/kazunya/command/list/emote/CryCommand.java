package net.toujoustudios.kazunya.command.list.emote;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;

import java.util.List;

/**
 * This file was created by IanToujou.
 * Date: 08.02.2021
 * Time: 03:37
 * Project: Kazunya
 */
public class CryCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {

        List<String> args = context.getArgs();
        TextChannel channel = context.getChannel();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setColor(Config.DEFAULT_EMBED_COLOR);

        if(args.size() > 0) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The command syntax is not correct.\nPlease use **" + Config.DEFAULT_PREFIX + getUsage() + "**");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        embedBuilder.setDescription("**" + context.getMember().getEffectiveName() + "** is crying... Nya...");
        embedBuilder.setImage("https://tenor.com/view/anime-umaru-cry-crying-tears-gif-5184314");

        channel.sendMessage(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "cry";
    }

    @Override
    public String getHelp() {
        return "Make yourself cry.";
    }

    @Override
    public String getUsage() {
        return "cry";
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.EMOTE;
    }

}
