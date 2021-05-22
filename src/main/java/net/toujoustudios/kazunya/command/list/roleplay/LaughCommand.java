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
 * Date: 22/05/2021
 * Time: 19:15
 * Project: Kazunya
 */
public class LaughCommand implements ICommand {

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
        images.add("https://media1.tenor.com/images/7e2bfe3c042e226a0f371f669fc55e47/tenor.gif?itemid=14132771");
        images.add("https://media1.tenor.com/images/6d7008706bd15d1ed1dc47387f02f853/tenor.gif?itemid=10665609");
        images.add("https://media1.tenor.com/images/ad4804e880c2edcecbb79217b479610a/tenor.gif?itemid=10903422");
        images.add("https://media1.tenor.com/images/182f3e1d1fc398d0ea99c9433a4f0c65/tenor.gif?itemid=12130183");
        images.add("https://media1.tenor.com/images/615dc6190b6438d911f366944a917ede/tenor.gif?itemid=9388677");
        images.add("https://media1.tenor.com/images/c468ca0162b2757b45a751870e753c64/tenor.gif?itemid=8453319");
        images.add("https://media1.tenor.com/images/26df2182fc943676dc6cc51371efc04b/tenor.gif?itemid=8932912");
        images.add("https://media1.tenor.com/images/ccf51fb7683192a9b909d7c8116cc6da/tenor.gif?itemid=11115623");
        images.add("https://media1.tenor.com/images/cec591b860ddf5447dba64008127309b/tenor.gif?itemid=12048187");
        images.add("https://media1.tenor.com/images/a5ae7acff7f4ae61536a409fd581c14b/tenor.gif?itemid=8539543");
        images.add("https://media1.tenor.com/images/2775948586d6a24811726ce4dc681d47/tenor.gif?itemid=13786657");
        images.add("https://media1.tenor.com/images/fb80a2dd4fdb86c6eeee94125f23c161/tenor.gif?itemid=5060962");
        images.add("https://media1.tenor.com/images/3be8aa0228169cf5748e21eb972ffa1d/tenor.gif?itemid=12252557");
        images.add("https://media1.tenor.com/images/60752436c762fd710643cffec01f6cbd/tenor.gif?itemid=9051310");
        images.add("https://media1.tenor.com/images/44ea3c92369e173dcfe3da0ebe954b3c/tenor.gif?itemid=7374735");
        images.add("https://media1.tenor.com/images/09d453fdea8349671b36c06746afd080/tenor.gif?itemid=12200646");
        images.add("https://media1.tenor.com/images/1b4a4d70324db4ebb5849dd67d1d6414/tenor.gif?itemid=9955720");
        images.add("https://media1.tenor.com/images/de9deca0d39e158c3d13d42f511d8013/tenor.gif?itemid=14088527");

        embedBuilder.setDescription("**" + context.getMember().getEffectiveName() + "** is laughing :D");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));

        channel.sendMessage(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "laugh";
    }

    @Override
    public String getHelp() {
        return "Make yourself laugh.";
    }

    @Override
    public String getUsage() {
        return "laugh";
    }

    @Override
    public boolean isNSFW() {
        return false;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}
