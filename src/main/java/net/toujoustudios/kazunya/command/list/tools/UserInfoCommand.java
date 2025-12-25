package net.toujoustudios.kazunya.command.list.tools;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.util.ColorUtil;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * A command to get basic information from Discord about a specific user.
 *
 * @author Ian Toujou
 * @version 1.2.0
 * @since 1.0.0
 */
public class UserInfoCommand implements ICommand {

    private final Config config;

    public UserInfoCommand() {
        config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        Member target = args.getFirst().getAsMember();
        if(target == null) return;
        User targetUser = target.getUser();

        embedBuilder.setTitle("**:zap: User Information**");
        embedBuilder.setThumbnail(targetUser.getEffectiveAvatarUrl());

        String generalBuilder = "Username: `" + targetUser.getName() + "`\n" +
                "Display Name: `" + target.getEffectiveName() + "`\n" +
                "User ID: `" + target.getId() + "`\n" +
                "Bot Account: `" + (targetUser.isBot() ? "Yes" : "No") + "`";

        String datesBuilder = "Account Created: `" + targetUser.getTimeCreated().format(DateTimeFormatter.RFC_1123_DATE_TIME) + "`\n" +
                "Server Joined: `" + target.getTimeJoined().format(DateTimeFormatter.RFC_1123_DATE_TIME) + "`";

        embedBuilder.addField(":information_source: General Information:", generalBuilder, false);
        embedBuilder.addField(":calendar: Dates:", datesBuilder, false);

        embedBuilder.setColor(ColorUtil.rgb(config.getString("format.color.default")));
        context.getInteraction().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String name() {
        return "userinfo";
    }

    @Override
    public String description() {
        return "Displays general information about a discord user.";
    }

    @Override
    public String emoji() {
        return "👤";
    }

    @Override
    public List<OptionData> options() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "user", "The user you want to get information on.", true));
        return optionData;
    }

    @Override
    public CommandCategory category() {
        return CommandCategory.TOOLS;
    }
}
