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
 * Date: 26/04/2021
 * Time: 10:23
 * Project: Kazunya
 */
public class SmileCommand implements ICommand {

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
        images.add("https://media1.tenor.com/images/ba7c28c45c0123e95fbdf0854cbc7861/tenor.gif?itemid=12869746");
        images.add("https://media1.tenor.com/images/06a472766b5c90c0959a572eaaa6fb4b/tenor.gif?itemid=8073510");
        images.add("https://media1.tenor.com/images/c8d13a4636c548e962d8d4fdb0eaa169/tenor.gif?itemid=12217236");
        images.add("https://media1.tenor.com/images/cc66ae959cb51c118c782325fcdc4f3f/tenor.gif?itemid=9869247");
        images.add("https://media1.tenor.com/images/52ea7d449a5402030a3432fd3c94aa99/tenor.gif?itemid=13119051");
        images.add("https://media1.tenor.com/images/cf8a83dbdf57ae8b6bd15353d1c2bb86/tenor.gif?itemid=17477956");
        images.add("https://media1.tenor.com/images/55dde6c4f1eaca6b1e52626b980c0074/tenor.gif?itemid=13576447");
        images.add("https://media1.tenor.com/images/c5fad21f9828d19044a58f8b84a6e14b/tenor.gif?itemid=6013419");
        images.add("https://media1.tenor.com/images/325b3ba6a2beabe21c79b54c6de4e2c7/tenor.gif?itemid=15060821");
        images.add("https://media1.tenor.com/images/c49dc9422aac61eebbf8ae9d42bb26b7/tenor.gif?itemid=15792815");
        images.add("https://media1.tenor.com/images/64e0528a06b474ffb14525c437da2544/tenor.gif?itemid=11031890");
        images.add("https://media1.tenor.com/images/4e0a400d7621b5452854bcae00d9a98e/tenor.gif?itemid=5723668");
        images.add("https://media1.tenor.com/images/82b39c323ca376e9bb5844a54973fc42/tenor.gif?itemid=16596386");
        images.add("https://media1.tenor.com/images/6b353c18a4628d3d2346d031591296fa/tenor.gif?itemid=12803100");
        images.add("https://media1.tenor.com/images/0a157faece8b75e2cc7bc32e0a2a166b/tenor.gif?itemid=16094441");
        images.add("https://media1.tenor.com/images/ea548b6de4a49f3875d7f733a5676a94/tenor.gif?itemid=16993925");
        images.add("https://media1.tenor.com/images/7676a956e0dda07ec7f55d3eacbf353b/tenor.gif?itemid=16072068");
        images.add("https://media1.tenor.com/images/9411ce1ef75d43771bf0f305e7eb6487/tenor.gif?itemid=12793368");
        images.add("https://media1.tenor.com/images/aeb0ef81524736ebb6c1881398e076b7/tenor.gif?itemid=13946208");
        images.add("https://media1.tenor.com/images/a784d25bc90f5e81ac6615f2b165d2e6/tenor.gif?itemid=9097669");
        images.add("https://media1.tenor.com/images/895ea88ae5b22b4774ee5be0b9c17d01/tenor.gif?itemid=16414389");
        images.add("https://media1.tenor.com/images/3cb1bfa449cb8feb07a900353db5357e/tenor.gif?itemid=13576448");
        images.add("https://media1.tenor.com/images/148a2f4fbf904d6008ca9c7d71806859/tenor.gif?itemid=17383218");
        images.add("https://media1.tenor.com/images/d40f71dfc053af4995d48de258931f44/tenor.gif?itemid=7909470");
        images.add("https://media1.tenor.com/images/5b69fcd25e2467003e675cf32a232260/tenor.gif?itemid=8808104");
        images.add("https://media1.tenor.com/images/ecc8d5665f2698529d63b7c7c55fb5fc/tenor.gif?itemid=8674277");
        images.add("https://media1.tenor.com/images/dd11b8313236dba83c954c3f0cb0083a/tenor.gif?itemid=6202171");
        images.add("https://media1.tenor.com/images/a9c114df59d644d43e1da6f3e7db66ca/tenor.gif?itemid=4838961");

        embedBuilder.setDescription("**" + context.getMember().getEffectiveName() + "** is smiling :D Yay!");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));

        channel.sendMessage(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "smile";
    }

    @Override
    public String getHelp() {
        return "Make yourself smile and show your happiness.";
    }

    @Override
    public String getUsage() {
        return "smile";
    }

    @Override
    public boolean isNSFW() { return false; }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}
