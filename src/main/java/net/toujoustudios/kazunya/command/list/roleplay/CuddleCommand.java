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
 * Time: 21:10
 * Project: Kazunya
 */
public class CuddleCommand implements ICommand {

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
        images.add("https://media1.tenor.com/images/c51d8a4505e1dfef709efd4739d09faa/tenor.gif?itemid=5754133");
        images.add("https://media1.tenor.com/images/9c055cf13ecd8255834775c0af48f2c3/tenor.gif?itemid=16361738");
        images.add("https://media1.tenor.com/images/7edded2757934756fdc240019d956cb3/tenor.gif?itemid=16403937");
        images.add("https://media1.tenor.com/images/8cbe0edadc12ca1056d5eb685a4c27f6/tenor.gif?itemid=14518537");
        images.add("https://media1.tenor.com/images/c445e2665d12cfda0921291d919cbe9a/tenor.gif?itemid=15069987");
        images.add("https://media1.tenor.com/images/9af57b60dca6860724a0ff6c1689c246/tenor.gif?itemid=8467962");
        images.add("https://media1.tenor.com/images/1a65319302b9e1c86a99a39e9a81084e/tenor.gif?itemid=3553099");
        images.add("https://media1.tenor.com/images/a829b33d49f61a042728c06347bddd57/tenor.gif?itemid=5166505");
        images.add("https://media1.tenor.com/images/bfacd94f66bdde64009f420277464e67/tenor.gif?itemid=15069983");
        images.add("https://media1.tenor.com/images/012cc6d6cb65c3c98bd5505ab2e1c42a/tenor.gif?itemid=13317505");
        images.add("https://media1.tenor.com/images/b0ee5dcaad65487caf02906b3409b699/tenor.gif?itemid=7454269");
        images.add("https://media1.tenor.com/images/8f8ba3baeecdf28f3e0fa7d4ce1a8586/tenor.gif?itemid=12668750");
        images.add("https://media1.tenor.com/images/d0c2e7382742f1faf8fcb44db268615f/tenor.gif?itemid=5853736");
        images.add("https://media1.tenor.com/images/d16a9affe8915e6413b0c1f1d380b2ee/tenor.gif?itemid=12669052");
        images.add("https://media1.tenor.com/images/5e007281145725639ae2e182a7f734d8/tenor.gif?itemid=11742921");
        images.add("https://media1.tenor.com/images/45895aa6fcd2f5b8a49993aa8167eb98/tenor.gif?itemid=18833698");
        images.add("https://media1.tenor.com/images/8042046da3a26c4d3cedb5ce06cdeb14/tenor.gif?itemid=4901574");
        images.add("https://media1.tenor.com/images/3d62384321435408f50823ae6f5ca033/tenor.gif?itemid=12270770");

        embedBuilder.setDescription("**" + member.getEffectiveName() + "** is cuddling **" + target.getEffectiveName() + "**! :purple_heart: Nya~");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));
        channel.sendMessage(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "cuddle";
    }

    @Override
    public String getHelp() {
        return "Cuddle another user.";
    }

    @Override
    public String getUsage() {
        return "cuddle [user]";
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}
