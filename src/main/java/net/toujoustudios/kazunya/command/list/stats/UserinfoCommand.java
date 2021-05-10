package net.toujoustudios.kazunya.command.list.stats;

import com.jagrosh.jdautilities.commons.utils.FinderUtil;
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
import java.util.stream.Collectors;

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

        String joined = String.join("", args);
        List<User> foundUsers = FinderUtil.findUsers(joined, context.getJDA());

        if(foundUsers.isEmpty()) {

            List<Member> foundMembers = FinderUtil.findMembers(joined, context.getGuild());

            if(foundMembers.isEmpty()) {

                embedBuilder.setTitle("**__ERROR__**");
                embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
                embedBuilder.setDescription(":x: The given user is invalid.");
                channel.sendMessage(embedBuilder.build()).queue();
                return;

            }

            foundUsers = foundMembers.stream().map(Member::getUser).collect(Collectors.toList());

        }

        User user = foundUsers.get(0);
        Member target = context.getGuild().getMember(user);

        if(target == null) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The given user is invalid.");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        embedBuilder.setThumbnail(user.getEffectiveAvatarUrl().replaceFirst("gif", "png"));
        embedBuilder.addField(":information_source: General Information:", "**Username:** " + user.getAsTag() + "\n**Display Name:** " + target.getEffectiveName() + "\n**User ID:** " + user.getId() + "\n**Bot Account:** " + (user.isBot() ? "Yes" : "No"), false);
        embedBuilder.addField(":calendar: Dates:", "**Account Created:** " + user.getTimeCreated().format(DateTimeFormatter.RFC_1123_DATE_TIME) + "\n**Server Joined:** " + target.getTimeJoined().format(DateTimeFormatter.RFC_1123_DATE_TIME), false);

        UserManager userManager = UserManager.getUser(user.getId());

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
