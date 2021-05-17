package net.toujoustudios.kazunya.command.list.roleplay;

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
 * Date: 12/04/2021
 * Time: 19:48
 * Project: Kazunya
 */
public class BlushCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {

        List<String> args = context.getArgs();
        TextChannel channel = context.getChannel();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setColor(Config.DEFAULT_EMBED_COLOR);
        embedBuilder.setTitle("**__ROLEPLAY__**");

        if(args.size() > 0) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The command syntax is not correct.\nPlease use **" + Config.DEFAULT_PREFIX + getUsage() + "**");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        ArrayList<String> images = new ArrayList<>();
        images.add("https://media1.tenor.com/images/c3190ef1d60989f745e95fb81559a52a/tenor.gif?itemid=12830507");
        images.add("https://media1.tenor.com/images/82b0f0a24e1621510b1216317edd4ba0/tenor.gif?itemid=14119517");
        images.add("https://media1.tenor.com/images/f72035e032125a5395883b8d68d9df5d/tenor.gif?itemid=16149781");
        images.add("https://media1.tenor.com/images/639036f3c2f138e87a679f7e121482bf/tenor.gif?itemid=15338269");
        images.add("https://media1.tenor.com/images/274fc34d3add3ce4cbb5716cb4f94f4f/tenor.gif?itemid=5841198");
        images.add("https://media1.tenor.com/images/5ea40ca0d6544dbf9c0074542810e149/tenor.gif?itemid=14841901");
        images.add("https://media1.tenor.com/images/794f8ff2b76326abe77171b3fb21252d/tenor.gif?itemid=11825225");
        images.add("https://media1.tenor.com/images/c118281cf06513a78e2fdc731db48c1b/tenor.gif?itemid=12942783");
        images.add("https://media1.tenor.com/images/8f76f034ccc458bd09675c0380f59cb7/tenor.gif?itemid=5634589");
        images.add("https://media1.tenor.com/images/9eba52d0506b552b7ef6a1981c0cfcff/tenor.gif?itemid=8680309");
        images.add("https://media1.tenor.com/images/d06d8c5273038c41f786bc75c53af470/tenor.gif?itemid=16255824");
        images.add("https://media1.tenor.com/images/cbfd2a06c6d350e19a0c173dec8dccde/tenor.gif?itemid=15727535");
        images.add("https://media1.tenor.com/images/1796f5f2469c4777cadf2a1ab57c6c0d/tenor.gif?itemid=9768769");
        images.add("https://media1.tenor.com/images/ad0fbaa8dd27994a8fbab2ab86122c7f/tenor.gif?itemid=13658383");
        images.add("https://media1.tenor.com/images/4c03573f06a1bd8841976abdafd16d26/tenor.gif?itemid=15711893");
        images.add("https://media1.tenor.com/images/9fa45bb3af562268984e7d7321f7ca9c/tenor.gif?itemid=17478014");
        images.add("https://media1.tenor.com/images/09d75740089598b54342df3641dbbffc/tenor.gif?itemid=5615361");
        images.add("https://media1.tenor.com/images/6069f7010fed86cb2105ea02e92129fe/tenor.gif?itemid=16142279");
        images.add("https://media1.tenor.com/images/29ab83ef501b53273cdb9489819225ff/tenor.gif?itemid=5522297");
        images.add("https://media1.tenor.com/images/1eb6c4585f9ad35ef69f07ec80c7ee17/tenor.gif?itemid=15974223");
        images.add("https://media1.tenor.com/images/99cc5a481355cb7c5a46d1506ea42ff2/tenor.gif?itemid=15812544");
        images.add("https://media1.tenor.com/images/9af8d8afab3b509a97f2440562845682/tenor.gif?itemid=13978385");
        images.add("https://media1.tenor.com/images/ce66f42bb8dbaf08f361d3c3bb0ff3ab/tenor.gif?itemid=11352124");
        images.add("https://media1.tenor.com/images/b2660fd9bbd1e58b49c8d3b0f44b1a13/tenor.gif?itemid=16890709");
        images.add("https://media1.tenor.com/images/4f270d2727e514056ae63f155ba0cef2/tenor.gif?itemid=13045709");
        images.add("https://media1.tenor.com/images/4fb97caeb1fceb2a2b24de3e9679381f/tenor.gif?itemid=19459906");
        images.add("https://media1.tenor.com/images/f62cae32b30d364bf0a8a1e7432c2ddf/tenor.gif?itemid=10198325");

        embedBuilder.setDescription("**" + context.getMember().getEffectiveName() + "** is blushing~ So cute >w<");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));

        channel.sendMessage(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "blush";
    }

    @Override
    public String getHelp() {
        return "Make yourself blush.";
    }

    @Override
    public String getUsage() {
        return "blush";
    }

    @Override
    public boolean isNSFW() { return false; }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}
