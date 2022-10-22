package net.toujoustudios.kazunya.command.list.roleplay;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.data.relation.UserRelationType;
import net.toujoustudios.kazunya.data.user.UserManager;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;
import net.toujoustudios.kazunya.util.ColorUtil;

import java.util.ArrayList;
import java.util.List;

public class DivorceCommand implements ICommand {

    private final Config config;

    public DivorceCommand() {
        config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        Member member = context.getMember();
        List<OptionMapping> args = context.getArgs();
        EmbedBuilder embedBuilder = new EmbedBuilder();
        Member target = args.get(0).getAsMember();

        assert target != null;
        if(target.getId().equals(member.getId())) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_USER_SELF);
            return;
        }

        UserManager memberManager = UserManager.getUser(member);
        UserManager targetManager = UserManager.getUser(target);

        if(memberManager.getRelationsOfType(UserRelationType.MARRIED).size() < 1) {
            ErrorEmbed.sendError(context, ErrorType.ACTION_NOT_MARRIED);
            return;
        }

        if(memberManager.getRelation(target.getId()) == null) {
            ErrorEmbed.sendError(context, ErrorType.ACTION_NOT_PARTNERS);
            return;
        }

        if(memberManager.getRelation(target.getId()).getType() != UserRelationType.MARRIED) {
            ErrorEmbed.sendError(context, ErrorType.ACTION_NOT_MARRIED);
            return;
        }

        memberManager.removeRelationWith(target.getId());
        targetManager.removeRelationWith(member.getId());

        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
        embedBuilder.setTitle("**:broken_heart: Divorce**");
        embedBuilder.setDescription("You and " + target.getAsMention() + " are now divorced...");
        context.getEvent().reply(target.getAsMention()).addEmbeds(embedBuilder.build()).setEphemeral(true).queue();

    }

    @Override
    public String getName() {
        return "divorce";
    }

    @Override
    public String getDescription() {
        return "Divorce yourself from your partner.";
    }

    @Override
    public String getEmoji() {
        return "💔";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "user", "The person you want to divorce.", true));
        return optionData;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}