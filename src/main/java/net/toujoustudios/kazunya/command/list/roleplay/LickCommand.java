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
 * Date: 15/05/2021
 * Time: 18:46
 * Project: Kazunya
 */
public class LickCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {

        List<String> args = context.getArgs();
        TextChannel channel = context.getChannel();
        EmbedBuilder embedBuilder = new EmbedBuilder();
        Member member = context.getMember();

        embedBuilder.setColor(Config.DEFAULT_EMBED_COLOR);
        embedBuilder.setTitle("**__ROLEPLAY__**");

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
        images.add("https://media1.tenor.com/images/5f73f2a7b302a3800b3613095f8a5c40/tenor.gif?itemid=10005495");
        images.add("https://media1.tenor.com/images/5c5828e51733c8ffe1c368f1395a03d0/tenor.gif?itemid=14231351");
        images.add("https://media1.tenor.com/images/c4e7f19ec6bc342c2e7d69caec637783/tenor.gif?itemid=16854581");
        images.add("https://media1.tenor.com/images/6b701503b0e5ea725b0b3fdf6824d390/tenor.gif?itemid=12141727");
        images.add("https://media1.tenor.com/images/9643577662a9946de17bd8c3fd124c72/tenor.gif?itemid=16422435");
        images.add("https://media1.tenor.com/images/21c8ff8307eb88bf8bf8438e4c78382b/tenor.gif?itemid=16943447");
        images.add("https://media1.tenor.com/images/dbc120cf518319ffe2aedf635ad2df93/tenor.gif?itemid=16600144");
        images.add("https://media1.tenor.com/images/b08b6d61bcf16bee7d56408f61855f35/tenor.gif?itemid=17549074");
        images.add("https://media1.tenor.com/images/feeef4685f9307b76c78a22ba0a69f48/tenor.gif?itemid=8413059");
        images.add("https://media1.tenor.com/images/1925e468ff1ac9efc2100a3d092c54ff/tenor.gif?itemid=4718221");
        images.add("https://media1.tenor.com/images/ec2ca0bf12d7b1a30fea702b59e5a7fa/tenor.gif?itemid=13417195");
        images.add("https://media1.tenor.com/images/25239414375d304c38880d02941fffe3/tenor.gif?itemid=16930771");
        images.add("https://media1.tenor.com/images/3d8bc3a931049b21e1bb19ecd4543787/tenor.gif?itemid=17779187");

        embedBuilder.setDescription("**" + member.getEffectiveName() + "** is lickling **" + target.getEffectiveName() + "**! :sweat_drops: Lewd!");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));
        channel.sendMessage(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "lick";
    }

    @Override
    public String getHelp() {
        return "Lick another user.";
    }

    @Override
    public String getUsage() {
        return "lick [@user]";
    }

    @Override
    public boolean isNSFW() { return false; }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}
