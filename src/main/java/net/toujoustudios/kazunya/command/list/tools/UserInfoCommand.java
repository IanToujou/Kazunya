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
import net.toujoustudios.kazunya.user.UserManager;
import net.toujoustudios.kazunya.util.ColorUtil;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 27/10/2021
 * Time: 23:56
 */
public class UserInfoCommand implements ICommand {

    private final Config config;

    public UserInfoCommand() {
        config = Config.getDefault();
    }

    @Override
    @SuppressWarnings("all")
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        Member target = args.get(0).getAsMember();
        if (target == null) return;
        User targetUser = target.getUser();
        UserManager targetManager = UserManager.getUser(targetUser.getId());

        embedBuilder.setTitle("**:zap: User Information**");
        embedBuilder.setThumbnail(targetUser.getEffectiveAvatarUrl());

        StringBuilder generalBuilder = new StringBuilder();
        generalBuilder.append("Username: `" + targetUser.getAsTag() + "`\n");
        generalBuilder.append("Display Name: `" + target.getEffectiveName() + "`\n");
        generalBuilder.append("User ID: `" + target.getId() + "`\n");
        generalBuilder.append("Bot Account: `" + (targetUser.isBot() ? "Yes" : "No") + "`");

        StringBuilder datesBuilder = new StringBuilder();
        datesBuilder.append("Account Created: `" + targetUser.getTimeCreated().format(DateTimeFormatter.RFC_1123_DATE_TIME) + "`\n");
        datesBuilder.append("Server Joined: `" + target.getTimeJoined().format(DateTimeFormatter.RFC_1123_DATE_TIME) + "`");

        embedBuilder.addField(":information_source: General Information:", generalBuilder.toString(), false);
        embedBuilder.addField(":calendar: Dates:", datesBuilder.toString(), false);

        StringBuilder relationshipBuilder = new StringBuilder();
        
        String partner = "`None`";
        if (targetManager.hasPartner()) {

            String partnerId = targetManager.getPartner();

            if (context.getGuild().getMemberById(partnerId) != null) {
                partner = context.getGuild().getMemberById(partnerId).getAsMention();
            } else {
                if (context.getJDA().getUserById(partnerId) != null) {
                    partner = "`" + context.getJDA().getUserById(partnerId).getName() + "`";
                } else {
                    partner = "`Invalid`";
                }
            }

        }

        relationshipBuilder.append("Partner: " + partner);
        embedBuilder.addField(":heart: Relationships:", relationshipBuilder.toString(), false);

        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
        context.getEvent().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "userinfo";
    }

    @Override
    public String getDescription() {
        return "Displays general information about a discord user.";
    }

    @Override
    public String getEmoji() {
        return "👤";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "user", "The user you want to get information on.", true));
        return optionData;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.TOOLS;
    }
}
