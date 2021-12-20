package net.toujoustudios.kazunya.command.list.roleplay;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.toujoustudios.kazunya.color.ColorTools;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;
import net.toujoustudios.kazunya.user.UserManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 29/08/2021
 * Time: 13:19
 */
public class MarryCommand implements ICommand {

    private final Config config;
    private final HashMap<Member, Member> requests = new HashMap<>();

    public MarryCommand() {
        config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        Member member = context.getMember();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        if (args.size() < 1) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_SYNTAX);
            return;
        }

        Member target = args.get(0).getAsMember();
        assert target != null;

        if (target.getUser().isBot()) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_USER_BOT);
            return;
        }

        if (target.getId().equals(member.getId())) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_USER_SELF);
            return;
        }

        UserManager memberManager = UserManager.getUser(member.getId());
        UserManager targetManager = UserManager.getUser(target.getId());

        if (memberManager.hasPartner()) {
            ErrorEmbed.sendError(context, ErrorType.ACTION_ALREADY_MARRIED_SELF);
            return;
        }

        if (targetManager.hasPartner()) {
            ErrorEmbed.sendError(context, ErrorType.ACTION_ALREADY_MARRIED_TARGET);
            return;
        }

        requests.putIfAbsent(member, target);

        if (requests.containsKey(target)) {

            if (requests.get(target) == member) {

                requests.remove(target);
                requests.remove(member);

                memberManager.setPartner(target.getId());
                targetManager.setPartner(member.getId());
                embedBuilder.setColor(ColorTools.getFromRGBString(config.getString("format.color.default")));
                embedBuilder.setTitle(":ring: **Marriage**");
                embedBuilder.setDescription(member.getAsMention() + " and " + target.getAsMention() + " are now happily married!");
                context.getEvent().replyEmbeds(embedBuilder.build()).queue();

            }

        } else {

            String message = "Do you want to marry me?";
            if (args.size() > 1) {
                message = args.get(1).getAsString();
            }

            embedBuilder.setColor(ColorTools.getFromRGBString(config.getString("format.color.default")));
            embedBuilder.setTitle(":ring: **Proposal**");
            embedBuilder.setDescription(member.getAsMention() + " wants to marry you, " + target.getAsMention() + "!\n" + member.getEffectiveName() + ": `" + message + "`");
            embedBuilder.addField(":white_check_mark: Accept:", "To accept the proposal, please type `/marry @" + member.getEffectiveName() + "`", false);
            embedBuilder.addField(":octagonal_sign: Decline:", "To decline the proposal, simply ignore it. But that would be sad.", false);
            context.getEvent().replyEmbeds(embedBuilder.build()).queue();

        }

    }

    @Override
    public String getName() {
        return "marry";
    }

    @Override
    public String getDescription() {
        return "Ask someone to be your wife or husband.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "user", "The person you want to marry.", true));
        optionData.add(new OptionData(OptionType.STRING, "message", "The proposal message."));
        return optionData;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}
