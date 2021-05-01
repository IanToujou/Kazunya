package net.toujoustudios.kazunya.command.list.fun;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;

import java.util.Collections;
import java.util.List;

/**
 * This file was created by IanToujou.
 * Date: 27/04/2021
 * Time: 00:35
 * Project: Kazunya
 */
public class ScoreboardCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {

        List<String> args = context.getArgs();
        TextChannel channel = context.getChannel();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setColor(Config.DEFAULT_EMBED_COLOR);
        embedBuilder.setTitle("**__SCOREBOARD__**");

        if(args.size() != 1) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The command syntax is not correct.\nPlease use **" + Config.DEFAULT_PREFIX + getUsage() + "**");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        if(args.get(0).equalsIgnoreCase("ship")) {


            for(Member all : context.getGuild().getMembers()) {

                String id = all.getId();

            }

        } else {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The specified ranking is not correct. Available rankings:\n`ship` - Displays the top ship rates");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        channel.sendMessage(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "scoreboard";
    }

    @Override
    public String getHelp() {
        return "Displays a scoreboard with various rankings.";
    }

    @Override
    public String getUsage() {
        return "scoreboard [ranking]";
    }

    @Override
    public boolean isNSFW() {
        return false;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.FUN;
    }

    @Override
    public List<String> getAliases() {
        return Collections.singletonList("sb");
    }

}
