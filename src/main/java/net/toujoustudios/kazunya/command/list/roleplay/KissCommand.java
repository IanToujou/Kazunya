package net.toujoustudios.kazunya.command.list.roleplay;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This file was created by IanToujou.
 * Date: 11/04/2021
 * Time: 02:12
 * Project: Kazunya
 */
public class KissCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {

        List<String> args = context.getArgs();
        TextChannel channel = context.getChannel();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setColor(Config.DEFAULT_EMBED_COLOR);

        if(args.size() == 0) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The command syntax is not correct.\nPlease use **" + Config.DEFAULT_PREFIX + getUsage() + "**");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        if(context.getMessage().getMentionedMembers().size() != 1) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The command syntax is not correct.\nPlease use **" + Config.DEFAULT_PREFIX + getUsage() + "**");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        Member target = context.getMessage().getMentionedMembers().get(0);

        ArrayList<String> images = new ArrayList<>();
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");

        embedBuilder.setDescription("**" + context.getMember().getEffectiveName() + "** is kissing **" + target.getEffectiveName() + "**! :purple_heart: Nya~");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));

        channel.sendMessage(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "kiss";
    }

    @Override
    public String getHelp() {
        return "Kiss another user.";
    }

    @Override
    public String getUsage() {
        return "kiss";
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}
