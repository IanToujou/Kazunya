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
 * Date: 11/04/2021
 * Time: 16:52
 * Project: Kazunya
 */
public class PurrCommand implements ICommand {

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
        images.add("https://media1.tenor.com/images/64d45ee51ea8d55760c81a93353ffdb3/tenor.gif?itemid=11179299");
        images.add("https://media1.tenor.com/images/0d84880342a1ac0c40f1f67caaceeee7/tenor.gif?itemid=15594779");
        images.add("https://media1.tenor.com/images/34d6e388391099e69c7967374b9b5706/tenor.gif?itemid=8774325");
        images.add("https://media1.tenor.com/images/b5397d5803883e7d4a4b355d068a7375/tenor.gif?itemid=14223397");
        images.add("https://media1.tenor.com/images/37a585471f5e895489a03ae705430218/tenor.gif?itemid=12142150");
        images.add("https://media1.tenor.com/images/ef110fa5bd49c7989669252d641b87ef/tenor.gif?itemid=10200682");
        images.add("https://media1.tenor.com/images/43e7cad8a8b0158d5dfb9a3af53213fe/tenor.gif?itemid=14547864");
        images.add("https://media1.tenor.com/images/c5e0a7c1f911ebe75e08f191b90a339a/tenor.gif?itemid=12089018");
        images.add("https://media1.tenor.com/images/b7b6ed8715dc9af4a449556fde6f1b12/tenor.gif?itemid=16331489");
        images.add("https://media1.tenor.com/images/750615ac2f0197dd6ba65fe3dd23810c/tenor.gif?itemid=17334913");
        images.add("https://media1.tenor.com/images/f9dd27c36db3c8e06f65f2ec36da5d99/tenor.gif?itemid=8294802");

        embedBuilder.setDescription("**" + context.getMember().getEffectiveName() + "** is purring~ Nya Nya~");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));

        channel.sendMessage(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "purr";
    }

    @Override
    public String getHelp() {
        return "Make yourself purr.";
    }

    @Override
    public String getUsage() {
        return "purr";
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}
