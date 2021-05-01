package net.toujoustudios.kazunya.command.list.roleplay;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.user.UserManager;

import java.util.List;

/**
 * This file was created by IanToujou.
 * Date: 01/05/2021
 * Time: 21:55
 * Project: Kazunya
 */
public class DivorceCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {

        EmbedBuilder embedBuilder = new EmbedBuilder();
        TextChannel channel = context.getChannel();
        List<String> args = context.getArgs();
        Member member = context.getMember();
        UserManager memberManager = UserManager.getUser(member.getId());

        embedBuilder.setTitle("**__DIVORCE__**");
        embedBuilder.setColor(Config.DEFAULT_EMBED_COLOR);

        if(args.size() > 1) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The command syntax is not correct.\nPlease use **" + Config.DEFAULT_PREFIX + getUsage() + "**");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        if(!memberManager.hasPartner()) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: You don't have any partner.");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        if(args.size() == 0) {

            embedBuilder.setDescription("Are you really sure you want to divorce?\nPlease confirm it with **" + Config.DEFAULT_PREFIX + "divorce confirm**");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        if(args.get(0).equalsIgnoreCase("confirm")) {

            String partnerId = memberManager.getPartner();
            String partnerName = "`Invalid`";

            UserManager targetManager = UserManager.getUser(partnerId);

            if(context.getJDA().getUserById(partnerId) != null) {

                partnerName = "`" + context.getJDA().getUserById(partnerId).getName() + "`";

            }

            if(context.getGuild().getMemberById(partnerId) != null) {

                partnerName = context.getGuild().getMemberById(partnerId).getAsMention();

            }

            embedBuilder.setDescription(":broken_heart: You have successfully divorced " + partnerName + "! ;w;");
            channel.sendMessage(embedBuilder.build()).queue();

            targetManager.removePartner();
            memberManager.removePartner();

        } else {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The command syntax is not correct.\nPlease use **" + Config.DEFAULT_PREFIX + getUsage() + "**");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

    }

    @Override
    public String getName() { return "divorce"; }

    @Override
    public String getHelp() { return "Make bad things happen and lose your partner."; }

    @Override
    public String getUsage() { return "divorce"; }

    @Override
    public boolean isNSFW() {
        return false;
    }

    @Override
    public CommandCategory getCategory() { return CommandCategory.ROLEPLAY; }

}
