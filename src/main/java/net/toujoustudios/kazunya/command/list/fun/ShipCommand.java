package net.toujoustudios.kazunya.command.list.fun;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;

import java.awt.*;
import java.util.List;
import java.util.Random;

/**
 * This file was created by IanToujou.
 * Date: 04.02.2021
 * Time: 03:05
 * Project: Kazunya
 */
public class ShipCommand implements ICommand {

    @Override
    @SuppressWarnings("all")
    public void handle(CommandContext context) {

        List<String> args = context.getArgs();
        TextChannel channel = context.getChannel();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setColor(Config.DEFAULT_EMBED_COLOR);
        embedBuilder.setTitle("**__SHIP RATING__**");

        if(args.size() == 0) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The command syntax is not correct.\nPlease use **" + Config.DEFAULT_PREFIX + getUsage() + "**");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        if(context.getMessage().getMentionedMembers().size() != 2) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The command syntax is not correct.\nPlease use **" + Config.DEFAULT_PREFIX + getUsage() + "**");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        Member first = context.getMessage().getMentionedMembers().get(0);
        Member second = context.getMessage().getMentionedMembers().get(1);

        int rating = 0;
        String firstId = first.getId();
        String secondId = second.getId();
        int num1 = Integer.parseInt(firstId.substring(firstId.length() - 2));
        int num2 = Integer.parseInt(secondId.substring(firstId.length() - 2));
        rating = num1 + num2;
        if(rating > 100) rating -= 100;

        String firstName = first.getEffectiveName();
        String secondName = second.getEffectiveName();
        firstName = firstName.substring(0, firstName.length() / 2);
        secondName = secondName.substring(secondName.length() / 2);

        String shipName = firstName + secondName.toLowerCase();

        int avatarNumber = new Random().nextInt(2);

        embedBuilder.setThumbnail(context.getMessage().getMentionedMembers().get(avatarNumber).getUser().getAvatarUrl());

        String commentary = "";

        if(rating < 10) {

            commentary = "The relationship would be truly horrible...";
            embedBuilder.setColor(Color.RED);

        } else if(rating < 30) {

            commentary = "This wouldn't work that great...";
            embedBuilder.setColor(Color.ORANGE);

        } else if(rating < 50) {

            commentary = "It is fine.";
            embedBuilder.setColor(Color.YELLOW);

        } else if(rating == 69) {

            commentary = "69? Nice.";
            embedBuilder.setColor(Color.GREEN);

        } else if(rating < 70) {

            commentary = "This could work nya~";
            embedBuilder.setColor(Color.GREEN);

        } else if(rating < 90) {

            commentary = "This would be a wholesome relationship! Nya~";
            embedBuilder.setColor(Color.PINK);

        } else if(rating < 100) {

            commentary = "The two should instantly get married! Nyaaa~";
            embedBuilder.setColor(Color.PINK);

        } else {

            commentary = "I WANT THEM TO GET MARRIED! NOW!";
            embedBuilder.setColor(Color.PINK);

        }

        embedBuilder.setDescription(commentary + "\n\n:chart_with_upwards_trend: Ship Rating: **" + rating + "%**\n:heart: Ship Name: **" + shipName + "**");
        channel.sendMessage(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "ship";
    }

    @Override
    public String getHelp() { return "Ship two users and get a compatibility score."; }

    @Override
    public String getUsage() { return "ship [@user] [@user]"; }

    @Override
    public boolean isNSFW() { return false; }

    @Override
    public CommandCategory getCategory() { return CommandCategory.FUN; }

}
