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
 * Time: 01:59
 * Project: Kazunya
 */
public class HugCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {

        List<String> args = context.getArgs();
        TextChannel channel = context.getChannel();
        EmbedBuilder embedBuilder = new EmbedBuilder();
        Member member = context.getMember();

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
        images.add("https://media1.tenor.com/images/1069921ddcf38ff722125c8f65401c28/tenor.gif?itemid=11074788");
        images.add("https://media1.tenor.com/images/78d3f21a608a4ff0c8a09ec12ffe763d/tenor.gif?itemid=16509980");
        images.add("https://media1.tenor.com/images/1d94b18b89f600cbb420cce85558b493/tenor.gif?itemid=15942846");
        images.add("https://media1.tenor.com/images/e9d7da26f8b2adbb8aa99cfd48c58c3e/tenor.gif?itemid=14721541");
        images.add("https://media1.tenor.com/images/6db54c4d6dad5f1f2863d878cfb2d8df/tenor.gif?itemid=7324587");
        images.add("https://media1.tenor.com/images/94989f6312726739893d41231942bb1b/tenor.gif?itemid=14106856");
        images.add("https://media1.tenor.com/images/bb9c0c56769afa3b58b9efe5c7bcaafb/tenor.gif?itemid=16831471");
        images.add("https://media1.tenor.com/images/506aa95bbb0a71351bcaa753eaa2a45c/tenor.gif?itemid=7552075");
        images.add("https://media1.tenor.com/images/4d89d7f963b41a416ec8a55230dab31b/tenor.gif?itemid=5166500");
        images.add("https://media1.tenor.com/images/969f0f462e4b7350da543f0231ba94cb/tenor.gif?itemid=14246498");
        images.add("https://media1.tenor.com/images/4db088cfc73a5ee19968fda53be6b446/tenor.gif?itemid=14637016");
        images.add("https://media1.tenor.com/images/daffa3b7992a08767168614178cce7d6/tenor.gif?itemid=15249774");
        images.add("https://media1.tenor.com/images/e58eb2794ff1a12315665c28d5bc3f5e/tenor.gif?itemid=10195705");
        images.add("https://media1.tenor.com/images/5845f40e535e00e753c7931dd77e4896/tenor.gif?itemid=9920978");
        images.add("https://media1.tenor.com/images/b77fd0cfd95f89f967be0a5ebb3b6c6a/tenor.gif?itemid=7864716");
        images.add("https://media1.tenor.com/images/7e30687977c5db417e8424979c0dfa99/tenor.gif?itemid=10522729");
        images.add("https://media1.tenor.com/images/f5df55943b64922b6b16aa63d43243a6/tenor.gif?itemid=9375012");
        images.add("https://media1.tenor.com/images/18474dc6afa97cef50ad53cf84e37d08/tenor.gif?itemid=12375072");
        images.add("https://media1.tenor.com/images/c7efda563983124a76d319813155bd8e/tenor.gif?itemid=15900664");
        images.add("https://media1.tenor.com/images/ee3c3831a62667dc84ec4149a1651d8b/tenor.gif?itemid=14924015");
        images.add("https://media1.tenor.com/images/460c80d4423b0ba75ed9592b05599592/tenor.gif?itemid=5044460");
        images.add("https://media1.tenor.com/images/b62f047f8ed11b832cb6c0d8ec30687b/tenor.gif?itemid=12668480");
        images.add("https://media1.tenor.com/images/34a1d8c67e7b373de17bbfa5b8d35fc0/tenor.gif?itemid=8995974");
        images.add("https://media1.tenor.com/images/228cc8397577141822195070c88f6083/tenor.gif?itemid=4977890");
        images.add("https://media1.tenor.com/images/552c49f523d61c01da04bb1128b42cbf/tenor.gif?itemid=13747286");
        images.add("https://media1.tenor.com/images/42922e87b3ec288b11f59ba7f3cc6393/tenor.gif?itemid=5634630");
        images.add("https://media1.tenor.com/images/c1058ebe89313d50dfc878d38630036b/tenor.gif?itemid=13976210");
        images.add("https://media1.tenor.com/images/864d525effe7da722203e209efafafec/tenor.gif?itemid=12308503");
        images.add("https://media1.tenor.com/images/df8b87203442db2c2af2a806eb7153d4/tenor.gif?itemid=16300141");
        images.add("https://media1.tenor.com/images/d19bfd9ba90422611ec3c2d835363ffc/tenor.gif?itemid=18374323");
        images.add("https://media1.tenor.com/images/a2b938d651a8f6b89ed4c02f9f8c13ed/tenor.gif?itemid=12010176");
        images.add("https://media1.tenor.com/images/8f28a9d7097b811950b85ff6d561eb55/tenor.gif?itemid=18169149");
        images.add("https://media1.tenor.com/images/3ce31b15c2326831a8de9f0b693763ff/tenor.gif?itemid=16787485");
        images.add("https://media1.tenor.com/images/aeb42019b0409b98aed663f35b613828/tenor.gif?itemid=14108949");
        images.add("https://media1.tenor.com/images/bb841fad2c0e549c38d8ae15f4ef1209/tenor.gif?itemid=10307432");
        images.add("https://media1.tenor.com/images/44b4b9d5e6b4d806b6bcde2fd28a75ff/tenor.gif?itemid=9383138");
        images.add("https://media1.tenor.com/images/edea458dd2cbc76b17b7973a0c23685c/tenor.gif?itemid=13041472");
        images.add("https://media1.tenor.com/images/94c44a9898927f22dff399c2c248f433/tenor.gif?itemid=11247364");

        if(member == target) {

            embedBuilder.setDescription("Nya... Here, I will give you a hug! :purple_heart:");

        } else {

            embedBuilder.setDescription("**" + member.getEffectiveName() + "** is hugging **" + target.getEffectiveName() + "**! :purple_heart: Nya~");

        }

        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));
        channel.sendMessage(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "hug";
    }

    @Override
    public String getHelp() {
        return "Hug another user.";
    }

    @Override
    public String getUsage() {
        return "hug";
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}
