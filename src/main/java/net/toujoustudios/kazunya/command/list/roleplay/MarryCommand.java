package net.toujoustudios.kazunya.command.list.roleplay;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.user.UserManager;

import java.util.HashMap;
import java.util.List;

/**
 * This file was created by IanToujou.
 * Date: 01/05/2021
 * Time: 03:27
 * Project: Kazunya
 */
public class MarryCommand implements ICommand {

    private final HashMap<Member, Member> requests = new HashMap<>();

    @Override
    public void handle(CommandContext context) {

        EmbedBuilder embedBuilder = new EmbedBuilder();
        TextChannel channel = context.getChannel();
        Member member = context.getMember();
        List<String> args = context.getArgs();

        embedBuilder.setTitle("**__MARRY__**");
        embedBuilder.setColor(Config.DEFAULT_EMBED_COLOR);

        if(args.size() < 1) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The command syntax is not correct.\nPlease use **" + Config.DEFAULT_PREFIX + getUsage() + "**");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        if(context.getMessage().getMentionedMembers().size() < 1) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The command syntax is not correct.\nPlease use **" + Config.DEFAULT_PREFIX + getUsage() + "**");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        Member target = context.getMessage().getMentionedMembers().get(0);

        if(target.getUser().isBot()) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: You cannot marry a bot. I know, we are superior to humans, but whatever...");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        if(target == member) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: You cannot marry yourself. Why would you?! (ಠ_ಠ)");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        UserManager targetManager = UserManager.getUser(target.getId());
        UserManager memberManager = UserManager.getUser(member.getId());

        if(memberManager.hasPartner()) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: You are already married. Don't be illoyal! ( •̀ω•́ )");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        if(targetManager.hasPartner()) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The given user is already married. Don't try to do anything stupid!");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        requests.putIfAbsent(member, target);

        if(requests.containsKey(target)) {

            if(requests.get(target) == member) {

                requests.remove(target);
                requests.remove(member);

                memberManager.setPartner(target.getId());
                targetManager.setPartner(member.getId());
                embedBuilder.setDescription(":ring: " + member.getAsMention() + " and " + target.getAsMention() + " are now happily married! :purple_heart:\n⊂(・w・)⊃");
                channel.sendMessage(embedBuilder.build()).queue();

                return;

            }

        }

        embedBuilder.setDescription(":heart: " + member.getAsMention() + " is proposing to " + target.getAsMention() + ".");
        embedBuilder.addField(":white_check_mark: Accept:", "To accept the proposal, use " + Config.DEFAULT_PREFIX + "marry " + member.getAsMention() + " :3", false);
        embedBuilder.addField(":octagonal_sign: Decline:", "To decline the proposal, just ignore it. But that would be sad (•́ ‸ •̀ )", false);

        channel.sendMessage(embedBuilder.build()).queue();

    }

    @Override
    public String getName() { return "marry"; }

    @Override
    public String getHelp() { return "Marry someone on the server."; }

    @Override
    public String getUsage() { return "marry [@user]"; }

    @Override
    public boolean isNSFW() { return false; }

    @Override
    public CommandCategory getCategory() { return CommandCategory.ROLEPLAY; }

}
