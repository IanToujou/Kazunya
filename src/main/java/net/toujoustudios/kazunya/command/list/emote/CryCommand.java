package net.toujoustudios.kazunya.command.list.emote;

import net.dv8tion.jda.api.EmbedBuilder;
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

        ArrayList<String> images = new ArrayList<>();
        images.add("https://media1.tenor.com/images/ce52606293142a2bd11cda1d3f0dc12c/tenor.gif?itemid=5184314");
        images.add("https://media1.tenor.com/images/4f22255d60f3f19edf9296992b4e3483/tenor.gif?itemid=4772697");
        images.add("https://media1.tenor.com/images/8f6da405119d24f7f86ff036d02c2fd4/tenor.gif?itemid=5378935");
        images.add("https://media1.tenor.com/images/031c7c348d3b86296976e2407723d4a8/tenor.gif?itemid=5014031");
        images.add("https://media1.tenor.com/images/7ef999b077acd6ebef92afc34690097e/tenor.gif?itemid=10893043");
        images.add("https://media.tenor.com/images/d7286a667f4c7f5e3af2daf2ac3d9948/tenor.gif");
        images.add("https://media1.tenor.com/images/f5ec64b40d2adf7deb84e3c0e192ff32/tenor.gif?itemid=6194053");
        images.add("https://media1.tenor.com/images/4b5e9867209d7b1712607958e01a80f1/tenor.gif?itemid=5298257");
        images.add("https://media1.tenor.com/images/2fb2965acbf3ed573e8b63080b947fe5/tenor.gif?itemid=5091716");
        images.add("https://media.tenor.com/images/05325358f7b7d2a8201063d588220518/tenor.gif");
        images.add("https://media.tenor.com/images/19089cd2b4970740debff2cdfc43329a/tenor.gif");
        images.add("https://media.tenor.com/images/30ded343472db298bd66b8df87d9853b/tenor.gif");
        images.add("https://media.tenor.com/images/c9fdb6e3ab914d5b195b11ebd99524d0/tenor.gif");
        images.add("https://media.tenor.com/images/cb1e1460334fdf4af0d439db80aa3ec6/tenor.gif");
        images.add("https://media.tenor.com/images/7cba9943d6449423bd9da78929bf6337/tenor.gif");
        images.add("https://media.tenor.com/images/3368a542ef94e3ecc0821585afa96a8a/tenor.gif");
        images.add("https://media1.tenor.com/images/67df1dca3260e0032f40048759a967a5/tenor.gif?itemid=5415917");
        images.add("https://media.tenor.com/images/eac8a7b4a0e7c468a31274b80c3958a1/tenor.gif");
        images.add("https://media.tenor.com/images/70478bb418236d9e2e852372b61570c6/tenor.gif");
        images.add("https://media1.tenor.com/images/81d53b1482f85320524c29ca0f7e9259/tenor.gif?itemid=17100832");
        images.add("https://media1.tenor.com/images/f4efbb0911cb0d6d3e8b1d1d4bdb83e1/tenor.gif?itemid=8934978");
        images.add("https://media1.tenor.com/images/7618310e4332bd3303acb414348e475c/tenor.gif?itemid=5755226");
        images.add("https://media.tenor.com/images/82c7523a9b203fc4e938d2d027540dea/tenor.gif");
        images.add("https://media.tenor.com/images/c1cd1857a8ac9981a297f763f52faf87/tenor.gif");
        images.add("https://media.tenor.com/images/a874f133b7168056c38a632a883b0656/tenor.gif");

        embedBuilder.setDescription("**" + context.getMember().getEffectiveName() + "** is crying... Nya...");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));

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
