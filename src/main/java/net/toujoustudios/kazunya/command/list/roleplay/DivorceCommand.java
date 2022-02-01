package net.toujoustudios.kazunya.command.list.roleplay;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.toujoustudios.kazunya.util.ColorUtil;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;
import net.toujoustudios.kazunya.main.Main;
import net.toujoustudios.kazunya.user.UserManager;
import net.toujoustudios.kazunya.util.ColorUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DivorceCommand implements ICommand {

    private final ArrayList<String> list = new ArrayList<>();
    private final Config config;

    public DivorceCommand() {
        config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        Member member = context.getMember();
        List<OptionMapping> args = context.getArgs();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        if (args.size() > 1) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_SYNTAX);
            return;
        }

        UserManager memberManager = UserManager.getUser(member.getId());

        if (!memberManager.hasPartner()) {
            ErrorEmbed.sendError(context, ErrorType.ACTION_NO_PARTNER);
            return;
        }

        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));

        if (list.contains(member.getId())) {

            User target = Main.getBot().getJDA().getUserById(memberManager.getPartner());
            embedBuilder.setTitle("**:broken_heart: Divorce**");
            if (target != null) {
                embedBuilder.setDescription("You and `" + target.getName() + "` are now divorced.");
            } else {
                embedBuilder.setDescription("You and `your partner` are now divorced.");
            }
            context.getEvent().replyEmbeds(embedBuilder.build()).setEphemeral(true).queue();
            return;

        }


        embedBuilder.setTitle("**:broken_heart: Divorce: Confirm**");
        embedBuilder.setDescription("Do you really want to divorce?\nPlease type `/divorce` again to confirm this action.");
        list.add(member.getId());
        context.getEvent().replyEmbeds(embedBuilder.build()).setEphemeral(true).queue();

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
    public List<OptionData> getOptions() {
        return Collections.emptyList();
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}