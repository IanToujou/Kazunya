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
 * Time: 22:51
 * Project: Kazunya
 * WARNING: THIS FILE CONTAINS NSFW MATERIAL (LINKS, IMAGES, ETC.)
 */
public class FuckCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {

        List<String> args = context.getArgs();
        TextChannel channel = context.getChannel();
        EmbedBuilder embedBuilder = new EmbedBuilder();
        Member member = context.getMember();

        embedBuilder.setColor(Config.DEFAULT_EMBED_COLOR);

        if(!channel.isNSFW()) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: You can only use this command in **NSFW** channels.");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

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
        images.add("https://img.rule34.xxx//images/3529/17a48932b647d0389f1a972ef16f3155.gif?3984981");
        images.add("https://wimg.rule34.xxx//images/4036/641fb57bfbca715f8651dbe514466145.gif?4579111");
        images.add("https://wimg.rule34.xxx//images/4036/5ba4723d74fb25df5153108859892f4f.gif?4579057");
        images.add("https://wimg.rule34.xxx//images/4022/1015b2c336c4dde479b2be9eb07f9ab4.gif?4564914");
        images.add("https://img.rule34.xxx//images/4022/102669b29d3759a50f2e52b65ea9c991.gif?4564930");
        images.add("https://wimg.rule34.xxx//images/3918/7033640885226ab02465048171301087.gif?4439966");
        images.add("https://wimg.rule34.xxx//images/3875/95601b118df57b34205ee9a1b5a69dcc.gif?4387832");
        images.add("https://img.rule34.xxx//images/3872/72f0c160da37d13003e3e806efdec08c.gif?4383529");
        images.add("https://wimg.rule34.xxx//images/3860/80fb17d555d4611b606ceed4efdd9d4b.gif?4370018");
        images.add("https://wimg.rule34.xxx//images/3860/c7fc3098e2883f9821d39820927818e6.gif?4369988");
        images.add("https://wimg.rule34.xxx//images/3858/bcb37527daa4719723e33ac28ff03fef.gif?4367632");
        images.add("https://img.rule34.xxx//images/3838/fcbe59663984549e4be534d60bc98921.gif?4345553");
        images.add("https://wimg.rule34.xxx//images/3835/76d95b595a5dce90d0cd1c776db544e1a62e7028.gif?4342424");
        images.add("https://wimg.rule34.xxx//images/3835/84f7893a2c269396b87056eb9ee6199cc99c9f22.gif?4342422");
        images.add("https://wimg.rule34.xxx//images/3689/3f578258dc13086b6f70997fbc1a661a.gif?4173542");

        embedBuilder.setDescription("**" + member.getEffectiveName() + "** is fucking **" + target.getEffectiveName() + "**! :sweat_drops:");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));
        channel.sendMessage(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "fuck";
    }

    @Override
    public String getHelp() {
        return "Fuck another user.";
    }

    @Override
    public String getUsage() {
        return "fuck [user]";
    }

    @Override
    public boolean isNSFW() { return true; }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}
