package net.toujoustudios.kazunya.command.list.stats;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.user.UserManager;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * This file was created by IanToujou.
 * Date: 10/05/2021
 * Time: 22:44
 * Project: Kazunya
 */
public class UserinfoCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {

        List<String> args = context.getArgs();
        TextChannel channel = context.getChannel();
        EmbedBuilder embedBuilder = new EmbedBuilder();
        Member member = context.getMember();

        embedBuilder.setColor(Config.DEFAULT_EMBED_COLOR);
        embedBuilder.setTitle("**__USER INFORMATION__**");

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
        User targetUser = target.getUser();

        embedBuilder.setThumbnail(targetUser.getEffectiveAvatarUrl());
        embedBuilder.addField(":information_source: General Information:", "**Username:** " + targetUser.getAsTag() + "\n**Display Name:** " + target.getEffectiveName() + "\n**User ID:** " + targetUser.getId() + "\n**Bot Account:** " + (targetUser.isBot() ? "Yes" : "No"), false);
        embedBuilder.addField(":calendar: Dates:", "**Account Created:** " + targetUser.getTimeCreated().format(DateTimeFormatter.RFC_1123_DATE_TIME) + "\n**Server Joined:** " + target.getTimeJoined().format(DateTimeFormatter.RFC_1123_DATE_TIME), false);

        UserManager userManager = UserManager.getUser(targetUser.getId());

        String partner = "None";
        if(userManager.hasPartner()) {

            String partnerId = userManager.getPartner();

            if(context.getGuild().getMemberById(partnerId) != null) {

                partner = context.getGuild().getMemberById(partnerId).getAsMention();

            } else {

                if(context.getJDA().getUserById(partnerId) != null) {

                    partner = "`" + context.getJDA().getUserById(partnerId).getName() + "`";

                } else {

                    partner = "`Invalid`";

                }

            }

        }

        embedBuilder.addField(":link: Relations:", "**Partner:** " + partner, false);

        channel.sendMessage(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "userinfo";
    }

    @Override
    public String getHelp() {
        return "Displays general information about a discord user.";
    }

    @Override
    public String getUsage() {
        return "userinfo [@user]";
    }

    @Override
    public boolean isNSFW() {
        return false;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.STATS;
    }

}
