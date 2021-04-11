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
 * Time: 17:01
 * Project: Kazunya
 */
public class KillCommand implements ICommand {

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
        images.add("https://media1.tenor.com/images/fbcd2c524059569f19e7a192f04893b5/tenor.gif?itemid=18284505");
        images.add("https://media1.tenor.com/images/d1adbb82cb428da4c2de5f158ef2caba/tenor.gif?itemid=17870588");
        images.add("https://media1.tenor.com/images/cbb1642c9aeb06b4055a9ce5bbdc908a/tenor.gif?itemid=5749160");
        images.add("https://media1.tenor.com/images/2487a7679b3d7d23cadcd51381635467/tenor.gif?itemid=11451829");

        String killer = "**" + context.getMember().getEffectiveName() + "**";
        String victim = "**" + target.getEffectiveName() + "**";

        ArrayList<String> deathMessages = new ArrayList<>();
        deathMessages.add("{Killer} used the death note on {Victim}.");
        deathMessages.add("{Victim} starved to death.");
        deathMessages.add("{Killer} detonated an ICBM on {Victim}'s head.");
        deathMessages.add("{Killer} punched {Victim} to death.");
        deathMessages.add("{Victim} died due to overdosed cuddling.");

        embedBuilder.setDescription(deathMessages.get(new Random().nextInt(deathMessages.size())).replace("{Killer}", killer).replace("{Victim}", victim) + " :skull:");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));

        channel.sendMessage(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "kill";
    }

    @Override
    public String getHelp() {
        return "Kill another user.";
    }

    @Override
    public String getUsage() {
        return "kill [user]";
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}
